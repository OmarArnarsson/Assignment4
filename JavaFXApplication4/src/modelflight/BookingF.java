package modelflight;

import java.util.*;
import java.nio.charset.*;

public class BookingF {

    private Flight flight;
    private String seatNumber;
    private Integer baggage;
    private Double price;
    private String bookingID;

    public BookingF(Flight _flight, String _seatNumber, Integer _baggage, Double _price ) {
        flight = _flight;
        price = _price;
        seatNumber = _seatNumber;
        baggage = _baggage;
        price = _price;
    }
    public BookingF() {
    }

    public void changePrice(Double newPrice) {
        this.price = newPrice;
    }

    /* Getters and setters */

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        //breyta verði ef sæti er business

    }

    public Integer getBaggage() {
        return baggage;
    }

    public void setBaggage(Integer baggage) {
        this.baggage = baggage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID() {
        bookingID = getAlphaNumericString(7);
    }


    //Random string generator
    public static String getAlphaNumericString(int n)
    {

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
}
