/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import hotelStuff.Hotel;
import java.util.ArrayList;
import java.util.Calendar;
import modelflight.ConnectedFlight;
import modeldaytour.Tour;

/**
 *
 * @author Þorsteinn Óskarsson, Háskóli Íslands, tho85@hi.is
 */
public class PackageList {
    
    private ArrayList<Package> listinn;
    
    
    public PackageList(){
        this.listinn = new ArrayList<Package>();
    }
    
    public ArrayList<Package> buildPackage (ArrayList<ConnectedFlight> f1,
                                            ArrayList<ConnectedFlight> f2, 
                                            ArrayList<Hotel> h,
                                            ArrayList<Tour> d,
                                            Double priceLow, 
                                            Double priceHigh,
                                            boolean menning,
                                            boolean adv,
                                            boolean skodun)
    {
  
        if(f1.isEmpty() || f2.isEmpty() || h.isEmpty() || d.isEmpty()){
            System.out.println("No packages available");
            return null;
        }
        else {
            System.out.println("before daytour and type filtering: " +d.size());
            System.out.print(d.size());
            System.out.println("After daytour and type filtering: " +d.size());
            filterType(d, menning, skodun, adv);
            System.out.print(d.size());
            if(d.isEmpty()){
                System.out.println("No packages available");
                return null;
            }
            ArrayList<Package> a = new ArrayList<Package>();
      
         /*   checkFlight(f1, dep, dest, go);
            checkFlight(f2, dest, dep, home);
            checkHotel(h, go, home);
            checkDayTour(d, go, home);*/
            
            
            for(int j = 0; j<f1.size(); j++){
                for(int i = 0; i<f2.size(); i++){
                    for(int k = 0; k<h.size(); k++){
                        for(int l = 0; l<d.size(); l++){
                            Package pack = new Package(f1.get(j),f2.get(i), d.get(l), h.get(k));
                            pack.setPrice(pack.getFlightTo().getTotalEconomyPrice(), pack.getFlightBack().getTotalEconomyPrice(),0.0, pack.d.getPrice());
                            a.add(pack);     
                        }
                    }
                }
            }
            System.out.println("before price and type filtering: " +a.size());
            filterPrice(a, priceLow, priceHigh);  
            System.out.println("After price and type filtering: " +a.size());
            this.listinn = a;
            return a;

           
        }
        
    }
    

    public void filterType(ArrayList<Tour> a, boolean menning, boolean skodun, boolean adv){
        System.out.print(menning+" "+skodun+" "+adv);
        if(menning && skodun && adv || !menning && !skodun && !adv){
            return;
        }
        else if(menning && skodun){
            for(int i = 0; i<a.size(); i++){
                if(!(a.get(i).getTourType().equals("Bar crawl") || a.get(i).getTourType().equals("Food tour") ||
                   a.get(i).getTourType().equals("Beer tour") || a.get(i).getTourType().equals("Car ride")  ||
                   a.get(i).getTourType().equals("Bus ride")  || a.get(i).getTourType().equals("Jeep ride"))){
                    a.remove(i);
                }
            }
        }
        else if(skodun && adv){
            for(int i = 0; i<a.size(); i++){
                if(!(a.get(i).getTourType().equals("Adventure") || a.get(i).getTourType().equals("Car ride")  ||
                   a.get(i).getTourType().equals("Bus ride")  || a.get(i).getTourType().equals("Jeep ride"))){
                    a.remove(i);
                }
            }
        }
        else if(menning && adv){
            for(int i = 0; i<a.size(); i++){
                if(!(a.get(i).getTourType().equals("Bar crawl") || a.get(i).getTourType().equals("Food tour") ||
                   a.get(i).getTourType().equals("Beer tour") || a.get(i).getTourType().equals("Adventure") || 
                   a.get(i).getTourType().equals("Jeep ride"))){
                    a.remove(i);
                }
            }
        }
        else if(menning){
            for(int i = 0; i<a.size(); i++){
                System.out.println(a.get(i).getTourType());
                if(!(a.get(i).getTourType().equals("Bar crawl") || a.get(i).getTourType().equals("Food tour") ||
                   a.get(i).getTourType().equals("Beer tour"))){
                    a.remove(i);
                }
            }
        }
        else if(adv){
            for(int i = 0; i<a.size(); i++){
                System.out.println(a.get(i).getTourType());
                if(!(a.get(i).getTourType().equals("Adventure") || 
                   a.get(i).getTourType().equals("Jeep ride"))){
                    a.remove(i);
                }
            }
        }
        else if(skodun){
            for(int i = 0; i<a.size(); i++){
                System.out.println(a.get(i).getTourType());
                if(!(a.get(i).getTourType().equals("Car ride") || a.get(i).getTourType().equals("Jeep ride") ||
                   a.get(i).getTourType().equals("Bus ride"))){
                    a.remove(i);
                }
            }
        }   
    }
    
    public void filterPrice(ArrayList<Package> a, Double priceLow, Double priceHigh){
        int size = a.size();
        for(int i = size-1; i>=0; i--){
            if(a.get(i).getPrice() < priceHigh && a.get(i).getPrice() >= priceLow ){ 
                 a.remove(i);    
            }
        }

    }
    
 /*   // Að neðan færum við mögulega yfir í search
    public void checkFlight(ArrayList<ConnectedFlight> f){
        for(int i = f.size()-1; i>=0; i--){
                if(!f.get(i).getFlight(i).getFlightNumber().equals(i)){//)|| !f.get(i).getDepDate.equals(depDate)
                  // || !f.get(i).arrLocation.equals( dest)   ){   
                     System.out.println(f.get(i).getFlight(i).getArrDate()+"    "+depDate);
                     f.remove(i);          
                }                 
            }
    }
    
    public void checkHotel(ArrayList<Hotel> h, Calendar go, Calendar home){
          for(int i = h.size()-1; i>=0; i--){
                if(h.get(i).timeStart.compareTo(go) != 0 || h.get(i).timeEnd.compareTo(home) != 0 ){
                    System.out.println("RemoveHotel");
                    h.remove(i);
                }
                   
            }
    }
    
    public void checkDayTour(ArrayList<DayTour> d, Calendar go, Calendar home){
         
            for(int i = d.size()-1; i>=0; i--){
                if(d.get(i).dateStart.compareTo(go) < 0 || d.get(i).dateStart.compareTo(home) > 0 ){
                    System.out.println("RemoveDT");
                    d.remove(i);
                }
                    
            }
        
    }*/
    
}
