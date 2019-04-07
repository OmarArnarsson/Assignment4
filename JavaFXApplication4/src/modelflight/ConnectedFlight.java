package modelflight;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class ConnectedFlight implements Cloneable{

    private ArrayList<Flight> flightList;
    private Double totalEconomyPrice;
    private Double totalBusinessPrice;

    public ConnectedFlight(ArrayList<Flight> flights) {
        this.flightList = flights;
        calculatePrices();
    }

    public ConnectedFlight() {
        this.flightList = new ArrayList<Flight>();
    }

    /**
     *
     * @return first departure location
     */
    public String getFirstDep() {
        return flightList.get(0).getDepLocation();
    }

    /**
     *
     * @return last arrival location
     */
    public String getLastArr() {
        return flightList.get(flightList.size()-1).getArrLocation();
    }

    public Calendar getLastArrTime() {
        if(flightList.size() == 0) {
            System.out.println("a");
            Calendar noFlightDate = Calendar.getInstance();
            noFlightDate.setTimeInMillis(0);
            return noFlightDate;
        } else {
            return flightList.get(flightList.size() - 1).getArrDate();
        }
    }

    /**
     * Calculates the total time of whole connected flight from the
     * arrival time of last airport and departure time of first airport
     * @return Total flight time in Date format
     */
    public Calendar getTotalFlightTime() {
        Calendar depDate = flightList.get(0).getDepDate();
        Calendar arrDate = flightList.get(flightList.size()-1).getArrDate();

        long difference = arrDate.getTimeInMillis() - depDate.getTimeInMillis();

        Calendar returnValue = Calendar.getInstance();
        returnValue.setTimeInMillis(difference);

        return returnValue;
    }

    /**
     * Add a flight to tha end of the <flightList>
     * @param flight
     */
    public void addFlightToEnd(Flight flight) {
        flightList.add(flight);
        calculatePrices();
    }

    /**
     * Calculate the total price of economy seats and business seats in the <flightList>,
     * and updates the variables <totalEconomyPrice> and <totalBusinessPrice>.
     * @return
     */
    private void calculatePrices() {
        totalEconomyPrice = 0.0;
        totalBusinessPrice = 0.0;
        for (int i = 0; i < flightList.size(); i++) {
            totalEconomyPrice += flightList.get(i).getPrice(true);
            totalBusinessPrice += flightList.get(i).getPrice(false);
        }
    }

    /**
     *
     * @return total price of economy seats
     */
    public Double getTotalEconomyPrice() {
        return totalEconomyPrice;
    }

    /**
     *
     * @return total prices of business seats
     */
    public Double getTotalBusinessPrice() {
        return totalBusinessPrice;
    }

    public int getFlightCount() {
        return flightList.size();
    }

    public ConnectedFlight clone() throws CloneNotSupportedException {
        return new ConnectedFlight((ArrayList)this.flightList.clone());
    }

    // Harri test föll

    public void printFlight() {
        System.out.println("Tengiflug:");
        for(int i = 0; i < flightList.size(); i++) {
            System.out.println("Flug:" + flightList.get(i).getDepLocation() + " til " + flightList.get(i).getArrLocation() + " í loftið kl " + flightList.get(i).getDepDate().getTime().toGMTString() + " og lendir kl " + flightList.get(i).getArrDate().getTime().toGMTString());
        }
    }

    public Flight getFlight(int i) {
        return flightList.get(i);
    }

    public String toString() {
        String flightString = "Flug:";
        for(int i = 0; i < flightList.size(); i++) {
            flightString += "\n" + flightList.get(i).toString();
        }
        return flightString;
    }

}
