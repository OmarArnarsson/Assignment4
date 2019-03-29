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
public class Hotel {
    
    private int price;
    private Calendar timeStart;
    private Calendar timeEnd;
    private int stars;
    
    
    
    public Hotel(Calendar start, Calendar end, int price, int stars){
        this.price = price;
        this.timeStart = start;
        this.timeEnd = end;
        this.stars = (int)Math.random()*5;
        
    }
    
    
}


