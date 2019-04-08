package modeldaytour;

import java.lang.reflect.Array;
import java.util.Date;

public class TourFilter {


    // þarf að vera public þangað til að það eru komnir setters
    // TODO: SJÁ FYRIR NEÐAN SETTERANA SEM ÞARF AÐ GERA
    public int price;
    public int groupSize;
    public String location;
    public String tourType;
    public String timeStart;    // breytti í string því það kemur sem string úr viðmótinu
    public boolean guidedTour;  // breytum í date síðar þegar að búið er að velja ferð í viðmóti kv helga
    public boolean privateTour;
    public boolean accessibility;


/*
    public TourFilter(int price, String groupSize,
                      String location, String tourType,
                      String timeStart, boolean guidedTour,
                      boolean privateTour, boolean accessibility) {

        this.price = price;
        this.groupSize = gSize(Integer.parseInt(groupSize));
        this.location = location;
        this.tourType = tourType;
        this.timeStart = timeStart;
        this.guidedTour = guidedTour;
        this.privateTour = privateTour;
        this.accessibility = accessibility;
    }
*/
    /**
     * Föll sem skoða hvort intak sé rétt
     */

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
    // TODO: setja inn settera þannig að þegar tekið er við
    //       breytum úr viðmóti setjum við upp gildin á réttu
    //       formi sem passa við gagnagrunns breyturnar.
    //       þá ættum við að taka út constructorinn ... held ég

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



    /*
    public void TourFound(ArrayList<Tour> tours) {
        this.tourList = tours;
        calculatePrice();
    }
    */
    /*
    private void calculatePrice() {
        totalPrice = 0;
        for (int i = 0; i < tourList.size(); i++) {
            totalPrice += tourList.get(i).getPrice();
        }
    }
    */
    /*
    public int getTourCount() {
        return tourList.size();
    }*/

    /*
    public void showTour() {
        for (int i = 0; i < tourList.size(); i++) {
            System.out.println("Name of your: " + tourList.get(i).getTourName()
                    + "with the total price of: " + tourList.get(i).getPrice());
        }
    }*/

}
