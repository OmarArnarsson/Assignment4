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
        private ConnectedFlight f1;
        private ConnectedFlight f2;
        private Tour d;
        private Hotel h;
        private Double price;
        private String type; 
        
        
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
             
            String flugTo = "";
            String flugBack = "";
            
            
            
            for(int i = 0; i<this.f1.getFlightCount(); i++){
                String timiFlugTo = ""+this.f1.getFlight(i).getDepDate().getTime();
                timiFlugTo = timiFlugTo.substring(0, timiFlugTo.length()-12);
                String timiFlugTo2 = ""+this.f1.getFlight(i).getArrDate().getTime();
                timiFlugTo2 = timiFlugTo2.substring(0, timiFlugTo2.length()-12);
                flugTo += "Í loftið: "+timiFlugTo + "  Lending: "+timiFlugTo2+"\n";
            }
            for(int i = 0; i<this.f2.getFlightCount(); i++){
                String timiFlugBack = ""+this.f2.getFlight(i).getDepDate().getTime();
                timiFlugBack = timiFlugBack.substring(0, timiFlugBack.length()-12);
                String timiFlugBack2 = ""+this.f2.getFlight(i).getArrDate().getTime();
                timiFlugBack2 = timiFlugBack2.substring(0, timiFlugBack2.length()-12);
                flugBack += "Í loftið: "+timiFlugBack + "  Lending: "+timiFlugBack2+"\n";
            } 
             
             String pakki = "Áfangastaður "+this.f1.toString() +"\n"+
                            flugTo+"\n"+
                           "Dagferð: "+this.d.getTourName() + "\n"+
                           "Dagsetning: "+dags+"\n"+"\n"+
                           "Hotel: "+this.h.getName() + "\n"+"\n"+
                           "Heim"+this.f2.toString() + "\n"+
                            flugBack+"\n"+
                            verd +
                            "\n" + "___________________________________________________"+
                            "\n";
             
             return pakki;

         }
        
    }
