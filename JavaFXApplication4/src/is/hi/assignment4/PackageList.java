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
         
        
        
        public Package(Flight f1, Flight f2, DayTour d, Hotel h){
            this.f1 = f1;
            this.f2 = f2;   
            this.h=h;
            this.d=d;
        }  
        
        public void setPrice(double f1, double f2, double h, double d){
            this.price = f1+f2+d+h;
        }
        private Double getPrice(){
            return this.price;
            
        }
        
        
    }
    
    
    public PackageList(){
        
    }
    
    public ArrayList<Package> buildPackage (ArrayList<Flight> f1, ArrayList<Flight> f2, ArrayList<Hotel> h, ArrayList<DayTour> d,
                                            String dep, String dest, Calendar go,  Calendar home, Double priceLow, Double priceHigh)
    {
        
        if(f1.isEmpty() || f2.isEmpty() || h.isEmpty() || d.isEmpty()){
            System.out.print("Sorry");
            return null;
        }
        else {

            ArrayList<Package> a = new ArrayList<Package>();
       
            PackageList s = new PackageList();
            
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
            System.out.print(a.size());
            filterPrice(a, priceLow, priceHigh);  
            return a;

           
        }
        
    }
    
    
    public void filterPrice(ArrayList<Package> a, Double priceLow, Double priceHigh){
        int size = a.size();
        for(int i = 0; i<size; i++){

            if(!(a.get(i).getPrice() < priceHigh && a.get(i).getPrice() > priceLow)){
                 a.remove(i);    
               
            }
        }

    }
    
    public void checkFlight(ArrayList<Flight> f, String dep, String dest, Calendar depDate){
        for(int i = 0; i<f.size(); i++){
                System.out.println("Flight1: ");
                if(!f.get(i).depLocation.equals(dep) || !f.get(i).depDate.equals(depDate)
                   || !f.get(i).arrLocation.equals( dest)   ){   
                     f.remove(i);          
                }                 
            }
    }
    
    public void checkHotel(ArrayList<Hotel> h, Calendar go, Calendar home){
          for(int i = 0; i<h.size(); i++){
                System.out.println("Hotel: ");
                if(h.get(i).timeStart.compareTo(go) != 0 || h.get(i).timeEnd.compareTo(home) != 0 ){
                    h.remove(i);
                }
                   
            }
    }
    
    public void checkDayTour(ArrayList<DayTour> d, Calendar go, Calendar home){
         
            for(int i = 0; i<d.size(); i++){
                System.out.println("DT: ");
                if(d.get(i).dateStart.compareTo(go) < 0 || d.get(i).dateStart.compareTo(home) > 0 ){
                    d.remove(i);
                }
                    
            }
        
    }
    
}
