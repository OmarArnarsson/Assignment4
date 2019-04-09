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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* Bookingmain book = new Bookingmain();
        book.setCostumerIDsetter(kennitala.getText());
        book.setSeatNumber("");
        book.setBaggage(Integer.parseInt(baggage.getText()));
        book.setFirstName(Fornafn.getText());
        book.setLastName(Eftirnafn.getText());
        book.setemail(email.getText());
        
        LocalDate leit = birthday.getValue();
        int dagur = leit.getDayOfMonth();
        int man = leit.getMonthValue();
        int ar = leit.getYear();
        
        Calendar afmaeli = Calendar.getInstance();
        afmaeli.set(ar, man, dagur);
        
        book.setBirthDay(afmaeli);
        book.setPassportNumber(passportnum.getText());
        book.setPhoneNumber(phone.getText());*/
        
    }    
    
}
