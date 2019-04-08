package controllerdaytour;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class searchResultsController extends AnchorPane {
    public Label tourName;
    public Label tourType;
    public Label location;
    public Label about;
    public Label date;
    public Label timeStart;
    public Label timeFinish;
    public Label guidedTour;
    public Label accessibleTour;
    public Label privateTour;
    public Label price;
    public ListView resultList;
    public AnchorPane anchorPaneResults;


    public void showSelectedTour(ContextMenuEvent contextMenuEvent) {
        System.out.print("hello click on list view");
    }

    public static void setUp() throws IOException {

    }
}
