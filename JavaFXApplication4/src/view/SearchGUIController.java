package view;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ConnectedFlight;
import model.Flight;
import model.SearchEngine;
import model.SearchResult;
import java.sql.SQLException;

import java.net.URL;
import java.util.*;

import java.io.IOException;
import java.util.ResourceBundle;

public class SearchGUIController implements Initializable {

    // Search engine for this search session
    private SearchEngine searchEngine;
    // Current search results
    private SearchResult currentResult;

    @FXML
    private ExpandableListView<ConnectedFlight> flightList; // List view for the results
    @FXML
    private SearchInputController searchInputController;

    private ExpandableListView.ContentProvider<ConnectedFlight> contentProvider = new ExpandableListView.ContentProvider<ConnectedFlight>() {
        @Override
        public String getTitleOf(final ConnectedFlight item) {
            String s = "Frá: " + item.getFirstDep() + "  Til: " + item.getLastArr() + "\n" +
                    "Kl.: " + item.getFlight(0).getDepDate().getTime() + "\n" +
                    "Heildarferðatími:" + Long.toString(item.getTotalFlightTime().getTimeInMillis()/60000) + " mín.";
            return s;
        }

        @Override
        public String getContentOf(final ConnectedFlight item) {
            String s = "Heildarverð á almenningsfarrými: " + Double.toString(item.getTotalEconomyPrice()) + "\n" +
                    "Heildarverð á fyrsta farrými: " + Double.toString(item.getTotalBusinessPrice());
            int length = item.getFlightCount();
            for (int i = 0; i < length; i++) {
                Flight f = item.getFlight(i);
                s = s + "\n\n"  + "Frá: " + f.getDepLocation() + "  Til: " + f.getArrLocation() + "\n" +
                        "Brottför: " + f.getDepDate().getTime() + "\n" + "Lending: " + f.getArrDate().getTime();
            }
            Pane sub = new Pane();

            return s;
        }

        @Override
        public ConnectedFlight getFlights(final ConnectedFlight item) {
            return item;
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchInputController.setController(this);

        searchEngine = new SearchEngine();

        long initTime = 1551420000000L; // 1st of march 2019 - 00:00
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(initTime);
        searchEngine.setDepDate(cal);
        /*
        searchEngine.setEconomy(true);
        searchEngine.setPassangerCount(1);
        searchEngine.setFlexibility(0);
        searchEngine.setDepLocation("RVK");
        searchEngine.setArrLocation("AEY");
        /*
        try {
            currentResult = searchEngine.findFlightCourse();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        ConnectedFlight cFlight = currentResult.findCheapestFlight(true);
        */
        //displayResults();

        flightList.setContentProvider(contentProvider);
    }

    /**
     * Display the current results
     */
    public void displayResults() {
        search();
        /*
        ArrayList<String> flightInfo = new ArrayList<String>();
        int length = currentResult.getResultCount();
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            flightInfo.add("Frá " + currentResult.getConnectedFlight(i).getFirstDep() + " til " + currentResult.getConnectedFlight(i).getLastArr() +
                    " þann " + currentResult.getConnectedFlight(i).getFlight(0).getDepDate().getTime());
        }
        ObservableList<String> fList = FXCollections.<String>observableArrayList();
        fList.addAll(flightInfo);
        flightList.setItems(fList);
        */

        ArrayList<ConnectedFlight> connectedFlights = new ArrayList<ConnectedFlight>();
        int length = currentResult.getResultCount();
        for (int i = 0; i < length; i++) {
            connectedFlights.add(currentResult.getConnectedFlight(i));
        }
        ObservableList<ConnectedFlight> fList = FXCollections.<ConnectedFlight>observableArrayList();
        fList.addAll(connectedFlights);
        flightList.setItems(fList);

        clearResults();
    }

    public void clearResults() {
        searchEngine = new SearchEngine();
    }

    private void search() {
        try {
            currentResult = searchEngine.findFlightCourse();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the departure location
     * @param loc
     */
    public void setDepLocation(String loc) {
        searchEngine.setDepLocation(loc);
    }

    /**
     * Set the arrival location
     * @param loc
     */
    public void setArrLocation(String loc) {
        searchEngine.setArrLocation(loc);
    }

    /**
     * Set the flexibility of the search
     * @param n
     */
    public void setFlex(int n) {
        searchEngine.setFlexibility(n);
    }

    /**
     * Set whether the search is for business class or economy.
     * b == true: business, b == false: economy
     * @param b
     */
    public void setClass(boolean b) {
        searchEngine.setEconomy(!b);
    }

    /**
     * Set how many spots in each flight we are looking for
     * @param n
     */
    public void setPassAmount(int n) {
        searchEngine.setPassangerCount(n);
    }

    /**
     * Set the date of the flight that is searched for
     * @param cal
     */
    public void setDate(Calendar cal) {
        searchEngine.setDepDate(cal);
    }
}
