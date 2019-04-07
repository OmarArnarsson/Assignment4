package controllerdaytour;

import modeldaytour.Tour;
import modeldaytour.TourFilter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class DatabaseManagerSuccess implements DatabaseManagerInterface {

    @Override
    public void openDB() {
        // gerir ekki neitt og skilar engu
    }

    @Override
    public void closeDB() {
        // gerir ekki neitt og skilar engu
    }

    @Override
    public LinkedList<Tour> selectTours(TourFilter filter) {

        Tour tour1 = new Tour();

        tour1.setLocation("Akureyri");
        tour1.setTimeStart("06/09/19");
        tour1.setTourType("Bus Ride");
        tour1.setGuidedTour(1);
        tour1.setAccessibility(0);
        tour1.setPrivateTour(0);
        tour1.setPrice(25000);
        tour1.setSeatsLeft(20);


        LinkedList<Tour> result = new LinkedList<Tour>();
        result.add(tour1);

        return result;
    }
}