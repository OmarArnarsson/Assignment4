/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author stein
 */
public class DayTour {
    
    private int id;
    private String type;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int price;
    private String destination;
    private int groupSize;
    
    
    public DayTour(LocalDate start, LocalDate end, int price,
                    String destination, int groupSize){
        
        this.dateStart = start;
        this.dateEnd = end;
        this.price = price;
        this.destination = destination;
        this.groupSize = groupSize;
        
    }
    
        
}
