package is.hi.assignment4;

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
        public Flight f1;
        public Flight f2;
        public DayTour d;
        public Hotel h;
        public Double price;
        public String type; 
        
        
        public Package(Flight f1, Flight f2, DayTour d, Hotel h){
            this.f1 = f1;
            this.f2 = f2;   
            this.h=h;
            this.d=d;
            this.type = d.type;
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
