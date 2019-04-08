/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package is.hi.assignment4;
import java.util.Calendar;
import modeldaytour.BookingD;
import modeldaytour.Customer;
import  modelflight.BookingF;
import modelflight.Traveller;
/**
 *
 * @author Högni Freyr Gunnarsson hfg7@hi.is
 * @date
 * Háskóli Íslands
 */
public class Bookingmain {
    
   // private BookingID
    private Package pack;
    private BookingF flightBooking1;    
    private BookingF flightBooking2;
    private BookingD dtBooking;
    private Traveller traveller;
    private Customer customer;
    //hlutir sem við fáum frá interface-inu
    private Integer costumerID;
    private String seatnumber;
    private Integer baggage;
    private String firstName;
    private String lastName;
    private String email;
    private Calendar birthday;
    private String passportNumber;
    private String phoneNumber;
    



    public Bookingmain(Package pack){
        this.pack =pack;
    }
    
    public void makeBookings(){
        this.flightBooking1 = new BookingF(this.pack.f1, this.seatnumber, this.baggage, this.pack.f1.getTotalEconomyPrice());
    }

}
