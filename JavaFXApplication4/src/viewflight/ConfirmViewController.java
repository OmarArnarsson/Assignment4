package viewflight;

import controllerflight.BookingManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelflight.Booking;
import modelflight.ConnectedBooking;

import java.util.Set;

public class ConfirmViewController {

    Stage primaryStage;

    BookingManager bookingManager;

    @FXML
    private Text fullName;
    @FXML
    private Text birthdate;
    @FXML
    private Text phoneNumber;
    @FXML
    private Text email;
    @FXML
    private Text nationality;
    @FXML
    private ListView<Booking> listView;

    ObservableList<Booking> observableList = FXCollections.observableArrayList();

    ConfirmViewController() {

    }

    @FXML
    private void initialize() {
        fullName.setText(bookingManager.getTraveller().getName());
        birthdate.setText(bookingManager.getTraveller().getBirthday().getTime().toGMTString());
        phoneNumber.setText(bookingManager.getTraveller().getPhone());
        email.setText(bookingManager.getTraveller().getEmail());
        nationality.setText(bookingManager.getTraveller().getNationality());

        observableList.addAll(bookingManager.getConnBooking().getBookingList());

        listView.setItems(observableList);

        System.out.println(observableList.size());

        listView.setCellFactory(param -> new ListCell<Booking>() {
            @Override
            protected void updateItem(Booking item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    FlightViewItem flightViewItem = new FlightViewItem();
                    flightViewItem.setInfo(item);
                    setGraphic(flightViewItem.getBox());
                }
            }
        });

    }


    public void setBooker(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    public void confirmBooking() {
        bookingManager.confirmBooking();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your flight has been booked \n Your booking number is: " + bookingManager.getBookingNumber());
        alert.showAndWait();
        primaryStage.close();
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}