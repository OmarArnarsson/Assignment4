package controllerdaytour;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modeldaytour.Customer;
import modeldaytour.Payment;
import modeldaytour.Tour;
import viewdaytour.Main;

import java.util.LinkedList;

public class searchController {
    public Slider chosenPrice;
    public CheckBox disabilityFriendly;
    public CheckBox choosePrivat;
    public CheckBox chooseTourguide;
    public DatePicker startTrip;
    public ChoiceBox chosenLocation;
    public Button fintMyTourButton;
    public Slider chosenGroupSize;
    public ChoiceBox chosenTourType;


    public void getFilters(ActionEvent actionEvent) throws Exception {
        // using the customer created in main - always using the same customer
        Customer customer = Main.customer;

        customer.filter.setTimeStart(startTrip.getValue().toString());
        customer.filter.setPrice(chosenPrice.getValue());
        customer.filter.setAccessibility(disabilityFriendly.isSelected());
        customer.filter.setGuidedTour(chooseTourguide.isSelected());
        customer.filter.setPrivateTour(choosePrivat.isSelected());
        customer.filter.setLocation(chosenLocation.getValue());
        customer.filter.setGroupSize(chosenGroupSize.getValue());
        customer.filter.setTourType(chosenTourType.getValue().toString());

        // using db created in Main
        LinkedList<Tour> result = Main.db.selectTours(customer.filter);

        // using tourController created in Main
        Main.tourController.result = result;

        /** to see results form search, remove later*/
        if (result.size() > 0) {
            Tour selected = result.getFirst();
            System.out.println("Name of first trip found: " + selected.getTourName());
            System.out.println("Id of first trip found: " + selected.getId());
            System.out.println("Number of seats left in first trip found: " + selected.getSeatsLeft());
        } else {
            System.out.println("No seats left");
        }

        setupNextPage();
    }

    // activated on window onload
    @FXML
    protected void initialize(){
        // initialize the choiceBox chosenLocation
        chosenLocation.setItems(FXCollections.observableArrayList(
                "Reykjavík", "Akureyri"));

        chosenTourType.setItems(FXCollections.observableArrayList(
                "Bus Tour", "Car Ride", "Adventure", "Bar Crawl", "Food Tour", "Jeep Ride", "Beer Tour"));
    }

    private void setupNextPage() throws Exception {
       /* Parent resultView = FXMLLoader.load(getClass().getResource("view/searchResults.fxml"));
        Scene sceneResult = new Scene(resultView);
        Stage window = new Stage();
        window.setScene(sceneResult);
        window.show();*/
    }

}
