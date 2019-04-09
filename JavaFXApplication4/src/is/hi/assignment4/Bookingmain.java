/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package is.hi.assignment4;
import java.util.Calendar;
import modeldaytour.BookingD;
import modeldaytour.Customer;
import modelflight.ConnectedBooking;
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
    private ConnectedBooking flightBooking1;    
    private ConnectedBooking flightBooking2;
    private BookingD dtBooking;
    private Traveller traveller;
    private Customer customer;
    //hlutir sem við fáum frá interface-inu
    private String costumerID;
    private String seatnumber;
    private Integer baggage;
    private String firstName;
    private String lastName;
    private String email;
    private Calendar birthday;
    private String passportNumber;
    private String phoneNumber;
    private String nationality;
    



    public Bookingmain(Package pack){
        this.pack =pack;
    }
    
    public void makeBookings(){
        this.flightBooking1 = new ConnectedBooking(this.pack.f1);
        this.flightBooking2 = new ConnectedBooking(this.pack.f2);
        this.traveller= new Traveller(this.firstName, this.birthday, this.nationality,this.passportNumber, this.email, this.phoneNumber);
    }

    
    public void setCostumerIDsetter(String ID){
        this.costumerID=ID;
    }
    public void setSeatNumber(String seat){
        this.seatnumber=seat;
    }
    public void setBaggage(Integer baggage){
        this.baggage=baggage;
    }
    public void setFirstName(String name){
        this.firstName=name;
    }
    public void setLastName(String name){
        this.lastName=name;
    }
    public void setemail(String email){
        this.email=email;
    }
    public void setBirthDay(Calendar birthd){
        this.birthday=birthd;
    }
    public void setPassportNumber(String pass){
        this.passportNumber=pass;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public void setNationality(String nationality){
        this.nationality=nationality;
    }
}
