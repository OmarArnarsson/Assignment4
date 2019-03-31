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
    public String depLocation;
    public Calendar depDate;
    public String arrLocation;
    public Calendar arrDate;
    public Double economyPrice;
    public int flightNr;


    public Flight(String depLocation, Calendar depDate, String arrLocation, Calendar arrDate,  Double economyPrice){
        this.depLocation = depLocation;
        this.depDate = depDate;
        this.arrLocation = arrLocation;
        this.arrDate = arrDate;
        this.economyPrice = economyPrice;
        this.flightNr = (int)Math.random()*10000;
        
    }

    
}
