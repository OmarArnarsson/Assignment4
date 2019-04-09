package controllerflight;

import modelflight.Traveller;
import modelflight.Flight;
import modelflight.BookingF;
import modelflight.ConnectedBooking;
import modelflight.ConnectedFlight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.nio.charset.*;


public class BookingManager {

    private ConnectedBooking connectedBooking;
    private Traveller passenger;
    private String bookingNumber;
    private DatabaseController dbController;
    private ConnectedFlight connectedFlight;



    public BookingManager(){
        this.dbController = new DatabaseController();
    }

    public void setFlights(ConnectedFlight cf){
        connectedFlight = cf;
        connectedBooking = new ConnectedBooking(cf);
    }
    public ConnectedFlight getFlightlist(){
        return connectedFlight;
    }

    public ArrayList<String> getBookedSeats(Flight flight) throws SQLException {
        String num = flight.getFlightNumber();

        ResultSet rs = dbController.getBookedSeats(num);
        ArrayList<String> takenSeats = new ArrayList<String>();
        while(rs.next()){
           takenSeats.add(rs.getString("seatNum"));
        }
        return takenSeats;
    }

    public ConnectedBooking getConnBooking() {
        return connectedBooking;
    }

    public void getConnBooking(String bookingNum){
        ResultSet rs = dbController.getConnectedBooking(bookingNum);
        // Skoða hvort lengdin sé stærri en 1 (Má ekki koma út fleiri en eitt connBooking)
    /*
        // Þarf að útfæra
        while(rs.next()) {
            /*
            connectedBooking.add(new Booking(new Flight(rs.getString("flightNumber"),rs.getString("depLocation"),
                    rs.getString("arrLocation"),rs.getLong("depDate"),
                    rs.getLong("arrDate"),rs.getString("aircraftType"),
                    rs.getDouble("economyPrice"),rs.getDouble("businessPrice"))));

        }
    */

    }

    public void changeBooking(BookingF _newBooking, BookingF _oldBooking){

    }
    public void changeSeat(Boolean economy, int row, String seat, BookingF booking ){

    }
    public void changeBaggage(int bagCount, BookingF booking){
        booking.setBaggage(bagCount);
    }

    //Gera traveller hlut hérna eða í þínum viewController harri
    public void makeTraveller(String name, Calendar birthday, String nationality, String passportNumber, String email, String phone){
        passenger = new Traveller(name, birthday,nationality,passportNumber,email,phone);
        addTraveller(passenger);
    }

    public void addTraveller(Traveller traveller){
        passenger = traveller;
        connectedBooking.addTraveller(traveller);
    }

    public Traveller getTraveller() {
        return passenger;
    }

    //Skoða hvort valið sætanumer sé business?
    public Double calcSeatPrice(String _seatnr){
        //gera ehv heimskulegt
        return 1000.0; // ??
    }

    //Setja inn sætaNR og flug og fjöldi bags
    public void makeBooking(String seatnum, Flight flight, int numBaggage){
        BookingF booking = new BookingF(flight, seatnum, numBaggage, flight.getPrice(true));
        addBooking(booking);
    }
    public void addBooking(BookingF booking){
        connectedBooking.addBooking(booking);
    }

    public void confirmBooking(){
        makeBookingID(); // Búa til bookingID
        dbController.addBooking(connectedBooking);
    }
    // svo það þurfi ekki að búa til nýjan hlut af BookingManager fyrir annan traveller
    public void resetBooking(){
        connectedBooking = new ConnectedBooking(connectedFlight);
    }
    public void makeBookingID(){
        bookingNumber = getAlphaNumericString(7); //lengd 7
        System.out.println("BókunarNR fyrir " + passenger.getName() + " er " + bookingNumber);
    }

    //Random string generator
    public static String getAlphaNumericString(int n) {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {

                r.append(ch);
                n--;
            }
        }
        // return the resultant string
        return r.toString();
    }

    public String getBookingNumber() {
        return bookingNumber;
    }
}
