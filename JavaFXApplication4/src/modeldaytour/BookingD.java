package modeldaytour;

import java.util.Date;

public class BookingD {

    private int tourId;         // id of the tour that the customer has chosen
    private int price;          // price of the tour that has been chosen
    private String customerId;  // id of the customer booking the trip
    private Date time;          // date of the tour

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

    public Date getTime() {
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

    public void setTime(Date time) {
        this.time = time;
    }

}