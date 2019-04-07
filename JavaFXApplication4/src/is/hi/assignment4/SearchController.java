/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import hotelStuff.FilterEngine;
import hotelStuff.HotelDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.SearchEngine;
import model.SearchResult;


/**
 *
 * @author stein
 */
public class SearchController{
    

    private SearchEngine flightSearchTo;
    private SearchEngine flightSearchBack;
    private FilterEngine Hotel;

    private double[] priceRange = new double[2];
    private boolean menning;
    private boolean adventure;
    private boolean skodunarferdir;
    
    private String depLoc;
    private String arrLoc;
    private String depLoc1;
    private String arrLoc1;
    
    private LocalDate departure;
    private LocalDate home;

    private int count;
    
    
    public SearchController(){
        
        flightSearchTo = new SearchEngine();
        flightSearchBack = new SearchEngine();
        Hotel =  new FilterEngine();
    }  
    
    public ArrayList<Package> getResults() throws SQLException, CloneNotSupportedException{
        
        
        //process strings
        this.processFlight();
        SearchResult a = flightSearchTo.findFlightCourse();
        SearchResult b = flightSearchBack.findFlightCourse();
        
        System.out.println("TO:  "+a.getResultCount()+"  HOME:    "+b.getResultCount());
        //leita hotel
        //this.processHotel();
        ArrayList<hotelStuff.Hotel> Hotels = Hotel.findHotelLoc(arrLoc);
        System.out.print(Hotels);
        //System.out.print(HotelDAO.getAllHotels());
        //leita daytour
        
        // byggja pakka = a
        
        //skila pakka a
        
        return new ArrayList<Package>();
    }
    
    public void processFlight(){
        //flight to
        flightSearchTo.setPassangerCount(this.count);
        flightSearchTo.setEconomy(true);
        flightSearchTo.setDepLocation(this.depLoc);

        Instant instant = Instant.from(this.departure.atStartOfDay(ZoneId.systemDefault()));
        long time = instant.toEpochMilli();
        Calendar cal =  Calendar.getInstance();
        cal.setTimeInMillis(time);
        
        flightSearchTo.setDepDate(cal);
        flightSearchTo.setArrLocation(this.arrLoc);
        flightSearchTo.setFlexibility(0);
        
        
        //flight home
        flightSearchBack.setPassangerCount(this.count);
        flightSearchBack.setEconomy(true);
        flightSearchBack.setDepLocation(this.arrLoc);

        Instant instant1 = Instant.from(this.home.atStartOfDay(ZoneId.systemDefault()));
        long time1 = instant1.toEpochMilli();
        Calendar cal1 =  Calendar.getInstance();
        cal1.setTimeInMillis(time1);
        
        flightSearchBack.setDepDate(cal1);
        flightSearchBack.setArrLocation(this.depLoc);
        flightSearchBack.setFlexibility(0);
        
        
        
    }
      
    
    
    public void processDayTours(){
        
    }
    
    public void processHotel() throws SQLException{
        //Hotel.findHotelLoc(this.depLoc);
    }
    
    
    public void setFlightEngine(){
        this.flightSearchTo = new SearchEngine();
        this.flightSearchBack = new SearchEngine();
    }
    public void setCount(int a){
        this.count = a;
    }
    public void setDepLoc(String a){
        this.depLoc = a;
    }
    public void setDepLoc1(String a){
        this.depLoc1 = a;
    }
    public void setArrLoc(String a){
        this.arrLoc = a;
    }
    public void setArrLoc1(String a){
        this.arrLoc1 = a;
    }
    
    public void setPriceRange1(double a){
        if(a == 0){
            this.priceRange[0] = 0;
            this.priceRange[1] = Integer.MAX_VALUE;;
        }
        else if(a == 1){
            this.priceRange[0] = 0;
            this.priceRange[1] = 50000;
        }
        else if(a == 2){
            this.priceRange[0] = 50001;
            this.priceRange[1] = 100000;
        }
        else if(a == 3){
            this.priceRange[0] = 100001;
            this.priceRange[1] = 150000;
        }
        else {
            this.priceRange[0] = 150001;
            this.priceRange[1] = Integer.MAX_VALUE;
        }
    }
    
    public void setMenning(boolean a){
        this.menning = a;
    }
     public void setAdventure(boolean a){
        this.adventure = a;
    }
    public void setSkodunar(boolean a){
        this.skodunarferdir = a;
    }

    public void setDepDate(LocalDate value) {
        this.departure = value;
    }

    public void setHomeDate(LocalDate value) {
        this.home = value;
        
    }
    
}
