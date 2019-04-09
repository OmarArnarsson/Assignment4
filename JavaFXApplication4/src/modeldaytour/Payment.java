package modeldaytour;

import java.util.Date;

public class Payment {

    private int price;
    private int tourId;
    private String customerId;
    private String customerName;
    private Date date;

    public Payment(int price, int tourId, String customerId,
                   String customerName, Date date) {
        this.price = price;
        this.tourId = tourId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getTourId() {
        return tourId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getDate() {
        return date;
    }
}