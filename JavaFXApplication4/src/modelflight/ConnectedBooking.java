package modelflight;

import java.util.ArrayList;

public class ConnectedBooking {

    ArrayList<Booking> bookingList;
    ConnectedFlight flightList;
    Traveller passenger;
    String bookingNumber;
    Double price;

    public ConnectedBooking(ConnectedFlight connFlight) {
        this.flightList = connFlight;
        bookingList = new ArrayList<Booking>();
    }

    public void addTraveller(Traveller traveller) {
        this.passenger = traveller;
    }

    public void addBooking(Booking booking) {
        this.bookingList.add(booking);
    }

    public void calculatePrice(){
        price = flightList.getTotalEconomyPrice();
    }


    /* Getters and setters */
    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public ConnectedFlight getFlightList() {
        return flightList;
    }

    public void setFlightList(ConnectedFlight flightList) {
        this.flightList = flightList;
    }

    public Traveller getPassenger() {
        return passenger;
    }

    public void setPassenger(Traveller passenger) {
        this.passenger = passenger;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}


