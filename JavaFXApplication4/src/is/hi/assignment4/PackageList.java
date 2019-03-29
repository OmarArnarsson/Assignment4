/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.util.ArrayList;

/**
 *
 * @author Þorsteinn Óskarsson, Háskóli Íslands, tho85@hi.is
 */
public class PackageList {
    
    public ArrayList<PackageList> list;
    
    public PackageList(){
        this.list = new ArrayList<PackageList>();
    }
    
    public ArrayList<PackageList> buildPackage (ArrayList<Flight> f1, ArrayList<Flight> f2, ArrayList<Hotel> h, ArrayList<DayTour> d){
        
        if(f1.isEmpty() || f2.isEmpty() || h.isEmpty() || d.isEmpty()){
            System.out.print("Sorry");
            return null;
        }
        else {
            
        }
        return null;
    }
    
}
