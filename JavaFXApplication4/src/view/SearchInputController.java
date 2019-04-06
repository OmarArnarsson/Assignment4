package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.omg.CORBA.INTERNAL;


import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.spi.CalendarNameProvider;

public class SearchInputController implements Initializable {

    @FXML
    private SearchGUIController searchGUIController;
    @FXML
    private TextField arrText;
    @FXML
    private TextField depText;
    @FXML
    private TextField passAmount;
    @FXML
    private DatePicker date;
    @FXML
    private Button searchButton;
    @FXML
    private ChoiceBox<String> flex;
    @FXML
    private CheckBox business;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initChoice();
    }

    @FXML
    private void searchButtonHandler(ActionEvent action) {
        sendInfo();
        searchGUIController.displayResults();
    }

    /**
     * Connect the controllers
     * @param searchGUIController1
     */
    public void setController(SearchGUIController searchGUIController1) {
        searchGUIController = searchGUIController1;
    }

    /**
     * Initialize the choice box
     */
    private void initChoice() {
        ArrayList<String> choices = new ArrayList<String>();
        String arr[] = {"0", "1", "2", "3", "4", "5"};
        for (String s : arr) {
            choices.add(s);
        }
        ObservableList<String> flexChoices = FXCollections.<String>observableArrayList();
        flexChoices.addAll(choices);
        flex.setItems(flexChoices);
        flex.setValue("0");
    }

    /**
     * Send the information to the main controller
     */
    private void sendInfo() {
        int currentChoice = Integer.parseInt(flex.getSelectionModel().getSelectedItem());

        searchGUIController.setDepLocation(depText.getText());
        searchGUIController.setArrLocation(arrText.getText());
        searchGUIController.setPassAmount(Integer.parseInt(passAmount.getText()));
        searchGUIController.setFlex(currentChoice);
        searchGUIController.setClass(business.isSelected());

        System.out.println(date.getValue().toString());
        String st = date.getValue().toString();
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(st.substring(0,4)), Integer.parseInt(st.substring(5,7)), Integer.parseInt(st.substring(8,10)));
        cal.set(Integer.parseInt(st.substring(0,4)), Integer.parseInt(st.substring(5,7))-1, Integer.parseInt(st.substring(8,10)), 6, 0);
        System.out.println(cal.getTime());

        searchGUIController.setDate(cal);
    }
}
