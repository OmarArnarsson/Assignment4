/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.util.Calendar;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author omararnars
 */
public class PurchaseController implements Initializable {

    @FXML
    private TextField Fornafn;
    @FXML
    private TextField passportnum;
    @FXML
    private TextField Eftirnafn;
    @FXML
    private TextField email;
    @FXML
    private TextField kennitala;
    @FXML
    private TextField phone;
    @FXML
    private TextField baggage;
    @FXML
    private DatePicker birthday;
    
    @FXML
    private AnchorPane Anchor;
    @FXML
    private TextField land;

    //for demo
    private ArrayList<Bookingmain> bookings;
    private Bookingmain book;
    private Scene secondScene;
    private Stage newWindow;
    private Package pack;
    private int passangerCount;
    @FXML
    private Button panta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding fornafnValid = Bindings.createBooleanBinding(() -> {
            return this.Fornafn.getText().isEmpty();
        }, this.Fornafn.textProperty());
        BooleanBinding eftirnafnValid = Bindings.createBooleanBinding(() -> {
            return this.Eftirnafn.getText().isEmpty();
        }, this.Eftirnafn.textProperty());
        BooleanBinding baggageValid = Bindings.createBooleanBinding(() -> {
            return this.baggage.getText().isEmpty();
        }, this.baggage.textProperty());
        BooleanBinding birthDayValid = Bindings.createBooleanBinding(() -> {
            return this.birthday.getEditor().getText().isEmpty();
        }, this.birthday.getEditor().textProperty());
        BooleanBinding emailValid = Bindings.createBooleanBinding(() -> {
            return this.email.getText().isEmpty();
        }, this.email.textProperty());
        BooleanBinding kennitalaValid = Bindings.createBooleanBinding(() -> {
            return this.kennitala.getText().isEmpty();
        }, this.kennitala.textProperty());
        BooleanBinding landValid = Bindings.createBooleanBinding(() -> {
            return this.land.getText().isEmpty();
        }, this.land.textProperty());
        BooleanBinding passportValid = Bindings.createBooleanBinding(() -> {
            return this.passportnum.getText().isEmpty();
        }, this.passportnum.textProperty());

        panta.disableProperty().bind(fornafnValid.or(eftirnafnValid.or(baggageValid.or(birthDayValid.or(emailValid.or(kennitalaValid.or(landValid.or(passportValid))))))));
        
        this.bookings = new ArrayList<Bookingmain>();
    }    
  
    
    public void createBook(Package pack, int count){
        
        this.pack = pack;
        this.passangerCount = count;
        this.book = new Bookingmain(pack);
        Anchor.setVisible(true);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(Anchor);
 
        secondScene = new Scene(secondaryLayout, 500, 300);
 
                // New window (Stage)
        newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);
        newWindow.show();
        
     
    }

 
    @FXML
    private void buttonHandler(ActionEvent event) throws SQLException {
  
        this.book.setCostumerIDsetter(kennitala.getText());
        this.book.setSeatNumber("");
        this.book.setBaggage(Integer.parseInt(baggage.getText()));
        this.book.setFirstName(Fornafn.getText());
        this.book.setLastName(Eftirnafn.getText());
        this.book.setemail(email.getText());

        LocalDate leit = birthday.getValue();
        int dagur = leit.getDayOfMonth();
        int man = leit.getMonthValue();
        int ar = leit.getYear();
    
        Calendar afmaeli = Calendar.getInstance();
        afmaeli.set(ar, man, dagur);
    
        this.book.setBirthDay(afmaeli);
        this.book.setNationality(land.getText());
        this.book.setPassportNumber(passportnum.getText());
        this.book.setPhoneNumber(phone.getText());
        this.book.makeBookings();
        newWindow.hide();
        this.passangerCount--;
        this.bookings.add(this.book);
         
        
        this.cleanTextFields();
        if(this.passangerCount > 0){   
            
            createBook(this.pack, this.passangerCount);

        }    
        
        if(this.passangerCount == 0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Staðfesting");
            alert.setHeaderText("Upplýsingar um pöntun");
            String info="";
            for(int i=0; i<this.bookings.size(); i++){
                info+= "\n" + this.bookings.get(i).getInfo()+"\n"+
                        "------------------------------------ \n";
            }
            alert.setContentText(info);
            alert.showAndWait();
        }
       
    }
    
   
    
    public void cleanTextFields(){
       
        this.Fornafn.setText("");
        this.passportnum.setText("");
        this.Eftirnafn.setText("");
        this.email.setText("");
        this.kennitala.setText("");
        this.phone.setText("");
        this.baggage.setText("");
        this.birthday.setValue(null);
        this.land.setText("");
    }
}
