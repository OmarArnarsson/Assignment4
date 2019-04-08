/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package is.hi.assignment4;
import modeldaytour.Booking;
import  modelflight.ConnectedBooking;

/**
 *
 * @author Högni Freyr Gunnarsson hfg7@hi.is
 * @date
 * Háskóli Íslands
 */
public class Bookingmain {
    
   // private BookingID
    private Package pack;
    private ConnectedBooking flightBooking1;    
    private ConnectedBooking flightBooking2;
    private Booking dtBooking;
    private boolean confirmation=false;
    
    public Bookingmain(Package pack){
        this.pack =pack;
    }
    
    public void makeBookings(){
        this.flightBooking1= new ConnectedBooking(this.pack.f1);
        this.flightBooking2= new ConnectedBooking(this.pack.f2);
        //this.dtBooking= new Booking(this.pack.d.getId(),this.pack.d.getPrice(),null,null,0);
    
    }

}
