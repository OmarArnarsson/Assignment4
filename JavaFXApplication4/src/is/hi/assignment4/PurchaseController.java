/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
        // TODO
    }    
    
}
