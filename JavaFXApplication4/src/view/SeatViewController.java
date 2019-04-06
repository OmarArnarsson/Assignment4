package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class SeatViewController {

    Stage primaryStage;

    @FXML
    private Button confirmButton;

    SeatViewController() {

    }

    @FXML
    private void initialize() throws IOException {

    }

    public void setBooker() {

    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void confirmBooking(ActionEvent event) throws IOException {
        // Staðfesta öll gögnin séu rétt
        // Búa til nýjan Traveller
        // setja travellerinn í BookingManager hlutinn sem er í BookingView
        // Fara í SeatView sceneið
        //Alert alert = new Alert(Alert.AlertType.INFORMATION, "Delete ?");
        //alert.showAndWait();

        System.out.println("haha3");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmView.fxml"));

        SeatViewController seatViewController = new SeatViewController();
        seatViewController.setBooker();
        seatViewController.setStage(this.primaryStage);

        loader.setController(seatViewController);

        Parent root = loader.load();

        Scene scene = new Scene(root);

        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Confirm your booking");
        this.primaryStage.show();
    }
}
