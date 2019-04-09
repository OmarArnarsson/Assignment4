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
import javafx.scene.Node;
import javafx.scene.Scene;
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

        
    private Bookingmain book;
    private Scene secondScene;
    private Stage newWindow;
    private Package pack;
    private int passangerCount;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
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
    private void buttonHandler(ActionEvent event) {
  
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
        System.out.print(this.passangerCount);
        if(this.passangerCount > 0){
            createBook(this.pack, this.passangerCount);
        }
        
       
    }
    
}
