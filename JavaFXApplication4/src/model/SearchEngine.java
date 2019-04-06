package model;

import controller.DatabaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchEngine {

    private ArrayList<ConnectedFlight> results;
    private String depLocation;
    private Calendar depDate;
    private String arrLocation;
    private int flexibility; // hve margir dagar flex
    private boolean economy;
    private int passangerCount;

    private DatabaseController dbController;

    public SearchEngine() {
        this.dbController = new DatabaseController();
        this.results = new ArrayList<ConnectedFlight>();
    }

    public SearchEngine(String databaseName) {
        this.dbController = new DatabaseController(databaseName);
        this.results = new ArrayList<ConnectedFlight>();
    }

    /**
     * Sets the departing location
     * @param depLocation 3-char string to identify airport
     */
    public void setDepLocation(String depLocation) {
        this.depLocation = depLocation;
    }

    /**
     * Sets the time at which or after you want to fly
     * @param depDate Calendar object with the date
     */
    public void setDepDate(Calendar depDate) {
        this.depDate = depDate;
    }

    /**
     * Sets the arriving location
     * @param arrLocation 3-char string to identify airport
     */
    public void setArrLocation(String arrLocation) {
        this.arrLocation = arrLocation;
    }

    public void setFlexibility(int flexibility) {
        if(flexibility < 0)
            this.flexibility = 0;
        else
            this.flexibility = flexibility;
    }

    /**
     * Sets the class you want to search for
     * @param economy True is economy, false is business
     */
    public void setEconomy(boolean economy) {
        this.economy = economy;
    }

    /**
     * Sets how many passengers you want to search for a flight for
     * @param passangerCount Integer number of passengers
     */
    public void setPassangerCount(int passangerCount) {
        if(passangerCount < 0)
            this.passangerCount = 0;
        else
            this.passangerCount = passangerCount;
    }

    /**
     * Search for a flight path based on the set variables
     * @return SearchResults object with all paths
     * @throws SQLException
     * @throws CloneNotSupportedException
     */
    public SearchResult findFlightCourse() throws SQLException, CloneNotSupportedException {

        Calendar searchDepDate = Calendar.getInstance();
        searchDepDate.setTimeInMillis(depDate.getTimeInMillis() - 86400000 * flexibility);
        Calendar searchArrDate = Calendar.getInstance(); // 48 hours to fly the whole way
        searchArrDate.setTimeInMillis(depDate.getTimeInMillis() + 86400000 * (int)(flexibility + 2));

        // Gets flights that fulfil the date requirements for arrival and departure
        ResultSet rs = this.dbController.getFlightList(searchDepDate,searchArrDate);

        ArrayList<Flight> flightList = new ArrayList<Flight>();

        while(rs.next()) { // Iterate throug the ResultSet to get all flights
            flightList.add(new Flight(rs.getString("flightNumber"),rs.getString("depLocation"),
                    rs.getString("arrLocation"),rs.getLong("depDate"),
                    rs.getLong("arrDate"),rs.getString("aircraftType"),
                    rs.getDouble("economyPrice"),rs.getDouble("businessPrice")));
        }

        // Create a graph of flights and airports used in the search
        Graph flightGraph = new Graph();
        // Add all flights and airports to the search
        for(int i = 0; i < flightList.size(); i++) {
            if(flightList.get(i).getSeatsLeft(this.economy) >= this.passangerCount) {
                flightGraph.addFlight(flightList.get(i));
            }
        }

        ArrayList<String> visitedLocations = new ArrayList<String>();
        ConnectedFlight flightRoute = new ConnectedFlight();

        // Return no ConnectedFlights if arrival airport and departure airport is the same
        if(this.depLocation.equals(this.arrLocation))
            return new SearchResult(new ArrayList<ConnectedFlight>());

        // Return no ConnectedFlight if arrival airport or departure airport is not in the graph
        if(flightGraph.getAirport(this.arrLocation) == null || flightGraph.getAirport(this.depLocation) == null)
            return new SearchResult(new ArrayList<ConnectedFlight>());

        // Recursively search through the whole graph for all possible paths
        recursiveSearch(flightGraph, depLocation, visitedLocations, flightRoute, depDate.getTimeInMillis());

        // Return the results from the recursive search
        return new SearchResult(this.results);
    }

    /**
     * Recursive search for all flights paths between currentLocation and visitedLocation
     * @param flightGraph Graph of the flights we are using for our search
     * @param currentLocation String value for current location in the recursive search
     * @param visitedLocation String ArrayList for all locations that we have already visited in the recursive search
     * @param flightRoute ConnectedFlight path which we have so far gone in the recursive search
     * @param arrivalTime The time at which we arrived at current location, long value in millisec since UTC and
     *                    should have gotten our baggage and etc (landing time + 1 hour)
     * @throws CloneNotSupportedException
     */
    public void recursiveSearch(Graph flightGraph, String currentLocation, ArrayList<String> visitedLocation,
                                ConnectedFlight flightRoute, long arrivalTime) throws CloneNotSupportedException {
        // Add our current location to the visited location list
        visitedLocation.add(currentLocation);
        //
        Calendar currentTime = Calendar.getInstance();

        if (flightRoute.getFlightCount() == 0) {
            currentTime.setTimeInMillis(arrivalTime);
        } else {
            currentTime.setTimeInMillis(arrivalTime + 3600000); // 1 hour to get baggage and etc
        }
        // End the search if we are at the destination location
        if (currentLocation.equals(this.arrLocation)) {
            this.results.add(flightRoute);
            return;
        }
        // Iterate through all the flights after arrivalTime from our current location
        for (int i = 0; i < flightGraph.getAirport(currentLocation).getPossibleFlights(currentTime).size(); i++) {
            Flight flightToCheck = flightGraph.getAirport(currentLocation).getPossibleFlights(currentTime).get(i);
            // Only check flights to airports which we have never been to before in this recursive search
            if (!visitedLocation.contains(flightToCheck.getArrLocation())) {
                // Create a new ConnectedFlight object which clones the one in the input and add next flight to it
                ConnectedFlight newFlightRoute = flightRoute.clone();
                newFlightRoute.addFlightToEnd(flightToCheck);
                // Create a new ArrayList clone of our visitedLocation ArrayList
                ArrayList<String> newVisitedLocation = new ArrayList<String>();
                newVisitedLocation = (ArrayList) visitedLocation.clone();
                // Recursivly search for flights from our next airport, keeping our path so far and visited
                // locations with us so we can effecivly search the graph
                recursiveSearch(flightGraph, flightToCheck.getArrLocation(), newVisitedLocation,
                        newFlightRoute, newFlightRoute.getLastArrTime().getTimeInMillis());
            }
        }
    }
}

/**
 * Class for a graph of flights and airports
 */
class Graph {

    private ArrayList<Airport> airports;

    Graph() {
        airports = new ArrayList<Airport>();
    }

    /**
     * Adds an airport the graph
     * @param location 3-char string identifier of the airport
     */
    public void addAirport(String location) {
        airports.add(new Airport(location));
    }

    /**
     * Retrieve airport object
     * @param location 3-char string identifier of the airport
     * @return Airport object for the requested location
     */
    Airport getAirport(String location) {
        for(int i = 0; i < airports.size(); i++) {
            if(airports.get(i).getLocation().equals(location))
                return airports.get(i);
        }
        return null;
    }

    /**
     * Add flight to the graph
     * @param flight Flight object to be added to the graph
     */
    void addFlight(Flight flight) {
        // Retriever the departure airport object of the flight
        Airport airport = getAirport(flight.getDepLocation());

        if(airport != null) {
            airport.addOutboundFlight(flight);
        } else { // If there is no such airport, add it the graph then add the flight to it
            addAirport(flight.getDepLocation());
            airport = getAirport(flight.getDepLocation());
            airport.addOutboundFlight(flight);
        }
    }

    public void printAirports() {
        for(int i = 0; i < airports.size(); i++) {
            System.out.println(airports.get(i).getLocation());
        }
    }

    public void printFlights() {
        for(int i = 0; i < airports.size(); i++) {
            System.out.print(airports.get(i).getLocation());
            ArrayList<Flight> flightsFromAirport = airports.get(i).getAllFlights();
            for(int j = 0; j < flightsFromAirport.size(); j++) {
                Calendar time = Calendar.getInstance();
                time = flightsFromAirport.get(j).getArrDate();
                System.out.println(flightsFromAirport.get(j).getDepLocation() + " to " + flightsFromAirport.get(j).getArrLocation() + " at " + time.getTime().toGMTString());
            }
        }
    }
}

/**
 * Class for the airports, used in the Graph class
 */
class Airport {

    private String location;
    private ArrayList<Flight> outboundFlights;

    Airport(String location) {
        this.location = location;
        this.outboundFlights = new ArrayList<Flight>();
    }

    /**
     * Add an outbound flight to the airport
     * @param flight Flight object to be added to this airport
     */
    void addOutboundFlight(Flight flight) {
        outboundFlights.add(flight);
    }

    /**
     * Show you all flights after the supplied date
     * @param depDate Calender object with the date
     * @return
     */
    ArrayList<Flight> getPossibleFlights(Calendar depDate) {

        ArrayList<Flight> outboundList = new ArrayList<Flight>();

        for(int i = 0; i < outboundFlights.size(); i++) {
            if(outboundFlights.get(i).getDepDate().getTimeInMillis() >= depDate.getTimeInMillis())
                outboundList.add(outboundFlights.get(i));
        }
        return outboundList;
    }

    /**
     * Get all flights from this airport
     * @return ArrayList of flights from the airport
     */
    ArrayList<Flight> getAllFlights() {
        return outboundFlights;
    }

    /**
     * Gets the 3-char identifier for the airport
     * @return 3-char string identifier of the airport
     */
    String getLocation() {
        return this.location;
    }
}