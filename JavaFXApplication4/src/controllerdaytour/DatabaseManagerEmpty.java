package controllerdaytour;

import modeldaytour.Tour;
import modeldaytour.TourFilter;

import java.util.LinkedList;

public class DatabaseManagerEmpty implements DatabaseManagerInterface {

    @Override
    public LinkedList<Tour> selectTours(TourFilter filter) {
        LinkedList<Tour> result = new LinkedList<Tour>();
        return result;
    }

    @Override
    public LinkedList<Tour> searchByDate(TourFilter filter) {
        return null;
    }
}