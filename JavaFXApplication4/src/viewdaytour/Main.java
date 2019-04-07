package viewdaytour;

import controllerdaytour.DatabaseManager;
import controllerdaytour.TourController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modeldaytour.Tour;
import modeldaytour.TourFilter;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Main extends Application {

    // búum til eitt instance af db man og tourcontroller sem hægt er að nálgast
    // annarsstaðar í kerfinu sjá dæmi hér fyrir neðan
    public static DatabaseManager db = new DatabaseManager();
    public static TourController tourController = new TourController(db);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World og sigga");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        // þetta er til þess að prófa gagnagrunninn hérna fyrir neðan ------------------
        TourFilter filter;

        filter = new TourFilter(25000, "2","Akureyri",
                "Bus ride", "06/09/19", true, false,
                false);


        LinkedList<Tour> result = new LinkedList<Tour>();

        db.removeSeats(0, 9, 18);

        result = db.selectTours(filter);


        if (result.size() > 0) {
            Tour selected = result.getFirst();
            System.out.println("Name of first trip found: " + selected.getTourName());
            System.out.println("Id of first trip found: " + selected.getId());
            System.out.println("Number of seats left in first trip found: " + selected.getSeatsLeft());
            System.out.println("Number of trips found: " + tourController.getNumTripsFound());
        } else {
            System.out.println("No seats left");
        }



        // stoppar hér -----------------------------------------------------------------
    }


    public static void main(String[] args) {
        launch(args);
    }
}
