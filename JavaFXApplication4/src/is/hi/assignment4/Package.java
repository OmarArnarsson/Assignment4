package is.hi.assignment4;

import hotelStuff.Hotel;
import modelflight.ConnectedFlight;
import modeldaytour.Tour;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Þorsteinn Óskarsson, Háskóli Íslands, tho85@hi.is
 */
public class Package {
        public ConnectedFlight f1;
        public ConnectedFlight f2;
        public Tour d;
        public Hotel h;
        public Double price;
        public String type; 
        
        
        public Package(ConnectedFlight f1, ConnectedFlight f2, Tour d, Hotel h){
            this.f1 = f1;
            this.f2 = f2;   
            this.h=h;
            this.d=d;
            this.type = d.getTourType();
        }  
        
        public void setPrice(double f1, double f2, double h, double d){
            this.price = f1+f2+d+h;
        }
        
        public Double getPrice(){
            return this.price;    
        }
        public String getType(){
            return this.type;    
        }
        
        
        
    }
