/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.SearchEngine;
import model.SearchResult;


/**
 *
 * @author stein
 */
public class SearchController{
    
    private String depLocation;

    private SearchEngine flightSearch;
    

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

    
    public SearchController(){
        
        flightSearch = new SearchEngine();
        
    }  
    
    public ArrayList<Package> getResults() throws SQLException, CloneNotSupportedException{
        
        //process strings
        
        //leita flug
        //leita hotel
        //leita daytour
        
        // byggja pakka = a
        
        //skila pakka a
        
        return new ArrayList<Package>();
    }
    
    public void processFlight(){
       
    }
    
    public void processDayTours(){
        
    }
    
    public void processHotel(){
        
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
