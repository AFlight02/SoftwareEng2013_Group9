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
public class TurnTest {
    
    public TurnTest() {
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
     * Test of getTurnDir method, of class Turn.
     */
    @Test
    public void testGetTurnDir() {
        System.out.println("getTurnDir");
        Turn instance = new Turn(Turn.direction.LEFT, 6);
        Turn.direction expResult = Turn.direction.LEFT;
        Turn.direction result = instance.getTurnDir();
        assertEquals(expResult, result);
    }

    /**
     * Test of getS1 method, of class Turn.
     */
    @Test
    public void testGetS1() {
        System.out.println("getS1");
        Turn instance = new Turn(Turn.direction.LEFT, 6);
        int expResult = 6;
        int result = instance.getS1();
        assertEquals(expResult, result);
    }

    /**
     * Test of dirFromString method, of class Turn.
     */
    @Test
    public void testDirFromString() {
        System.out.println("dirFromString");
        String name = "LEFT";
        Turn.direction expResult = Turn.direction.LEFT;
        Turn.direction result = Turn.dirFromString(name);
        assertEquals(expResult, result);
        
        name = "RIGHT";
        expResult = Turn.direction.RIGHT;
        result = Turn.dirFromString(name);
        assertEquals(expResult, result);
    }
}