package modeldaytour;

import javafx.scene.image.Image;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controllerdaytour.TourController;

public class Tour {

    private int id;
    private String tourName;
    private int price;
    private String tourType;
    private String location;
    private String about;
    private Date timeStart;
    private Date timeFinish;
    private boolean guidedTour;
    private boolean privateTour;
    private boolean accessibility;
    private int seatsLeft;

    // FUNCTIONS ----------------------------------------------------------------


    // SETTERS -------------------------------------------------------------------

    /**
     * Takes a string and creates a date on the form "MM/dd/yyyy-hh:mm"
     * @param timeStart time start from database
     */
    public void setTimeStart(String timeStart) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-hh:mm");
        String dateInString = timeStart;

        try {
            this.timeStart = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a string and creates a date on the form "MM/dd/yyyy-hh:mm"
     * @param timeFinish time finish from database
     */
    public void setTimeFinish(String timeFinish) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-hh:mm");
        String dateInString = timeFinish;

        try {
            this.timeFinish = formatter.parse(dateInString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * accessibility is an int in the database so it has to be
     * changed to boolean here
     * @param accessibility
     */
    public void setAccessibility(int accessibility) {
        if (accessibility == 1) this.accessibility = true;
        if (accessibility == 0) this.accessibility = false;
    }

    /**
     * guidedTour is an int in the database so it has to be
     * changed to boolean here
     * @param guidedTour
     */
    public void setGuidedTour(int guidedTour) {
        if (guidedTour == 1) this.guidedTour = true;
        if (guidedTour == 0) this.guidedTour = false;
    }

    /**
     * privateTour is an int in the database so it has to be
     * changed to boolean here
     * @param privateTour
     */
    public void setPrivateTour(int privateTour) {
        if (privateTour == 1) this.privateTour = true;
        if (privateTour == 0) this.privateTour = false;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setSeatsLeft(int seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    // GETTERS ---------------------------------------------------------------------


    public int getId() {
        return id;
    }

    public String getTourName() {
        return tourName;
    }

    public String getTourType() {
        return tourType;
    }

    public String getLocation() {
        return location;
    }

    public String getAbout() {
        return about;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public boolean isGuidedTour() {
        return guidedTour;
    }

    public boolean isPrivateTour() {
        return privateTour;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    public int getPrice() {
        return price;
    }
}