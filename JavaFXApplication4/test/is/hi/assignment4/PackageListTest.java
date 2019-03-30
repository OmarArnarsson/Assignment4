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

    /**
     * Test of buildPackage method, of class PackageList.
     */
   /* @Test
    public void testBuildPackage() {
        System.out.println("buildPackage");
        ArrayList<Flight> f1 = null;
        ArrayList<Flight> f2 = null;
        ArrayList<Hotel> h = null;
        ArrayList<DayTour> d = null;
        String dest = "";
        String dep = "";
        Calendar go = null;
        Calendar home = null;
        PackageList instance = new PackageList();
        ArrayList<PackageList.Package> expResult = null;
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, dest, dep, go, home);
        assertEquals(expResult, result);

    }*/
    
    @Test
    public void testNull() {
        System.out.println("buildPackage");
      
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
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate);
        assertNull( result);
        
    }
    
    
    @Test
    public void testAllTheSame() {
        System.out.println("buildPackage");
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        

        Calendar depDate =  Calendar.getInstance();

        Calendar arrDate = Calendar.getInstance();

        for(int i = 0; i<5; i++){
            f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
            f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
            d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4));
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
        
        ArrayList<PackageList.Package> expResult = k;
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate);
        assertEquals(true, myComparison(k,result));
    
    }
    
    
    @Test
    public void testForValidity() {
        System.out.println("buildPackage");
        ArrayList<Flight> f1 = new ArrayList<Flight>();
        ArrayList<Flight> f2 = new ArrayList<Flight>();
        ArrayList<Hotel> h = new ArrayList<Hotel>();
        ArrayList<DayTour> d = new ArrayList<DayTour>();
        

        Calendar depDate =  Calendar.getInstance();

        Calendar arrDate = Calendar.getInstance();
        
        
        f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
        f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
        d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4));
        h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        
        f1.add(new Flight("Akureyri", depDate, "Reykjavík", arrDate, Math.random()*150000));
        f2.add(new Flight("Höfn", depDate, "Akureyri", arrDate, Math.random()*150000));
        d.add(new DayTour(depDate, arrDate, (int)Math.random()*10000, "Reykjavík", 4));
        h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        
        
                
        ArrayList<PackageList.Package> k = new ArrayList<PackageList.Package>();
        PackageList instance = new PackageList();
       
        for(int j = 0; j<2; j++){
            for(int i = 0; i<1; i++){
                for(int s = 0; s<2; s++){
                    for(int l = 0; l<2; l++){
                        k.add(instance.new Package(f1.get(j), f2.get(i), d.get(s), h.get(l)));
                    }
                }
            }
        }
        

        
        ArrayList<PackageList.Package> expResult = k;
        ArrayList<PackageList.Package> result = instance.buildPackage(f1, f2, h, d, "Akureyri", "Reykjavík", arrDate, depDate);
        assertEquals(true, myComparison(k,result));
        randomString();
    
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
                Flight d = first.get(i).f2;
                
                if(!(a.flightNr == b.flightNr)){
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
    
    
    public String randomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        
        return generatedString;
    }
    
}
