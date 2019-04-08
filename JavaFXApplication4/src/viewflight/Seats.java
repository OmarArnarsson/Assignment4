package viewflight;

import controllerflight.BookingManager;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelflight.BookingF;
import modelflight.ConnectedBooking;
import modelflight.ConnectedFlight;
import modelflight.Flight;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Seats extends Application {
    String bombEcon = "34x\n34x\n34x\n\n34x\n34x\n34x\n";
    String bombBusi = "8x\n8x\n\n8x\n8x\n";

    BookingManager bookingManager;
    Stage primaryStage;

    ReservedSeatList reservedSeatList;

    int baggage;

    String[] warp = new String[]{"A","B","C","D","E","F"};

    public Seats(BookingManager bookingManager, Stage primaryStage, int baggage) {
        this.bookingManager = bookingManager;
        this.primaryStage = primaryStage;
        this.baggage = baggage;
    }


    static class SeatOnFlight {

        Seat pickedSeat;
        Flight flight;

        public Seat getPickedSeat() {
            return pickedSeat;
        }

        public void setPickedSeat(Seat pickedSeat) {
            this.pickedSeat = pickedSeat;
        }

        public Flight getFlight() {
            return flight;
        }

        public void setFlight(Flight flight) {
            this.flight = flight;
        }
    }

    class ReservedSeatList {
        ArrayList<Flight> flightList;
        ArrayList<Seat> seatList;

        public ReservedSeatList(ConnectedFlight flightList) {

            this.flightList = new ArrayList<Flight>();
            this.seatList = new ArrayList<Seat>();

            for(int i = 0; i < flightList.getFlightCount(); i++) {
                this.flightList.add(flightList.getFlight(i));
                this.seatList.add(new Seat());
            }
        }

        public Seat getSeatForFlight(Flight flight) {
            System.out.println(flight);
            System.out.println(this.flightList.contains(flight));
            System.out.println(this.flightList.indexOf(flight));
            return this.seatList.get(this.flightList.indexOf(flight));
        }

        public void setSeatForFlight(Seat seat, Flight flight) {
            seatList.set(this.flightList.indexOf(flight),seat);
        }

        public void getCount() {
            System.out.println(flightList.size() + " og " + seatList.size());
        }
    }

    static class Seat extends Group {
        Color freeColor = Color.rgb(30, 250, 40);
        Color reservedColor = Color.rgb(170, 40,  40);
        Color bookedColor = Color.rgb(100,100,100);

        boolean iAmReserved = false;

        Circle pillow;
        String no;
        Flight flight;
        ReservedSeatList reservedSeatList;

        boolean isDummySeat = false;

        public Circle getPillow() {
            return pillow;
        }

        public Seat() {
            Circle pillow = new Circle(12);
            this.pillow = pillow;
            this.isDummySeat = true;
        }

        public Seat(String no, boolean taken, Flight flight, ReservedSeatList reservedSeatList) {
            this.no = no;
            this.flight = flight;
            this.reservedSeatList = reservedSeatList;

            reservedSeatList.getCount();

            Circle pillow = new Circle(12);

            this.pillow = pillow;

            if(taken)
                pillow.setFill(bookedColor);
            else
                pillow.setFill(freeColor);
            pillow.setStrokeWidth(1);
            pillow.setStroke(Color.rgb(30, 40, 40));
            getChildren().add(pillow);

            Text lable = new Text(""+no);
            lable.setFont(lable.getFont().font(7));
            lable.setTextAlignment(TextAlignment.CENTER);
            lable.setTextOrigin(VPos.CENTER);
            lable.setLayoutX(-lable.getLayoutBounds().getWidth()/2);
            getChildren().add(lable);

            setOnMouseClicked(m -> {
                if(taken)
                    return;
                this.setReserved();
                reservedSeatList.getSeatForFlight(flight).setOpen();
                reservedSeatList.setSeatForFlight(this, flight);
            });
        }

        public void setOpen() {
            pillow.setFill(freeColor);
        }

        public void setReserved() {
            pillow.setFill(reservedColor);
        }

        public boolean isDummySeat() {
            return isDummySeat;
        }

        static double width() {
            return 26;
        }

        static double height() {
            return 36;
        }
    }
    Pane theater1(Pane pane, String theater, Flight flight, ReservedSeatList reservedSeatList) throws SQLException {

        ArrayList<String> takenSeats = bookingManager.getBookedSeats(flight);

        takenSeats.add("A13");
        takenSeats.add("C22");

        Collections.sort(takenSeats);

        double x = 20;
        double y = 40 + Seat.height()*(8 - theater.length() + theater.replaceAll("\n","").length())/2;
        int no = 1;
        double rowCount = 0;
        int rowCounter = 0;
        for (String row : theater.split("\n")) {
            int count = 0;
            if(row.length() > 0) {
                rowCount =  Double.parseDouble(row.substring(0,row.length()-1));
            }
            System.out.println(row + " röð og teljari segir " + rowCounter);
            x = 20 + Seat.width()*(34 - rowCount)/2;
            for (int c : row.toCharArray()) {
                switch (c) {
                    case 'x':
                        while (count-- > 0) {
                            boolean taken = takenSeats.contains(warp[rowCounter] + no);
                            Seat seat = new Seat(warp[rowCounter] + no, taken, flight,reservedSeatList );
                            seat.setLayoutX(x);
                            x+= Seat.width();
                            seat.setLayoutY(y);
                            pane.getChildren().add(seat);
                            no++;
                        }
                        count = 0;
                        no=1;
                        break;
                    case '0': case '1': case '2': case '3': case '4': case '5': case'6': case '7': case '8': case '9':
                        count = 10*count + (c-'0');
                        break;
                    case '_':
                        x+= Seat.width();
                        break;
                    case '.':
                        x+= Seat.width()/2;
                        break;
                    default: System.out.println("Unknown char: '"+(char)c+"'");
                }

            }
            if(row.length() > 0) {
                rowCounter++;
            }
            y += Seat.height();
            count = 0;
            x = 20;
        }
        return pane;
    }

    LinkedList<Node> myPages = new LinkedList<Node>();
    void addTab(String label, Region node) {
        myPages.add(node);
        node.setBackground(new Background(new BackgroundFill(Color.rgb(200, 170, 200), new CornerRadii(0), new Insets(0))));
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Background of Panes");

        BorderPane border = new BorderPane();
        Pagination pages = new Pagination();
        Scene scene = new Scene(border,900,400,Color.WHITE);
        primaryStage.setScene(scene);

        HBox topHBox = new HBox();
        Text titleText = new Text();
        titleText.setText("Flug hér");
        topHBox.getChildren().add(titleText);
        topHBox.setAlignment(Pos.CENTER);
        border.setTop(topHBox);

        ConnectedFlight flightList = bookingManager.getFlightlist();

        System.out.println(flightList.getFlightCount());

        reservedSeatList = new ReservedSeatList(flightList);

        for(int i = 0; i < flightList.getFlightCount(); i++) {
            Flight flightToDraw = flightList.getFlight(i);
            addTab(flightToDraw.toString(), theater1(new Pane(), bombEcon, flightToDraw, reservedSeatList));
        }

        pages.setPageCount(myPages.size());
        pages.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                titleText.setText("" + param);
                return myPages.get(param);
            }
        });

        Button btnBottom = new Button("Bottom");
        btnBottom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // passa að það séu sæti valin fyrir hvert flug
                for(int i = 0; i < flightList.getFlightCount(); i++) {
                    if(reservedSeatList.getSeatForFlight(flightList.getFlight(i)).isDummySeat()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                "Seat not picked for all flights");
                        alert.showAndWait();
                        return;
                    }
                }
                // búa til allar bókanir og setja í bookingmanager
                for(int i = 0; i < flightList.getFlightCount(); i++) {
                    bookingManager.makeBooking(reservedSeatList.getSeatForFlight(flightList.getFlight(i)).no,
                            flightList.getFlight(i),baggage);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmView.fxml"));

                ConfirmViewController confirmViewController = new ConfirmViewController();
                confirmViewController.setBooker(bookingManager);
                confirmViewController.setStage(primaryStage);

                loader.setController(confirmViewController);

                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);

                primaryStage.setScene(scene);
                primaryStage.setTitle("Confirm booking");
                primaryStage.show();
            }
        });
        HBox bottomHBox = new HBox();
        border.setBottom(bottomHBox);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.getChildren().add(btnBottom);

        border.setCenter(pages);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}