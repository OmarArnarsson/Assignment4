/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import hotelStuff.Hotel;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
                                            boolean skodun,
                                            boolean economy,
                                            LocalDate toDate,
                                            LocalDate homeDate)
    {
  
        if(f1.isEmpty() || f2.isEmpty() || h.isEmpty() || d.isEmpty()){
            System.out.println("No packages available");
            return new ArrayList<Package>();
        }
        else {
            System.out.print(d.size());
            d = filterType(d, menning, skodun, adv);
            checkFlight(f1,toDate);
            checkFlight(f2,homeDate);
            System.out.print(d.size());
            if(d.isEmpty() || f1.isEmpty() || f2.isEmpty()){
                System.out.println("No packages available");
                return new ArrayList<Package>();
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
                            System.out.println("DATES F1:  "+f1.get(j).getLastArrTime()+"DATES F2:  "+f2.get(i).getLastArrTime());
                            double priceF1;
                            double priceF2;
                            if(economy){
                                priceF1 = pack.getFlightTo().getTotalEconomyPrice();
                                priceF2 = pack.getFlightBack().getTotalEconomyPrice();
                            }
                            else {
                                priceF1 = pack.getFlightTo().getTotalBusinessPrice();
                                priceF2 = pack.getFlightBack().getTotalBusinessPrice();
                            }
                            pack.setPrice(priceF2, priceF1,h.get(i).getPricerange(), pack.getTour().getPrice());
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
    
    public void setList(ArrayList<Package> a){
        this.listinn = a;
    }
    public Package getResultNr(int i){
        return this.listinn.get(i);
    }

    public ArrayList<Tour>  filterType(ArrayList<Tour> a, boolean menning, boolean skodun, boolean adv){
        ArrayList<Tour> nList = new ArrayList<Tour>();
        System.out.println(menning+" "+skodun+" "+adv);    
        if(menning && skodun && adv || !menning && !skodun && !adv){
            return a;
        }
        else if(menning && skodun && !adv){
            for(int i = 0; i<a.size(); i++){
                if((a.get(i).getTourType().equals("Bar crawl") || a.get(i).getTourType().equals("Food tour") ||
                   a.get(i).getTourType().equals("Beer tour") || a.get(i).getTourType().equals("Car ride")  ||
                   a.get(i).getTourType().equals("Bus ride")  || a.get(i).getTourType().equals("Jeep ride"))){
                    //a.remove(i);
                    nList.add(a.get(i));
                }
            }
        }
        else if(skodun && adv && !menning){
            for(int i = 0; i<a.size(); i++){
                if((a.get(i).getTourType().equals("Adventure") || a.get(i).getTourType().equals("Car ride")  ||
                   a.get(i).getTourType().equals("Bus ride")  || a.get(i).getTourType().equals("Jeep ride"))){
                    //a.remove(i);
                    nList.add(a.get(i));
                }
            }
        }
        else if(menning && adv && !skodun){
            for(int i = 0; i<a.size(); i++){
                if((a.get(i).getTourType().equals("Bar crawl") || a.get(i).getTourType().equals("Food tour") ||
                   a.get(i).getTourType().equals("Beer tour") || a.get(i).getTourType().equals("Adventure") || 
                   a.get(i).getTourType().equals("Jeep ride"))){
                    //a.remove(i);
                    nList.add(a.get(i));
                }
            }
        }
        else if(menning && !adv && !skodun){
            for(int i = 0; i<a.size(); i++){
                if((a.get(i).getTourType().equals("Bar crawl") || a.get(i).getTourType().equals("Food tour") ||
                   a.get(i).getTourType().equals("Beer tour"))){
                    //a.remove(i);
                    nList.add(a.get(i));
                }
            }
        }
        else if(adv && !skodun && !menning){
            for(int i = 0; i<a.size(); i++){
                if((a.get(i).getTourType().equals("Adventure") || 
                   a.get(i).getTourType().equals("Jeep ride"))){
                   // a.remove(i);
                    nList.add(a.get(i));
                }
            }
        }
        else if(skodun && !menning && !adv){
            for(int i = 0; i<a.size(); i++){
                if((a.get(i).getTourType().equals("Car ride") || a.get(i).getTourType().equals("Jeep ride") ||
                   a.get(i).getTourType().equals("Bus ride"))){
                   // a.remove(i);
                    nList.add(a.get(i));
                }
            }
        }  
        System.out.println("Fjöldi DT eftir FILTERTYOE:  "+nList.size());
        return nList;

    }
    
    public void filterPrice(ArrayList<Package> a, Double priceLow, Double priceHigh){
        int size = a.size();
        for(int i = size-1; i>=0; i--){
            if(!(a.get(i).getPrice() < priceHigh && a.get(i).getPrice() >= priceLow)){ 
                 a.remove(i);    
            }
        }

    }
    
    // Að neðan færum við mögulega yfir í search
    public void checkFlight(ArrayList<ConnectedFlight> f, LocalDate d){
        for(int i = f.size()-1; i>=0; i--){
                Calendar lastArrTime = f.get(i).getLastArrTime();
                
                lastArrTime.get(Calendar.DAY_OF_MONTH);
                String date = d+"";
                date = date.substring(8, 10);
                int compare = Integer.parseInt(date);
                if( !(lastArrTime.get(Calendar.DAY_OF_MONTH) == compare)){
                    f.remove(i);    
                }
                
            }                
            
    }
    /*
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
