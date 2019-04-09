package controllerdaytour;

import modeldaytour.Tour;
import modeldaytour.TourFilter;
import viewdaytour.Main;
import java.util.LinkedList;
import java.sql.*;

public class DatabaseManager implements DatabaseManagerInterface {

    private Connection connection = null;
    private String databaseUrl = "jdbc:sqlite:db/Database.db";;
    private LinkedList<Tour> result = new LinkedList<Tour>();

    /**
     * Searches for tours by all relevant filter variables
     * @param filter from the customer
     * @return linked list of tours that match the filter
     * @throws ClassNotFoundException if there is not a connection to the db
     */
    @Override
    public LinkedList<Tour> selectTours(TourFilter filter) throws ClassNotFoundException {

        String filterLocation = filter.getLocation();
        String filterTimeStart = filter.getTimeStart();
        int filterPrivateTour = filter.isPrivateTour();
        int filterAccessibility = filter.isAccessibility();
        int filterGuidedTour = filter.isGuidedTour();
        String filterTourType = filter.getTourType();
        int filterGroupSize = filter.getGroupSize();
        int filterPrice = filter.getPrice();

        int inaccuracy = 1000;
        int numTripsFound = 0;

        String sqlString = "SELECT * FROM DAYTOURS WHERE " +
                           "location LIKE ? AND " +        // 1
                           "timeStart LIKE ? AND " +       // 2
                           "tourType LIKE ? AND " +        // 3
                           "privateTour = ? AND " +     // 4
                           "accessibility = ? AND " +   // 5
                           "guidedTour = ? AND " +      // 6
                           "seatsLeft >= ? AND " +         // 7
                           "price <= ?";                   // 8


        try {
            connection = DriverManager.getConnection(databaseUrl);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);

            preparedStatement.setString(1, filterLocation);
            preparedStatement.setString(2, filterTimeStart.concat("%"));
            preparedStatement.setString(3, filterTourType);
            preparedStatement.setInt(4, filterPrivateTour);
            preparedStatement.setInt(5, filterAccessibility);
            preparedStatement.setInt(6, filterGuidedTour);
            preparedStatement.setInt(7, filterGroupSize);
            preparedStatement.setInt(8, filterPrice+inaccuracy);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {

                Tour tour = new Tour();

                tour.setId(rs.getInt("id"));
                tour.setTourName(rs.getString("tourName"));
                tour.setTourType(rs.getString("tourType"));
                tour.setLocation(rs.getString("location"));
                tour.setAbout(rs.getString("aboutTour"));
                tour.setTimeStart(rs.getString("timeStart"));
                tour.setTimeFinish(rs.getString("timeFinish"));
                tour.setSeatsLeft(rs.getInt("seatsLeft"));
                tour.setPrivateTour(rs.getInt("privateTour"));
                tour.setGuidedTour(rs.getInt("guidedTour"));
                tour.setAccessibility(rs.getInt("accessibility"));
                tour.setPrice(rs.getInt("price"));

                result.add(tour);

                numTripsFound++;

            }

        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

        Main.tourController.setNumTripsFound(numTripsFound);

        return result;
    }


    /**
     * Searches by date, location, timeStart, tourType, seatsleft and price excludes the boolean
     * varables that are used in the searchTours function.
     * @param filter filter from the customer
     * @return linked list og tours that match the given filter
     * @throws ClassNotFoundException if there is not a connection to the db
     */
    @Override
    public LinkedList<Tour> searchByDate(TourFilter filter) throws ClassNotFoundException {

        String filterLocation = filter.getLocation();
        String filterTimeStart = filter.getTimeStart();
        String filterTourType = filter.getTourType();
        int filterGroupSize = filter.getGroupSize();
        int filterPrice = filter.getPrice();

        int inaccuracy = 1000;
        int numTripsFound = 0;

        String sqlString = "SELECT * FROM DAYTOURS WHERE " +
                "location LIKE ? AND " +        // 1
                "timeStart LIKE ? AND " +       // 2
                "tourType LIKE ? AND " +        // 3
                "seatsLeft >= ? AND " +         // 4
                "price <= ?";                   // 5


        try {
            connection = DriverManager.getConnection(databaseUrl);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);

            preparedStatement.setString(1, filterLocation);
            preparedStatement.setString(2, filterTimeStart.concat("%"));
            preparedStatement.setString(3, filterTourType);
            preparedStatement.setInt(4, filterGroupSize);
            preparedStatement.setInt(5, filterPrice+inaccuracy);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {

                Tour tour = new Tour();

                tour.setId(rs.getInt("id"));
                tour.setTourName(rs.getString("tourName"));
                tour.setTourType(rs.getString("tourType"));
                tour.setLocation(rs.getString("location"));
                tour.setAbout(rs.getString("aboutTour"));
                tour.setTimeStart(rs.getString("timeStart"));
                tour.setTimeFinish(rs.getString("timeFinish"));
                tour.setSeatsLeft(rs.getInt("seatsLeft"));
                tour.setPrivateTour(rs.getInt("privateTour"));
                tour.setGuidedTour(rs.getInt("guidedTour"));
                tour.setAccessibility(rs.getInt("accessibility"));
                tour.setPrice(rs.getInt("price"));

                result.add(tour);

                numTripsFound++;

            }

        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

        Main.tourController.setNumTripsFound(numTripsFound);

        return result;
    }


    /**
     * removes the number of seats the customer has chosen to book
     * @param numSeatsBooked number of seats that the customer is booking
     * @param tourId the id of the tour that needs to be updated due to booking
     * @param seatsLeft seats left on the tour that is being booked
     */
    public void removeSeats(int numSeatsBooked, int tourId, int seatsLeft) {

        int updatedSeatsLeft = seatsLeft - numSeatsBooked;

        String sqlString ="UPDATE DAYTOURS " +
                          "SET seatsLeft = ? " +
                          "WHERE id = ?";

        try {
            connection = DriverManager.getConnection(databaseUrl);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, updatedSeatsLeft);
            preparedStatement.setInt(2, tourId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

    }
}