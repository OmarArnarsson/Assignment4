/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Þorsteinn Óskarsson, Háskóli Íslands, tho85@hi.is
 */
public class PackageListTest {
    
    public PackageListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
    @Test
    public void testTypePriceFilteringAndWrongHotelOrFlightLocation() {
        System.out.println("testTypePriceFilteringAndWrongHotelOrFlightLocation");
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        

        Calendar depDate =  Calendar.getInstance();

        Calendar arrDate = Calendar.getInstance();
        
        Calendar wrong = Calendar.getInstance();
        wrong.add((Calendar.MONTH), 2);   
        String wrongString = "Höfn";
        
        String[] types = {"Outdoor", "Activity", "Nature", "Culture"};
        for(int i = 0; i<4; i++){
            f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, 50000.0));
            f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, 50000.0));
            d.add(new DayTour(depDate, arrDate, 40000, "Reykjavík", 4, types[i]));
            h.add(new Hotel(arrDate, arrDate, 40000, (int)Math.random()*5));
        }
        f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, 150000.0));
        f2.add(new Flight(wrongString, depDate, "Akureyri", arrDate, 30000.0));
        d.add(new DayTour(depDate, arrDate, 10000, "Reykjavík", 4, "Nature"));
        h.add(new Hotel(wrong, arrDate, 10000, (int)Math.random()*5));
        System.out.println(f1.size()+"  "+ f2.size()+"  "+ h.size()+"  "+  d.size());

        
        PackageList instance = new PackageList();
        
        
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate, 60000.0, 240000.0, "Outdoor");
        assertEquals(checkType(result, "Outdoor"), checkPrice(result,60000.0, 250000.0));
        
        // 64 because 4*4*1*4=80, 4 flights to because one is makes the package too expensive,
        // 4 flights back because one has wrong date, 1 daytour that matches Outdoor,
        // 4 hotels because one has wrong date
        assertEquals(64,result.size());
        
        
    }
    
    
    
    @Test
    public void testTypes() {
        System.out.println("testTypes");
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        

        Calendar depDate =  Calendar.getInstance();

        Calendar arrDate = Calendar.getInstance();
        String[] types = {"Outdoor", "Activity", "Nature", "Nature", "Culture"};
        for(int i = 0; i<5; i++){
            f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
            f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
            d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4, types[i]));
            h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        }
        
       
        ArrayList<PackageList.Package> k = new ArrayList<PackageList.Package>();
        
        PackageList instance = new PackageList();
        
        
        ArrayList<PackageList.Package> expResult = k;
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate, 0.0, 10000000.0, "Outdoor");
        assertTrue(checkType(result, "Outdoor"));
        
    }
    
    
    
    
    
    @Test
    public void testPriceRange() {
        System.out.println("testPriceRange");
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        

        Calendar depDate =  Calendar.getInstance();

        Calendar arrDate = Calendar.getInstance();
        String[] types = {"Outdoor", "Activity", "Nature", "Nature", "Culture"};
        for(int i = 0; i<5; i++){
            f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
            f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
            d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4, types[i]));
            h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        }       
       
        ArrayList<PackageList.Package> k = new ArrayList<PackageList.Package>();
        
        PackageList instance = new PackageList();
        
        
        ArrayList<PackageList.Package> expResult = k;
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate, 0.0, 100000.0, "");
        assertTrue(checkPrice(result, 0.0, 100000.0));
        
    }
    
    
    @Test
    public void testNull() {
        System.out.println("testNull");
      
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        
        String depLocation;
        Calendar depDate =  Calendar.getInstance();
        String arrLocation;
        Calendar arrDate = Calendar.getInstance();
        Double economyPrice;
        for(int i = 0; i<5; i++){
            f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, 10.0));
            f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
            
            h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        }
        
        PackageList instance = new PackageList();
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate, 0.0, 1000000000.0, "");
        assertNull( result);
        
    }
    
    
    @Test
    public void testAllTheSame() {
        System.out.println("testAllTheSame");
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        

        Calendar depDate =  Calendar.getInstance();

        Calendar arrDate = Calendar.getInstance();

        for(int i = 0; i<5; i++){
            f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
            f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
            d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4,""));
            h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        }
        ArrayList<PackageList.Package> k = new ArrayList<PackageList.Package>();
        PackageList instance = new PackageList();
        for(int j = 0; j<f1.size(); j++){
            for(int i = 0; i<f2.size(); i++){
                for(int s = 0; s<h.size(); s++){
                    for(int l = 0; l<d.size(); l++){
                        k.add(instance.new Package(f1.get(j), f2.get(i),d.get(s), h.get(l)));
                    }
                }
            }
        }
        
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate,0.0, 1000000000.0, "");
        assertEquals(true, myComparison(k,result));
    
    }
    
    
    @Test
    public void testWrongHotelDateAndWrongFlight() {
        System.out.println("testWrongHotelDateAndWrongFlight");
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        

        Calendar depDate =  Calendar.getInstance();

        Calendar arrDate = Calendar.getInstance();
        
        Calendar wrong = Calendar.getInstance();
        wrong.add((Calendar.MONTH), 2);   
        String wrongString = "Höfn";
        
        f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
        f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
        d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4,""));
        h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        
        f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
        f2.add(new Flight(wrongString, depDate, "Akureyri", arrDate, Math.random()*150000));
        d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4, ""));
        h.add(new Hotel(wrong, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        
        
                
        ArrayList<PackageList.Package> k = new ArrayList<PackageList.Package>();
        PackageList instance = new PackageList();
       
        for(int j = 0; j<2; j++){
            for(int i = 0; i<1; i++){
                for(int s = 0; s<2; s++){
                    for(int l = 0; l<1; l++){
                        k.add(instance.new Package(f1.get(j), f2.get(i), d.get(s), h.get(l)));
                    }
                }
            }
        }
        

        
        ArrayList<PackageList.Package> expResult = k;
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate, 0.0, 1000000000.0, "");
        assertEquals(true, myComparison(k,result));
    
    }
    
    
    // Function that checks some values of Flight, DayTour and Hotels for the purpose of
    // confirming that they are the same for testing purposes, its not a complete
    // comparison because it is not needed.
    public boolean myComparison(ArrayList<PackageList.Package> first, ArrayList<PackageList.Package> second){
        
        boolean ret = false;
        
        if(first.size() != second.size()) return ret;
             
        else {
            
            
            for(int i = 0; i<first.size(); i++){
                Flight a = first.get(i).f1;
                Flight b = second.get(i).f1;
                Flight c = first.get(i).f2;
                Flight d = second.get(i).f2;
                
                if(!(a.flightNr == b.flightNr && c.flightNr == d.flightNr)){
                    return ret;
                }
                
                Hotel h1 = first.get(i).h;
                Hotel h2 = second.get(i).h;
                
                if(!(h1.timeStart.equals(h2.timeStart) && h1.stars == h2.stars)){
                    return ret;
                }
                
                DayTour d1 = first.get(i).d;
                DayTour d2 = second.get(i).d;
                
                if(!(d1.id == d2.id)){
                    return ret;
                }
                
            }
        }
        return true;
       }
    
    
    public boolean checkPrice(ArrayList<PackageList.Package> a, Double low, Double high){
        
        boolean ret = true;
        
        for(int i = 0; i<a.size(); i++){
            if(!(a.get(i).getPrice() < high && a.get(i).getPrice() > low ) ){
                ret = false;
                break;
            }
        }
        
        return ret;
    }
    
    
    public boolean checkType(ArrayList<PackageList.Package> a, String type){
        
        boolean ret = true;
        
        for(int i = 0; i<a.size(); i++){
            if(!(a.get(i).d.type.equals(type)) ){
                ret = false;
                break;
            }
        }
        
        return ret;
    }
    
    
}
