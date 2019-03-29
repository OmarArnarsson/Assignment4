/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author stein
 */
public class Flight {
    private int price;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private Clock clock;
    private int flightId;
    
    
    public Flight(LocalDate start, LocalDate end, int price ){
        this.price = price;
        this.timeStart = start;
        this.timeEnd = end;
        this.clock = Clock.systemUTC();
        this.flightId = (int)Math.random()*10;
        
    }
    
    public LocalDate getStart(){
        return this.timeStart;
    }
    public LocalDate getEnd(){
        return this.timeEnd;
    }
    
    
}
