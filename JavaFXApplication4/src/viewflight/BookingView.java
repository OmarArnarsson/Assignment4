package viewflight;

import controllerflight.BookingManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingView extends Application {

    Stage window;
    Scene passanger, seats, book;

    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PassengerView.fxml"));

        // Dummy
        BookingManager bookingManager = new BookingManager();

        PassengerViewController passangerViewController = new PassengerViewController();
        passangerViewController.setBooker(bookingManager);
        passangerViewController.setStage(this.primaryStage);

        loader.setController(passangerViewController);

        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Passanger information");
        primaryStage.show();
    }
}
