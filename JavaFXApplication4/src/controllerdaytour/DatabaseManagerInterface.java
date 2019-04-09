package controllerdaytour;

import modeldaytour.Tour;
import modeldaytour.TourFilter;

import java.util.LinkedList;

interface DatabaseManagerInterface {
    public LinkedList<Tour> selectTours(TourFilter filter) throws ClassNotFoundException;
    LinkedList<Tour> searchByDate(TourFilter filter) throws ClassNotFoundException;
}