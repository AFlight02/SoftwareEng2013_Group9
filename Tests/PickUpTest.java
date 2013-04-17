/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AntAttack_Group9;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class PickUpTest {
    
    public PickUpTest() {
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
     * Test of getS1 method, of class PickUp.
     */
    @Test
    public void testGetS1() {
        System.out.println("getS1");
        PickUp instance = new PickUp(6,9);
        int expResult = 6;
        int result = instance.getS1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getS2 method, of class PickUp.
     */
    @Test
    public void testGetS2() {
        System.out.println("getS2");
        PickUp instance = new PickUp(6,9);
        int expResult = 9;
        int result = instance.getS2();
        assertEquals(expResult, result);
    }
}