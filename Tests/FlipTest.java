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
public class FlipTest {
    
    public FlipTest() {
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
     * Test of getRandom method, of class Flip.
     */
    @Test
    public void testGetRandom() {
        System.out.println("getRandom");
        Flip instance = new Flip(3,6,9);
        int expResult = 3;
        int result = instance.getRandom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getS1 method, of class Flip.
     */
    @Test
    public void testGetS1() {
        System.out.println("getS1");
        Flip instance = new Flip(3,6,9);
        int expResult = 6;
        int result = instance.getS1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getS2 method, of class Flip.
     */
    @Test
    public void testGetS2() {
        System.out.println("getS2");
        Flip instance = new Flip(3,6,9);
        int expResult = 9;
        int result = instance.getS2();
        assertEquals(expResult, result);
    }
}