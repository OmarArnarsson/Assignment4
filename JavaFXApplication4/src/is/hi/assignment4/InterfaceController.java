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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
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

        
    private int virkurIndex = -1;

    @FXML
    private Button purchaseButton;
    

    private SearchController sc;
    private PackageList list;
    @FXML
    private CheckBox almennt;
    @FXML
    private CheckBox saga;

    @FXML
    private CheckBox synaAllt;
    @FXML
    private CheckBox check30;
    @FXML
    private CheckBox check80;

    private PurchaseController purchaseController;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sc = new SearchController();
        list = new PackageList();
        this.listaVal();
        this.synaAllt.setSelected(true);
        
    }

    @FXML
    private void buttonHandler(ActionEvent event) throws SQLException, CloneNotSupportedException, Exception {
        
       
        sc.resetEngines();
        
      
        sc.setPriceRange(this.check30.isSelected(), this.check50.isSelected(), 
                         this.check80.isSelected(), this.checkOver.isSelected(),
                         this.synaAllt.isSelected());


        sc.setCount(Integer.parseInt(fjoldi.getText()));
        sc.setDepLoc(leit.getText());
        sc.setArrLoc(leit1.getText());
        sc.setDepLoc1(leit1.getText());
        sc.setArrLoc1(leit.getText());
        sc.setDepDate(dagsetning.getValue());
        sc.setHomeDate(dagsetning1.getValue());
        ArrayList<Package> pakkar = sc.getResults();
        
        sc.setList(pakkar);

        
        ObservableList<Package> l = FXCollections.observableArrayList(pakkar);
        
        listinn.setItems(l);
        
        listinn.setItems(l);
        listinn.setCellFactory(lv -> new ListCell<Package>() {
            @Override
            public void updateItem(Package item, boolean empty) {
                super.updateItem(item, empty);

                    if (empty) {
                        setText(null);
                    } else {
                        String text = item.toString() ; // get text from item
                        setText(text);
                    }
                
            }
            });
      
  
        
       
        
    }

    
  
    
    
    private void listaVal() {
       
        MultipleSelectionModel<Package> lsm1 = this.listinn.getSelectionModel();
        lsm1.selectedItemProperty().addListener(new ChangeListener<Package>(){
            @Override
            public void changed(ObservableValue<? extends Package> observable, Package oldValue, Package newValue) {
                virkurIndex=lsm1.getSelectedIndex();
                System.out.print(virkurIndex);
            }
        });
       
    }

    
    @FXML
    private void check50Handler(ActionEvent event) {
        if(this.check50.isSelected()){
            this.check30.setDisable(true);
            this.check80.setDisable(true);
            this.checkOver.setDisable(true);
            this.synaAllt.setDisable(true);
            this.synaAllt.setSelected(false);
               
        }
        else {
            this.synaAllt.setDisable(false);
            this.check30.setDisable(false);
            this.check80.setDisable(false);
            this.checkOver.setDisable(false);  
            this.synaAllt.setSelected(true);
        }
    }

   

    @FXML
    private void checkOverHandler(ActionEvent event) {
        if(this.checkOver.isSelected()){
            this.check30.setDisable(true);
            this.check50.setDisable(true);
            this.check80.setDisable(true);
            this.synaAllt.setDisable(true);
            this.synaAllt.setSelected(false);
            
        }
        else {
            this.synaAllt.setDisable(false);
            this.check30.setDisable(false);
            this.check50.setDisable(false);
            this.check80.setDisable(false);
            this.synaAllt.setSelected(true);
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

    @FXML
    private void purchaseHandler(ActionEvent event) {
         
        if(virkurIndex!=-1){
            System.out.print(sc.getResultNr(virkurIndex).price);
            this.purchaseController.createBook(sc.getResultNr(virkurIndex));
        }
    }

    @FXML
    private void almenntHandler(ActionEvent event) {
        if(almennt.isSelected()){
            sc.setEconomy(true);
            saga.setDisable(true);
        }
        else saga.setDisable(false);
    }

    @FXML
    private void sagaHandler(ActionEvent event) {
         if(saga.isSelected()){
            sc.setEconomy(false);
            almennt.setDisable(true);
        }
        else {
             almennt.setDisable(false);
             sc.setEconomy(true);
         }
    }

    @FXML
    private void synaAlltHandler(ActionEvent event) {
         if(!this.check30.isSelected() && !this.check50.isSelected() && !this.check80.isSelected() && !this.checkOver.isSelected()){
             this.synaAllt.setSelected(true);
         }
 
    }

    @FXML
    private void check30Handler(ActionEvent event) {
        if(this.check30.isSelected()){
            this.check50.setDisable(true);
            this.check80.setDisable(true);
            this.checkOver.setDisable(true);
            this.synaAllt.setDisable(true);
            this.synaAllt.setSelected(false);
               
        }
        else {
            this.synaAllt.setDisable(false);
            this.check50.setDisable(false);
            this.check80.setDisable(false);
            this.checkOver.setDisable(false);  
            this.synaAllt.setSelected(true);
        }
    }

    @FXML
    private void check80Handler(ActionEvent event) {
        if(this.check80.isSelected()){
            this.check30.setDisable(true);
            this.check50.setDisable(true);
            this.checkOver.setDisable(true);
            this.synaAllt.setDisable(true);
            this.synaAllt.setSelected(false);
               
        }
        else {
            this.synaAllt.setDisable(false);
            this.check50.setDisable(false);
            this.check30.setDisable(false);
            this.checkOver.setDisable(false); 
            this.synaAllt.setSelected(true);
        }
    }

   

}
