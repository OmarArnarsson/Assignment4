/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.util.ArrayList;
import java.util.Calendar;
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
    @Test
    public void testBuildPackageFail1() {
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
            f1.add(new Flight("Akureyri", depDate, "Reykjavik", arrDate, Math.random()*150000));
            f2.add(new Flight("Reykjavík", depDate, "Akureyri", arrDate, Math.random()*150000));
            
            h.add(new Hotel(arrDate, arrDate, (int)Math.random()*10000, (int)Math.random()*5));
        }
        
        
        PackageList instance = new PackageList();
        ArrayList<PackageList> expResult = null;
        ArrayList<PackageList> result = instance.buildPackage(f1, f2, h, d);
        assertNull( result);
        
    }
    
}
