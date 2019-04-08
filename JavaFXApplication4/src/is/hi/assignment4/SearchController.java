/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;


import controllerdaytour.DatabaseManager;
import controllerdaytour.TourController;

import hotelStuff.FilterEngine;
import hotelStuff.Hotel;
import hotelStuff.HotelDAO;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import modeldaytour.Tour;
import modeldaytour.TourFilter;
import modelflight.ConnectedFlight;
import modelflight.SearchEngine;
import modelflight.SearchResult;


/**
 *
 * @author stein
 */
public class SearchController{
    

    private SearchEngine flightSearchTo;
    private SearchEngine flightSearchBack;
    private FilterEngine Hotel;

    private TourController tourController;
    private TourFilter tourFilter;
    private DatabaseManager tourDB;
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

        
        tourDB = new DatabaseManager();
        tourFilter = new TourFilter();
        tourController = new TourController(tourDB);

        Hotel =  new FilterEngine();

    }  
    
    public ArrayList<Package> getResults() throws SQLException, CloneNotSupportedException, Exception{
        
        
        //process strings
        this.processFlight();
        SearchResult a = flightSearchTo.findFlightCourse();
        SearchResult b = flightSearchBack.findFlightCourse();
        
        
        //leita hotel
        //this.processHotel();
        ArrayList<Hotel> Hotels = Hotel.findHotelLoc(arrLoc);
        System.out.print(Hotels);
        //System.out.print(HotelDAO.getAllHotels());
        //leita daytour
        System.out.println("TO:  "+a.getResultCount()+"  HOME:    "+b.getResultCount());
        ArrayList<Tour> DT = this.processDayTours();
        // byggja pakka = a
        PackageList Pakkar = new PackageList();
        double low = priceRange[0];
        double high = priceRange[1];
       // ArrayList<Package> Pakkarnir = Pakkar.buildPackage(a, b, Hotels, DT, arrLoc, depLoc, departure, home, low, high, "");
        
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
      
    
    
    public ArrayList<Tour> processDayTours() throws Exception{

        /*public TourFilter(int price, String groupSize,
                      String location, String tourType,
                      String timeStart, boolean guidedTour,
                      boolean privateTour, boolean accessibility) {

        this.price = price;
        this.groupSize = gSize(Integer.parseInt(groupSize));
        this.location = location;
        this.tourType = tourType;
        this.timeStart = timeStart;
        this.guidedTour = guidedTour;
        this.privateTour = privateTour;
        this.accessibility = accessibility;
        TourFilter tourFilter ;
        if(this.menning && this.adventure && this.skodunarferdir || !this.menning && !this.adventure && !this.skodunarferdir) {
            TourFilter filtAdv;
            TourFilter filtBus;
            TourFilter filtCar;
            TourFilter filtBar;
            TourFilter filtFood;
            TourFilter filtBeer;
            TourFilter filtJeep;
            /*
            busride
            carride
            barcrawl
            foodtour
            beertour
            adv
            jeepride
            
        }
        else if(this.menning && this.adventure ){
            
        }
        else if(this.adventure && this.skodunarferdir){
            
        }
        else if(this.menning && this.skodunarferdir){
            
        }
        else if(){
            
        }
        else if(){
            
        }
        else if(){
            
        }*/
        ArrayList<Tour> listinn = new ArrayList<>();
        
        tourFilter.setPrice(999999999);
        tourFilter.setGroupSize(this.count);
        tourFilter.setLocation(this.arrLoc);
        tourFilter.setTimeStart(this.departure+"");
        tourFilter.setTourType("%");
        
        LocalDate temp = this.departure;

        temp.plusDays(1L);
        System.out.print(temp);
        
        while(temp.compareTo(this.home) < 0){

            
            tourFilter.setTimeStart(temp+"");
            
            LinkedList<Tour> liste = tourController.searchByDate(tourFilter);
            for(int i = 0; i<liste.size(); i++){
                    
                listinn.add(liste.get(i));
        
            }
            
            temp=temp.plusDays(1);
            System.out.println(temp);

        }
        
        System.out.print(listinn.size());
        return listinn;

    }
        
    
    
    public void processHotel() throws SQLException{
        //Hotel.findHotelLoc(this.depLoc);
    }
    
    
    public void resetEngines(){
        this.flightSearchTo = new SearchEngine();
        this.flightSearchBack = new SearchEngine();
        this.tourFilter = new TourFilter();
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
    
    public void setPriceRange(double a){
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
