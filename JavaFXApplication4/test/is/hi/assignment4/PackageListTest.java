/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.util.ArrayList;
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
    public void testBuildPackage() {
        System.out.println("buildPackage");
        ArrayList<Flight> f1 = null;
        ArrayList<Flight> f2 = null;
        ArrayList<Hotel> h = null;
        ArrayList<DayTour> d = null;
        PackageList instance = new PackageList();
        ArrayList<PackageList> expResult = null;
        ArrayList<PackageList> result = instance.buildPackage(f1, f2, h, d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
