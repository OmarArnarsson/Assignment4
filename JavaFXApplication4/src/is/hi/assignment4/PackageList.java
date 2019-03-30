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
         
        
        
        public Package(Flight f1, Flight f2, DayTour d, Hotel h){
            this.f1 = f1;
            this.f2 = f2;   
            this.h=h;
            this.d=d;
        }  
        
    }
    
    
    public PackageList(){
        
    }
    
    public ArrayList<Package> buildPackage (ArrayList<Flight> f1, ArrayList<Flight> f2, ArrayList<Hotel> h, ArrayList<DayTour> d,
                                            String dest, String dep, Calendar go,  Calendar home)
    {
        
        if(f1.isEmpty() || f2.isEmpty() || h.isEmpty() || d.isEmpty()){
            System.out.print("Sorry");
            return null;
        }
        else {

            ArrayList<Package> a = new ArrayList<Package>();

            int whole = f1.size()*f2.size()*h.size()*d.size();
            
            PackageList s = new PackageList();
            
            for(int i = 0; i<f1.size(); i++){
                System.out.println("Flight1: ");
                if(!f1.get(i).depLocation.equals(dep) || !f1.get(i).depDate.equals(go)
                   || !f1.get(i).arrLocation.equals( dest)   ){
                    
                    // System.out.print(f1.remove(i));
                    System.out.println("dep  "+f1.get(i).depLocation.equals(dep));
                    System.out.println("depDate  "+f1.get(i).depDate.equals(go));
                    System.out.print("arr  "+f1.get(i).arrLocation.equals( dest) );
                }
                    
                
            }
            
            for(int i = 0; i<f2.size(); i++){
                System.out.println("Flight2: ");
                if(!f2.get(i).depLocation.equals(dest) || !f2.get(i).depDate.equals(home )
                   || !f2.get(i).arrLocation.equals(dep)){
                   
                    //System.out.print(f2.remove(i));
                    System.out.println("dep  "+f2.get(i).depLocation.equals(dep));
                    System.out.println("depDate  "+f2.get(i).depDate.equals(go));
                    System.out.print("arr  "+f2.get(i).arrLocation.equals( dest) );
                }
            }
            
            for(int i = 0; i<d.size(); i++){
                System.out.println("DT: ");
                if(d.get(i).dateStart.compareTo(go) < 0 || d.get(i).dateStart.compareTo(home) > 0 ){
                     System.out.print("DT");
                     System.out.print(d.remove(i));
                }
                    
            }
            
            for(int i = 0; i<h.size(); i++){
                System.out.println("Hotel: ");
                if(h.get(i).timeStart.compareTo(go) != 0 || h.get(i).timeEnd.compareTo(home) != 0 ){
                     // System.out.print(h.remove(i));
                     System.out.print("HotelSKR");
                }
                   
            }
            
            System.out.print("Size f1  "+f1.size()+"  Size f2  "+f2.size());
            
            for(int j = 0; j<f1.size(); j++){
                for(int i = 0; i<f2.size(); i++){
                    for(int k = 0; k<h.size(); k++){
                        for(int l = 0; l<d.size(); l++){
                            a.add(s.new Package(f1.get(j),f2.get(i), d.get(k), h.get(l)));     
                        }
                    }
                }
            }
                    
        
                
            return a;

           
        }
        
    }
    
}
