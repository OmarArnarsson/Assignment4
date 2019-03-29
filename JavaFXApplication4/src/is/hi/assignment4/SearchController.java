/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author stein
 */
public class SearchController implements Initializable {
    
    public DayTour[] dt;
    public Flight [] f;
    public Hotel [] h;
    public InterfaceController a;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dt = new DayTour[5];
        h = new Hotel[5];
        f = new Flight[5];
        
        for(int i = 0; i<dt.length; i++){
                
            dt[i] = new DayTour(LocalDate.now(), LocalDate.of(2019, Month.APRIL, i), 20000,
                    "Akureyri", i);
            
            h[i] = new Hotel(LocalDate.of(2019, Month.MARCH, i), LocalDate.of(2019, Month.APRIL,
                                              (int)Math.random()*6),(int) Math.random()*(30000 - 10000+1)+10000, 
                                              (int)Math.random()*5);
            f[i] = new Flight(LocalDate.of(2019, Month.MARCH, i), LocalDate.of(2019, Month.APRIL,
                                              (int)Math.random()*10), (int) Math.random()*(30000 - 10000+1)+10000);
            
            
            System.out.print("Hallo");
        }
    }    
    
    public ArrayList processFlight(LocalDate start, LocalDate end, int price){
        ArrayList<Flight> a = new ArrayList<Flight>();
        System.out.print(f.length);
        for(int i = 0; i<f.length; i++){
            if(start == f[i].getStart() && end == f[i].getEnd()){
                System.out.print(f[i]);
                a.add(f[i]);
            }
        }
        
        //getTours(end start,,.,..);
        for(int j = 0; j<a.size(); j++){
            System.out.print(a.get(j));
        }
        return a;
                
    }
    
    public void processDayTours(LocalDate start, LocalDate end){
        
    }
    
    public void processHotel(LocalDate start, LocalDate end){
        
    }
    
}
