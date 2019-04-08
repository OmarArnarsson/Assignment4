package controllerdaytour;

import modeldaytour.TourFilter;
import modeldaytour.Tour;

import java.util.LinkedList;

public class TourController {

    public DatabaseManagerInterface databaseManagerInterface;
    public LinkedList<Tour> result;
    public int numTripsFound;

    public TourController(DatabaseManagerInterface databaseManagerInterface) {
        this.databaseManagerInterface = databaseManagerInterface;
    }

    // FUNCTIONS ---------------------------------------------------------------

    /**
     * The search method for day tours
     * @param filter the tour filter that the user chooses
     * @return
     */
    public LinkedList<Tour> search(TourFilter filter) throws Exception {
        result = databaseManagerInterface.selectTours(filter);
        return result;
    }
    public LinkedList<Tour> searchByDate(TourFilter filter) throws Exception {
        result = databaseManagerInterface.searchByDate(filter);
        return result;
    }

    /*
    public void bookTour() {

    }

    public void payTour() {

    }*/


    // SETTERS ------------------------------------------------------------------

    public void setNumTripsFound(int numTripsFound) {
        this.numTripsFound = numTripsFound;
    }

    // GETTERS ------------------------------------------------------------------


    public int getNumTripsFound() {
        return numTripsFound;
    }
}