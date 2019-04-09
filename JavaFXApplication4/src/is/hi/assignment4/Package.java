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
            this.price =f1+f2+d+h;
        }
        
        public Double getPrice(){
            return this.price;    
        }
        public String getType(){
            return this.type;    
        }
        public Tour getTour(){
            return this.d;
        }
        public ConnectedFlight getFlightTo(){
            return this.f1;
        }
         public ConnectedFlight getFlightBack(){
            return this.f2;
        }
         public Hotel getHotel(){
            return this.h;
        }
         
         public String toString(){
             String verd = "Verð: "+this.getPrice();
             String dags = ""+this.d.getTimeStart();
             dags = dags.substring(0, 10);
             verd = verd.substring(0, verd.length()-2);
             String pakki = "Áfangastaður "+this.f1.toString() +"\n"+"\n"+
                           "Dagferð: "+this.d.getTourName() + "\n"+
                           "Dagsetning: "+dags+"\n"+"\n"+
                           "Hotel: "+this.h.getName() + "\n"+"\n"+
                           "Heim "+this.f2.toString() + "\n"+"\n"+
                            verd +
                            "\n" + "___________________________________________________"+
                            "\n";
             
             return pakki;

         }
        
    }
