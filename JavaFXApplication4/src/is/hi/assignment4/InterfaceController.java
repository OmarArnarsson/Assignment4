package is.hi.assignment4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    @FXML
    private CheckBox check50;
    @FXML
    private CheckBox check100;
    @FXML
    private CheckBox check150;
    @FXML
    private CheckBox checkOver;
    @FXML
    private CheckBox checkMenning;
    @FXML
    private CheckBox checkAdventure;
    @FXML
    private CheckBox checkSkodun;
    @FXML
    private ListView<Package> listinn;
   
        
    private SearchController sc;
    private PackageList list;
 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sc = new SearchController();
        list = new PackageList();
        
    }

    @FXML
    private void buttonHandler(ActionEvent event) throws SQLException, CloneNotSupportedException {
        sc.processFlight(dagsetning.getValue(), dagsetning1.getValue(),1000);
        ArrayList<Package> a = sc.getResults();
    }

    @FXML
    private void leitHandler(ActionEvent event) {
        System.out.print(leit.getText());
        
        
        
    }


    @FXML
    private void dateHandler(MouseEvent event) {
        System.out.print(dagsetning.getValue());
    }

    @FXML
    private void check50Handler(ActionEvent event) {
        sc.setPriceRange(50000);
        this.check150.setDisable(true);
        this.check150.setDisable(true);
        this.check150.setDisable(true);
    }

    @FXML
    private void check100Handler(ActionEvent event) {
    }

    @FXML
    private void check150Handler(ActionEvent event) {
    }

    @FXML
    private void checkOverHandler(ActionEvent event) {
    }

    @FXML
    private void checkMenningHandler(ActionEvent event) {
        
    }

    @FXML
    private void checkAdventureHandler(ActionEvent event) {
    }

    @FXML
    private void checkSkodunHandler(ActionEvent event) {
    }
    
}
