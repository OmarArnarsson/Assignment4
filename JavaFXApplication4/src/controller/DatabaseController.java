package controller;

import model.ConnectedBooking;
import model.Flight;

import java.sql.*;
import java.util.Calendar;

public class DatabaseController {

    private Connection databaseConnection;
    private String databaseName;

    public DatabaseController() {
        this.databaseName = "jdbc:sqlite:db/flights.db"; // breyta í rétt

        try {
            Class.forName("org.sqlite.JDBC");
            this.databaseConnection = DriverManager.getConnection(this.databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DatabaseController(String databaseName) {
        this.databaseName = "jdbc:sqlite:" + databaseName;

        try {
            Class.forName("org.sqlite.JDBC");
            this.databaseConnection = DriverManager.getConnection(this.databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getFlightList(Calendar startTime, Calendar endTime) {
        long startTimeUTC = startTime.getTimeInMillis();
        long endTimeUTC = endTime.getTimeInMillis(); // 36 tímar fyrir flugið sjálft í millisek

        String sql = "SELECT * FROM flights WHERE depDate >= ? AND arrDate <= ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = databaseConnection.prepareStatement(sql);
            pstm.setLong(1, startTimeUTC);
            pstm.setLong(2, endTimeUTC);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getFlight(String flightNumber) {
        String sql = "SELECT * FROM flights WHERE flightNumber = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = databaseConnection.prepareStatement(sql);
            pstm.setString(1, flightNumber);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getBookedSeats(String flightNumber) {
        String sql = "SELECT * FROM booking WHERE flightID = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = databaseConnection.prepareStatement(sql);
            pstm.setString(1, flightNumber);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getConnectedBooking(String bookingNumber) {
        String sql = "SELECT * FROM booking WHERE bookingNumber = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = databaseConnection.prepareStatement(sql);
            pstm.setString(1, bookingNumber);
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void addFlight(Flight flight) {

    }

    public boolean updateFlight(Flight oldFlight, Flight newFlight) {
        return false;
    }

    public void removeFlight(Flight flight) {
        String sql = "DELETE FROM flights WHERE flightNumber = ?";
        PreparedStatement pstm = null;
        try {
            pstm = databaseConnection.prepareStatement(sql);
            pstm.setString(1, flight.getFlightNumber());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBooking(ConnectedBooking conBooking) {

    }

    public boolean updateBooking(ConnectedBooking booking, String bookingNumber) {
        return false;
    }

    public void removeBooking(String bookingNumber) {
        String sql = "DELETE FROM bookings WHERE bookingNumber = ?";
        PreparedStatement pstm = null;
        try {
            pstm = databaseConnection.prepareStatement(sql);
            pstm.setString(1, bookingNumber);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
