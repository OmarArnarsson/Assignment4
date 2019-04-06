package view;


import controller.BookingManager;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Traveller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.regex.Pattern;


public class PassengerViewController {

    Stage primaryStage;

    BookingManager bookingManager;


    @FXML
    private TextField fullName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField nationality;
    @FXML
    private TextField baggage;
    @FXML
    private DatePicker birthdate;
    @FXML
    private Button seatButton;


    public PassengerViewController() {

    }

    @FXML
    private void initialize() {

        fullName.setText("");
        phoneNumber.setText("");
        email.setText("");
        nationality.setText("");
        birthdate.setValue(LocalDate.now());
        baggage.setText("");
    }

    @FXML
    private void pickSeat(ActionEvent event) throws Exception {

        if(!verifyName()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error in name field");
            alert.showAndWait();
            return;
        }
        if(!verifyPhone()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error in phone field");
            alert.showAndWait();
            return;
        }
        if(!verifyEmail()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error in email field");
            alert.showAndWait();
            return;
        }
        if(!verifyNatioanlity()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error in nationality field");
            alert.showAndWait();
            return;
        }
        if(!verifyBirthdate()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error in birth date field");
            alert.showAndWait();
            return;
        }
        if(!verifyBaggage()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error in baggage field");
            alert.showAndWait();
            return;
        }
        if(Integer.parseInt(baggage.getText()) >= 5) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Maximum baggage is 5");
            alert.showAndWait();
        }

        // Búa til nýjan Traveller
        Traveller traveller = new Traveller();

        traveller.setName(fullName.getText());
        traveller.setPhone(phoneNumber.getText());
        traveller.setEmail(email.getText());
        traveller.setNationality(nationality.getText());

        Calendar birthdateCalendar = Calendar.getInstance();

        String st = birthdate.getValue().toString();
        birthdateCalendar.set(Integer.parseInt(st.substring(0,4)), Integer.parseInt(st.substring(5,7))-1,
                Integer.parseInt(st.substring(8,10)), 0, 0);

        traveller.setBirthday(birthdateCalendar);

        bookingManager.addTraveller(traveller);

        System.out.println(bookingManager.getFlightlist().toString());

        Seats seats = new Seats(bookingManager, primaryStage, Integer.parseInt(baggage.getText()));

        seats.start(this.primaryStage);
    }



    private boolean verifyName() {

        String fullNameCheck = fullName.getText();

        return !fullNameCheck.isEmpty();
    }

    private boolean verifyPhone() {

        String phoneNumberCheck = phoneNumber.getText();

        if(phoneNumberCheck.length() < 3) // Country code is atleast + and 2 numbers
            return false;

        return phoneNumberCheck.substring(0, 1).equals("+") || phoneNumberCheck.substring(0, 2).equals("00");
    }

    private boolean verifyEmail() {

        String emailCheck = email.getText();
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emailCheck);
        return m.matches();
    }

    private boolean verifyNatioanlity() {

        String nationalityCheck = nationality.getText();

        return !nationalityCheck.isEmpty();
    }

    private boolean verifyBirthdate() {

        LocalDate birthdateCheck = birthdate.getValue();
        LocalDate currentdateCheck = LocalDate.now();

        return birthdateCheck.toEpochDay() < currentdateCheck.toEpochDay();
    }

    private boolean verifyBaggage() {

        String baggageCheck = baggage.getText();
        try {
            Integer.parseInt(baggageCheck);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void setBooker(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}