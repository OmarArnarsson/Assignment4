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
    
    private boolean economy=true;
    
    private LocalDate departure;
    private LocalDate home;

    
    private int count;
    private PackageList Pakkar;
    
    public SearchController(){
        
        flightSearchTo = new SearchEngine();
        flightSearchBack = new SearchEngine();

        
        tourDB = new DatabaseManager();
        tourFilter = new TourFilter();
        tourController = new TourController(tourDB);

        Hotel =  new FilterEngine();
        Pakkar = new PackageList();
     
        

    }  
    
    
    public void setList(ArrayList<Package> a){
          Pakkar.setList(a);
       }
    public Package getResultNr(int i){
        return Pakkar.getResultNr(i);
    }
    
    public void setEconomy(boolean a){
        this.economy=a;
    }
    
    public ArrayList<Package> getResults() throws SQLException, CloneNotSupportedException, Exception{
        
        //process strings
        this.processFlight();
        System.out.print(this.priceRange[0]+"  pR   "+this.priceRange[1]);
        ArrayList <ConnectedFlight> f1 = new ArrayList<>();
        SearchResult a = flightSearchTo.findFlightCourse();
        int alength = a.getResultCount();
        for (int i = 0; i < alength; i++) {
      
                f1.add(a.getConnectedFlight(i));
        }

        ArrayList <ConnectedFlight> f2 = new ArrayList<>();
        SearchResult b = flightSearchBack.findFlightCourse();
        int blength = b.getResultCount();
        for (int i = 0; i < blength; i++) {
                f2.add(b.getConnectedFlight(i));
        }
        System.out.println("Fjöldi TO:  "+f1.size()+"   Fjöldi BACK:  "+f2.size());
        
        
        //leita hotel
        //this.processHotel();
        ArrayList<Hotel> Hotels = Hotel.findHotelLoc(arrLoc);
        //System.out.print(Hotels.size());
        //System.out.print(HotelDAO.getAllHotels());
        //leita daytour
    
        ArrayList<Tour> DT = this.processDayTours();
        // byggja pakka = a



        double low = this.priceRange[0];
        double high = this.priceRange[1];
        System.out.print("áður en BUILD :"+this.priceRange[0]+"   "+this.priceRange[1]);
        ArrayList<Package> Pakkarnir = Pakkar.buildPackage(f1, f2, Hotels, DT, low, high, this.menning, this.adventure, this.skodunarferdir, this.economy, this.departure, this.home);
        
      

        //PackageList Pakkar = new PackageList();
        //double low = priceRange[0];
        //double high = priceRange[1];
        //ArrayList<Package> Pakkarnir = Pakkar.buildPackage(a, b, Hotels, DT, arrLoc, depLoc, departure, home, low, high, "");


        
        //skila pakka a
        
        return Pakkarnir;
    }
    
    public void processFlight(){
        //flight to
        flightSearchTo.setPassangerCount(this.count);
        
        
        if(!this.economy){
            flightSearchTo.setEconomy(false);
        }
        else {
            flightSearchTo.setEconomy(true);
        }
        
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

        if(!this.economy)
            flightSearchBack.setEconomy(false);
        else{
            flightSearchBack.setEconomy(true);
       }
        
        flightSearchBack.setDepLocation(this.arrLoc);

        Instant instant1 = Instant.from(this.home.atStartOfDay(ZoneId.systemDefault()));
        long time1 = instant1.toEpochMilli();
        Calendar cal1 =  Calendar.getInstance();
        cal1.setTimeInMillis(time1);
        
        flightSearchBack.setDepDate(cal1);
        flightSearchBack.setArrLocation(this.depLoc);
        flightSearchBack.setFlexibility(0);
        
        System.out.println("DS"+this.priceRange[0]+"BOOM");
        
    }
      
    
    
    public ArrayList<Tour> processDayTours() throws Exception{

        ArrayList<Tour> listinn = new ArrayList<>();
        
        tourFilter.setPrice(999999999);
        tourFilter.setGroupSize(this.count);
        tourFilter.setLocation(this.arrLoc);
        tourFilter.setTimeStart(this.departure+"");
        tourFilter.setTourType("%");
        
        LocalDate temp = this.departure;

        
        while(temp.compareTo(this.home) < 0){

            
            tourFilter.setTimeStart(temp+"");
            
            LinkedList<Tour> liste = tourController.searchByDate(tourFilter);
            for(int i = 0; i<liste.size(); i++){
                    
                listinn.add(liste.get(i));
        
            }
            
            temp=temp.plusDays(1);

        }
        
        System.out.println("Fjöldi DT:  "+listinn.size());
        return listinn;

    }
        
    
    
    public void processHotel() throws SQLException{
        //Hotel.findHotelLoc(this.depLoc);
    }
    
    
    public void resetEngines(){
        this.flightSearchTo = new SearchEngine();
        this.flightSearchBack = new SearchEngine();
        
        this.tourDB = new DatabaseManager();
        this.tourFilter = new TourFilter();
        this.tourController = new TourController(this.tourDB);
        Pakkar = new PackageList();
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
    
    public void setPriceRange(boolean low, boolean med, boolean high, boolean highest, boolean all){
        if(all){
            this.priceRange[0] = 0;
            this.priceRange[1] = 9999999;
        }
        else if(low){
            this.priceRange[0] = 0;
            this.priceRange[1] = 50000;
        }
        else if(med){
            this.priceRange[0] = 50001;
            this.priceRange[1] = 100000;
        }
        else if(high){
            this.priceRange[0] = 100001;
            this.priceRange[1] = 150000;
        }
        else if(highest){
            this.priceRange[0] = 150001;
            this.priceRange[1] = 9999999;
        }
        System.out.print(this.priceRange[0]+"   "+this.priceRange[1]+" PRICERANGE");
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
