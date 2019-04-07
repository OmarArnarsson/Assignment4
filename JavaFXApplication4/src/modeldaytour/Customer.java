package modeldaytour;

public class Customer {

    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String mobile;
    public TourFilter filter;
    public int tourPickID;       // helga added this, should we not save the tour that the customer picks?
    public Booking booking;

    public Customer(String id, String firstName, String lastName, String email,
                    String mobile, TourFilter filter, int tourPickID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.filter = filter;
        this.tourPickID = tourPickID;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer(String id) {
        this.id = id;
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

    public void setFilter(TourFilter filter) {
        this.filter = filter;
    }

}
