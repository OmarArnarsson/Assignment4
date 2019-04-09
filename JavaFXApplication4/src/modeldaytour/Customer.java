package modeldaytour;

public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    public TourFilter filter; // search criteria from customer to be looked for in the db
    private int tourId;        // id of the tour that the customer chooses
    private BookingD booking;   // booking for the customer

    public Customer() {
        filter = new TourFilter();
    }

    // GETTERS --------------------------------------------------------------------------------------------

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public TourFilter getFilter() {
        return filter;
    }

    public int getTourId() {
        return tourId;
    }

    public BookingD getBooking() {
        return booking;
    }

    // SETTERS --------------------------------------------------------------------------------------------

    public void setCustomerId(String id) {
        this.customerId = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setFilter(TourFilter filter) {
        this.filter = filter;
    }

    public void setTourId(int tourPickID) {
        this.tourId = tourPickID;
    }

    public void setBooking(BookingD booking) {
        this.booking = booking;
    }
}
