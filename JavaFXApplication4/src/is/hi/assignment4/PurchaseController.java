/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;


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
    
    
    private Bookingmain book;
    @FXML
    private AnchorPane Anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    /*    @FXML*/
    /*    private void buttonHandler(ActionEvent event) {
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
    this.book.setPassportNumber(passportnum.getText());
    this.book.setPhoneNumber(phone.getText());
    this.book.makeBookings();
    }*/
    public void createBook(Package pack){
        DialogPane p= new DialogPane();
        Anchor.setVisible(true);
        p.setContent(Anchor);
        this.book= new Bookingmain(pack);
        System.out.print(pack.price);
    }
    
}
