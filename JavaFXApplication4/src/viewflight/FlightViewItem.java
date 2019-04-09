package viewflight;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelflight.BookingF;

import java.io.IOException;

public class FlightViewItem
{
    @FXML
    private VBox vBox;
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    public FlightViewItem()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListViewFlight.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(BookingF item)
    {
        String firstLine = "Frá: " + item.getFlight().getDepLocation() + " til: " + item.getFlight().getArrLocation() +
                " Kl.: " + item.getFlight().getDepDate().getTime();
        String secondLine = "Sæti " + item.getSeatNumber() + " á verði " + item.getPrice() + " með " + item.getBaggage() + " töskur";
        label1.setText(firstLine);
        label2.setText(secondLine);

    }

    public VBox getBox()
    {
        return vBox;
    }
}