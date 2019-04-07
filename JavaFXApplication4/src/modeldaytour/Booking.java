package modeldaytour;

import java.util.Date;

public class Booking {

    private int tourId;
    private int price;
    private String customerId;
    private Date time;
    private int bookingStatus; //breyta þessu síðar kannsk

    /**
     * Constructor for Booking class
     * @param tourId
     * @param price
     * @param customerId
     * @param time
     * @param bookingStatus
     */
    private Booking(int tourId, int price, String customerId,
                   Date time, int bookingStatus) {
        this.tourId = tourId;
        this.price = price;
        this.customerId = customerId;
        this.time = time;
        this.bookingStatus = bookingStatus;
    }

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

    public int getBookingStatus() {
        return bookingStatus;
    }
}