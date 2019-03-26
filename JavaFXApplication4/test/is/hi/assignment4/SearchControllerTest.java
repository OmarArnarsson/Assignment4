/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.assignment4;

import java.time.LocalDate;
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
public class SearchControllerTest {
    
    public SearchControllerTest() {
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
     * Test of processFlight method, of class SearchController.
     */
    @Test
    public void testProcessFlight() {
        System.out.println("processFlight");
        LocalDate start = null;
        LocalDate end = null;
        SearchController instance = new SearchController();
        instance.processFlight(start, end);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processDayTours method, of class SearchController.
     */
    @Test
    public void testProcessDayTours() {
        System.out.println("processDayTours");
        LocalDate start = null;
        LocalDate end = null;
        String filter = "";
        SearchController instance = new SearchController();
        instance.processDayTours(start, end, filter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processHotel method, of class SearchController.
     */
    @Test
    public void testProcessHotel() {
        System.out.println("processHotel");
        LocalDate start = null;
        LocalDate end = null;
        SearchController instance = new SearchController();
        instance.processHotel(start, end);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
