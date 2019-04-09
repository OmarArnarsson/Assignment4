package modeldaytour;

import controllerdaytour.DatabaseManager;
import viewdaytour.Main;

import java.util.Date;

public class BookingD {

    private Tour chosenTour;    // id of the tour that the customer has chosen
    private int price;          // price of the tour that has been chosen
    private String customerId;  // id of the customer booking the trip
    private Date tourStart;     // starting date/time of the tour
    private Date tourFinish;    // finishing date/time of the tour

    /**
     * @param chosenTour the tour that the customer has chosen
     * @param customerId the id of the customer
     */
    public BookingD(Tour chosenTour, String customerId) {
        this.chosenTour = chosenTour;
        this.price = chosenTour.getPrice();
        this.customerId = customerId;
        this.tourStart = chosenTour.getTimeStart();
        this.tourFinish = chosenTour.getTimeFinish();
    }

    // FUNCTIONS ------------------------------------------------------------

    /**
     * @return total price of trip calculated for the whole group
     */
    public int getPriceForGroup() {
        int groupSize = Main.customer.getFilter().getGroupSize();
        int tourPrice = chosenTour.getPrice();
        return groupSize*tourPrice;
    }

    /**
     * @return the start time of day of the chosen tour fx: "18:00"
     */
    public String getStartTime() {
        return tourStart.toString().substring(11,16);
    }

    /**
     * @return the finish time of day of the chosen tour fx: "22:00"
     */
    public String getFinishTime() {
        return tourFinish.toString().substring(11,16);
    }

    /**
     * simplified string of the date that is only the month and day of month fx.: "Jun 05"
     * @return date of the tour month and day of month
     */
    public String getDate() {
        return tourStart.toString().substring(4,10);
    }

    /**
     * *REMOVES* seats from database
     * should only be called when the tour has been chosen and the seats
     * are therefore not available anymore
     */
    public void removeSeatsFromTour() {
        DatabaseManager db = new DatabaseManager();
        int groupSize = Main.customer.getFilter().getGroupSize();
        db.removeSeats(groupSize, chosenTour.getId(), chosenTour.getSeatsLeft());
    }

    // GETTERS --------------------------------------------------------------

    public Tour getChosenTour() {
        return chosenTour;
    }

    public int getPrice() {
        return price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Date getTourStart() {
        return tourStart;
    }

    public Date getTourFinish() {
        return tourFinish;
    }
}