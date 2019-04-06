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
    

    private int price50;
    
    public SearchController(){
        
        flightSearch = new SearchEngine();
        
    }  
    
    public ArrayList<Package> getResults() throws SQLException, CloneNotSupportedException{
        
        SearchResult s = flightSearch.findFlightCourse();
        
        for(int i = 0; i<10; i++){
            System.out.print(s.getConnectedFlight(i));
        }
        //leita flug
        //leita hotel
        //leita daytour
        
        // byggja pakka = a
        
        //skila pakka a
        return new ArrayList<Package>();
    }
    
    public void processFlight(LocalDate start, LocalDate end, int price){
      
    }
    
    public void processDayTours(LocalDate start, LocalDate end){
        
    }
    
    public void processHotel(LocalDate start, LocalDate end){
        
    }
    
    
    public void setPriceRange(int a){
        this.price50 = a;
    }
}
