/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author stein
 */
public class Flight {
    private String depLocation;
    private Calendar depDate;
    private String arrLocation;
    private Calendar arrDate;
    private Double economyPrice;


    public Flight(String depLocation, Calendar depDate, String arrLocation, Calendar arrDate,  Double economyPrice){
        this.depLocation = depLocation;
        this.depDate = depDate;
        this.arrLocation = arrLocation;
        this.arrDate = arrDate;
        this.economyPrice = economyPrice;
        
    }
    
    public Calendar getDepDate(){
        return this.depDate;
    }
    public Calendar getArrDate(){
        return this.arrDate;
    }
    
    
}
