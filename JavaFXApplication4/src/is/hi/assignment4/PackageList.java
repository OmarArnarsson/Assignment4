/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.util.ArrayList;
import java.util.Calendar;
import model.ConnectedFlight;

/**
 *
 * @author Þorsteinn Óskarsson, Háskóli Íslands, tho85@hi.is
 */
public class PackageList {
    
    
    public ArrayList<Package> buildPackage (ArrayList<ConnectedFlight> f1,
                                            ArrayList<ConnectedFlight> f2, 
                                            ArrayList<Hotel> h,
                                            ArrayList<DayTour> d,
                                            String dep,
                                            String dest,
                                            Calendar go, 
                                            Calendar home, 
                                            Double priceLow, 
                                            Double priceHigh,
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
                            pack.setPrice(pack.f1.getTotalEconomyPrice(), pack.f2.getTotalEconomyPrice(), pack.h.price, pack.d.price);
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
    public void checkFlight(ArrayList<ConnectedFlight> f, String dep, String dest, Calendar depDate){
        for(int i = f.size()-1; i>=0; i--){
                if(!f.get(i).getFlight(i).getArrDate().equals(i)){//)|| !f.get(i).getDepDate.equals(depDate)
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
        
    }
    
}
