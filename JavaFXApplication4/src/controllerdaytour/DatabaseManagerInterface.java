package controllerdaytour;

import modeldaytour.Tour;
import modeldaytour.TourFilter;

import java.util.LinkedList;

interface DatabaseManagerInterface {
    public void openDB() throws ClassNotFoundException;
    public void closeDB();
    public LinkedList<Tour> selectTours(TourFilter filter) throws ClassNotFoundException;
    public LinkedList<Tour> searchByDate(TourFilter filter) throws ClassNotFoundException;
}