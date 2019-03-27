package is.hi.assignment4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author stein
 */
public class InterfaceController implements Initializable {

    @FXML
    private AnchorPane Grunnur;
    @FXML
    private Button button;
    @FXML
    private TextField leit;
    @FXML
    private DatePicker dagsetning;
    @FXML
    private TextField leit1;
    @FXML
    private DatePicker dagsetning1;
    
    
    private SearchController sc;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sc = new SearchController();
 
        
    }

    @FXML
    private void buttonHandler(ActionEvent event) {
        sc.processFlight(dagsetning.getValue(), dagsetning1.getValue(),1000);
    }

    @FXML
    private void leitHandler(ActionEvent event) {
        System.out.print(leit.getText());
        
    }


    @FXML
    private void dateHandler(MouseEvent event) {
        System.out.print(dagsetning.getValue());
    }
    
}
