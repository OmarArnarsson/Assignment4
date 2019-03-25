/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLDocumentController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author stein
 */
public class java implements Initializable {

    @FXML
    private AnchorPane Grunnur;
    @FXML
    private Button button;
    @FXML
    private TextField leit;
    @FXML
    private DatePicker dagsetning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonHandler(ActionEvent event) {
    }

    @FXML
    private void leitHandler(ActionEvent event) {
    }

    @FXML
    private void dateHandler(ActionEvent event) {
    }
    
}
