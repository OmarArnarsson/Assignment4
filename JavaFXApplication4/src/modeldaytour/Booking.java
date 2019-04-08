package modeldaytour;

import java.util.Date;

public class Booking {

    private int tourId;         // id of the tour that the customer has chosen
    private int price;          // price of the tour that has been chosen
    private String customerId;  // id of the customer booking the trip
    private String time;          // date of the tour
    private String start;       // starting time of the tour
    private String finish;      // finishing time of the tour

    // GETTERS --------------------------------------------------------------

    public int getTourId() {
        return tourId;
    }

    public int getPrice() {
        return price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTime() {
        return time;
    }

    // SETTERS ---------------------------------------------------------------

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setTime(String time) {
        this.time = time;
    }

}