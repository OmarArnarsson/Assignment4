/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Þorsteinn Óskarsson, Háskóli Íslands, tho85@hi.is
 */
public class PackageList {
    
    
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
    
    
    public PackageList(){
        
    }
    
    public ArrayList<Package> buildPackage (ArrayList<Flight> f1, ArrayList<Flight> f2, ArrayList<Hotel> h, ArrayList<DayTour> d,
                                            String dep, String dest, Calendar go,  Calendar home, Double priceLow, Double priceHigh,
                                            String type)
    {
  
        if(f1.isEmpty() || f2.isEmpty() || h.isEmpty() || d.isEmpty()){
            System.out.println("No packages available");
            return null;
        }
        else {

            ArrayList<Package> a = new ArrayList<Package>();
      
            checkFlight(f1, dep, dest, go);
            checkFlight(f2, dest, dep, home);
            checkHotel(h, go, home);
            checkDayTour(d, go, home);
            
            
            for(int j = 0; j<f1.size(); j++){
                for(int i = 0; i<f2.size(); i++){
                    for(int k = 0; k<h.size(); k++){
                        for(int l = 0; l<d.size(); l++){
                            Package pack = new Package(f1.get(j),f2.get(i), d.get(l), h.get(k));
                            pack.setPrice(pack.f1.economyPrice, pack.f2.economyPrice, pack.h.price, pack.d.price);
                            a.add(pack);     
                        }
                    }
                }
            }
            System.out.println("Before price and type filtering: " +a.size());
            filterPrice(a, priceLow, priceHigh);  
            filterType(a, type);
            System.out.println("After price and type filtering: " +a.size());
            return a;

           
        }
        
    }
    
    
    public void filterType(ArrayList<Package> a, String type){
       
        if(type.equals(""))
            return;
        
        for(int i = a.size()-1; i>=0; i--){
            if(!(a.get(i).getType().equals(type))){
                 a.remove(i);     
            }
        }
    }
    
    public void filterPrice(ArrayList<Package> a, Double priceLow, Double priceHigh){
        int size = a.size();
        for(int i = size-1; i>=0; i--){

            if(!(a.get(i).getPrice() <= priceHigh && a.get(i).getPrice() >= priceLow)){
                 a.remove(i);    
               
            }
        }

    }
    
    // Að neðan færum við mögulega yfir í search
    public void checkFlight(ArrayList<Flight> f, String dep, String dest, Calendar depDate){
        for(int i = f.size()-1; i>=0; i--){
                if(!f.get(i).depLocation.equals(dep) || !f.get(i).depDate.equals(depDate)
                   || !f.get(i).arrLocation.equals( dest)   ){   
                     f.remove(i);          
                }                 
            }
    }
    
    public void checkHotel(ArrayList<Hotel> h, Calendar go, Calendar home){
          for(int i = h.size()-1; i>=0; i--){
                if(h.get(i).timeStart.compareTo(go) != 0 || h.get(i).timeEnd.compareTo(home) != 0 ){
                    h.remove(i);
                }
                   
            }
    }
    
    public void checkDayTour(ArrayList<DayTour> d, Calendar go, Calendar home){
         
            for(int i = d.size()-1; i>=0; i--){
                if(d.get(i).dateStart.compareTo(go) < 0 || d.get(i).dateStart.compareTo(home) > 0 ){
                    d.remove(i);
                }
                    
            }
        
    }
    
}
