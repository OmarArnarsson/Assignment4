/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package is.hi.assignment4;
import controllerflight.BookingManager;
import hotelStuff.HotelDAO;
import java.sql.SQLException;
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
    Package pack;
    private BookingManager flightBooking1;
    private BookingManager flightBooking2;
    private BookingD dtBooking;
    private Traveller traveller;
    private Customer customer;
    private HotelDAO hotelBooking;
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

    public void makeBookings() throws SQLException{
        this.traveller= new Traveller(this.firstName, this.birthday, this.nationality,this.passportNumber, this.email, this.phoneNumber);
        this.flightBooking1 = new BookingManager();
        this.flightBooking2 = new BookingManager();
        this.flightBooking1.setFlights(this.pack.getFlightTo());
        this.flightBooking2.setFlights(this.pack.getFlightBack());
        for(int i=0;i<flightBooking1.getFlightlist().getFlightCount();i++){
            flightBooking1.makeBooking(this.seatnumber,flightBooking1.getFlightlist().getFlight(i),this.baggage);
        }
        for(int i=0;i<flightBooking2.getFlightlist().getFlightCount();i++){
            flightBooking2.makeBooking(this.seatnumber,flightBooking2.getFlightlist().getFlight(i),this.baggage);
        }
        this.flightBooking1.addTraveller(this.traveller);
        this.flightBooking2.addTraveller(this.traveller);
        this.flightBooking1.confirmBooking();
        this.flightBooking2.confirmBooking();

        this.dtBooking=new BookingD(this.pack.getTour(),this.costumerID);
        this.customer = new Customer();
        this.customer.setBooking(dtBooking);
        this.customer.setCustomerId(this.costumerID);
        this.customer.setEmail(this.email);
        this.customer.setFirstName(this.firstName);
        this.customer.setLastName(this.lastName);
        this.customer.setMobile(this.phoneNumber);
        this.customer.setTourId(this.pack.getTour().getId());
        
        
        this.hotelBooking.bokaHandler(420,this.pack.getHotel().getName());
        

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
    
    public String getInfo(){
        
    String strengur= "Fulltnafn: " +this.firstName+" "+this.lastName+ "\n"+
            "Bókunarnúmer(brottför): "+this.flightBooking1.getBookingNumber() +
            "\n Bókunarnúmer(heimflug): "+this.flightBooking2.getBookingNumber()+
            "\n Dagsferð: "+this.pack.getTour().getTourName()+ 
            "\n Hótel: "+this.pack.getHotel().getName();
    return strengur;
    }
}
