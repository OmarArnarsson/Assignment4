/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.time.LocalTime;

/**
 *
 * @author stein
 */
public class MockDayTour {
    
    private int id;
    private String type;
    private LocalTime dateStart;
    private LocalTime dateEnd;
    private int price;
    private String destination;
    private int groupSize;
    
    
    public MockDayTour(LocalTime start, LocalTime end, int price,
                    String destination, int groupSize){
        
        this.dateStart = start;
        this.dateEnd = end;
        this.price = price;
        this.destination = destination;
        this.groupSize = groupSize;
        
    }
    
        
}
