/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.time.Clock;
import java.time.LocalTime;

/**
 *
 * @author stein
 */
public class MockHotel {
    
    private int price;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private int stars;
    
    
    
    public MockHotel(int price, LocalTime start, LocalTime end, int stars){
        this.price = price;
        this.timeStart = start;
        this.timeEnd = end;
        this.stars = (int)Math.random()*5;
        
    }
    
    
}

