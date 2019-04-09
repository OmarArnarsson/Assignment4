package modeldaytour;

public class TourFilter {

    private int price;
    private int groupSize;
    private String location;
    private String tourType;
    private String timeStart;
    private boolean guidedTour;
    private boolean privateTour;
    private boolean accessibility;


    // FUNCTIONS -------------------------------------------

    /**
     * Makes sure the group isn't smaller than 0 or larger than 1000
     * @param g
     * @return g or -1
     */
    public int gSize(int g){
        if(g < 1000 && g > 0){
            return g;
        }
        return -1;
    }


    // GETTERS ---------------------------------------------

    public int getPrice() {
        return price;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getLocation() {
        return location;
    }

    public String getTourType() {
        return tourType;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public int isGuidedTour() {
        int ifTrue = 1;
        int ifFalse = 0;

        if (this.guidedTour) return ifTrue;
        else return ifFalse;
    }

    public int isPrivateTour() {
        int ifTrue = 1;
        int ifFalse = 0;

        if (this.privateTour) return ifTrue;
        else return ifFalse;
    }

    public int isAccessibility() {
        int ifTrue = 1;
        int ifFalse = 0;

        if (this.accessibility) return ifTrue;
        else return ifFalse;
    }


    // SETTERS -----------------------------------------------

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = (int) price;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setGroupSize(double groupSize) {
        this.groupSize = (int) groupSize;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLocation(Object location) {
        this.location = location.toString();
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    /**
     * sets timeStart to match the database date format
     * @param timeStart is in ISO DatePicker form
     */
    public void setTimeStart(String timeStart) {
        String newTimeStart = "";
        String[] a = timeStart.split("-");
        String year = a[0].substring(2,4);
        String month = a[1];
        String day = a[2];
        newTimeStart = month + "/" + day + "/" + year;

        this.timeStart = newTimeStart;
    }

    public void setGuidedTour(boolean guidedTour) {
        this.guidedTour = guidedTour;
    }

    public void setPrivateTour(boolean privateTour) {
        this.privateTour = privateTour;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

}
