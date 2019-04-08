package viewdaytour;

import controllerdaytour.DatabaseManager;
import controllerdaytour.TourController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modeldaytour.Customer;

public class Main extends Application {

    // búum til eitt instance af db man og tourcontroller sem hægt er að nálgast
    // annarsstaðar í kerfinu sjá dæmi hér fyrir neðan
    public static DatabaseManager db = new DatabaseManager();
    public static TourController tourController = new TourController(db);
    public static Customer customer = new Customer();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Day tours");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
