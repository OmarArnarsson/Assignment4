/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author stein
 */
public class DayTour {
    
    public int id;
    public String type;
    public Calendar dateStart;
    public Calendar dateEnd;
    public int price;
    public String destination;
    public int groupSize;
    
    
    public DayTour(Calendar start, Calendar end, int price,
                    String destination, int groupSize, String type){
        
        this.dateStart = start;
        this.dateEnd = end;
        this.price = price;
        this.destination = destination;
        this.groupSize = groupSize;
        this.type = type;
        
    }
    
        
}
