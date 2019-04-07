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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.ConnectedFlight;

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
    @FXML
    private TextField fjoldi;
        
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
        
        sc.setFlightEngine();

        sc.setCount(Integer.parseInt(fjoldi.getText()));
        sc.setDepLoc(leit.getText());
        sc.setArrLoc(leit1.getText());
        sc.setDepLoc1(leit1.getText());
        sc.setArrLoc1(leit.getText());
        sc.setDepDate(dagsetning.getValue());
        sc.setHomeDate(dagsetning1.getValue());
        

        
        ObservableList<Package> l = FXCollections.observableArrayList();

        
      /*  listinn.setItems(l);
        listinn.setCellFactory(lv -> new ListCell<Package>() {
            @Override
            public void updateItem(Package item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = "" ; // get text from item
                    setText(text);
                }
            }
        });
      */
  
        
    }


 

    @FXML
    private void check50Handler(ActionEvent event) {
        if(this.check50.isSelected()){
            this.check100.setDisable(true);
            this.check150.setDisable(true);
            this.checkOver.setDisable(true);
            sc.setPriceRange1(1);
        }
        else {
            this.check100.setDisable(false);
            this.check150.setDisable(false);
            this.checkOver.setDisable(false);
            sc.setPriceRange1(0);
        }
    }

    @FXML
    private void check100Handler(ActionEvent event) {
        if(this.check100.isSelected()){
            this.check50.setDisable(true);
            this.check150.setDisable(true);
            this.checkOver.setDisable(true);
            sc.setPriceRange1(2);
        }
        else {
            this.check50.setDisable(false);
            this.check150.setDisable(false);
            this.checkOver.setDisable(false);
            sc.setPriceRange1(0);
        }
    }

    @FXML
    private void check150Handler(ActionEvent event) {
        if(this.check150.isSelected()){
            this.check50.setDisable(true);
            this.check100.setDisable(true);
            this.checkOver.setDisable(true);
            sc.setPriceRange1(3);
        }
        else {
            this.check50.setDisable(false);
            this.check100.setDisable(false);
            this.checkOver.setDisable(false);
            sc.setPriceRange1(0);
        }
    }

    @FXML
    private void checkOverHandler(ActionEvent event) {
        if(this.checkOver.isSelected()){
            this.check50.setDisable(true);
            this.check100.setDisable(true);
            this.check150.setDisable(true);
            sc.setPriceRange1(4);
        }
        else {
            this.check50.setDisable(false);
            this.check100.setDisable(false);
            this.check150.setDisable(false);
            sc.setPriceRange1(4);
        }
    }

    @FXML
    private void checkMenningHandler(ActionEvent event) {
        if(this.checkMenning.isSelected())
            sc.setMenning(true);
        else 
            sc.setMenning(false);
    }

    @FXML
    private void checkAdventureHandler(ActionEvent event) {
        if(this.checkAdventure.isSelected())
            sc.setAdventure(true);
        else 
            sc.setAdventure(false);
    }

    @FXML
    private void checkSkodunHandler(ActionEvent event) {
        if(this.checkSkodun.isSelected())
            sc.setSkodunar(true);
        else 
            sc.setSkodunar(false);
    }
    
}
