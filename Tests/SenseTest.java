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
public class SenseTest {
    
    public SenseTest() {
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
     * Test of getCondVal method, of class Sense.
     */
    @Test
    public void testGetCondVal() {
        System.out.println("getCondVal");
        Sense instance = new Sense(Sense.senseDir.HERE, 3, 6, Sense.condition.FOE);
        Sense.condition expResult = Sense.condition.FOE;
        Sense.condition result = instance.getCondVal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getS1 method, of class Sense.
     */
    @Test
    public void testGetS1() {
        System.out.println("getS1");
        Sense instance = new Sense(Sense.senseDir.HERE, 3, 6, Sense.condition.FOE);
        int expResult = 3;
        int result = instance.getS1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getS2 method, of class Sense.
     */
    @Test
    public void testGetS2() {
        System.out.println("getS2");
        Sense instance = new Sense(Sense.senseDir.HERE, 3, 6, Sense.condition.FOE);
        int expResult = 6;
        int result = instance.getS2();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSenseDirVal method, of class Sense.
     */
    @Test
    public void testGetSenseDirVal() {
        System.out.println("getSenseDirVal");
        Sense instance = new Sense(Sense.senseDir.HERE, 3, 6, Sense.condition.FOE);
        Sense.senseDir expResult = Sense.senseDir.HERE;
        Sense.senseDir result = instance.getSenseDirVal();
        assertEquals(expResult, result);
    }

    /**
     * Test of dirFromString method, of class Sense.
     */
    @Test
    public void testDirFromString() {
        System.out.println("dirFromString");
        String name = "HERE";
        Sense.senseDir expResult = Sense.senseDir.HERE;
        Sense.senseDir result = Sense.dirFromString(name);
        assertEquals(expResult, result);
        
        name = "AHEAD";
        expResult = Sense.senseDir.AHEAD;
        result = Sense.dirFromString(name);
        assertEquals(expResult, result);
        
        name = "LEFTAHEAD";
        expResult = Sense.senseDir.LEFTAHEAD;
        result = Sense.dirFromString(name);
        assertEquals(expResult, result);
        
        name = "RIGHTAHEAD";
        expResult = Sense.senseDir.RIGHTAHEAD;
        result = Sense.dirFromString(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of condFromString method, of class Sense.
     */
    @Test
    public void testCondFromString() {
        System.out.println("condFromString");
        String name = "FOE";
        Sense.condition expResult = Sense.condition.FOE;
        Sense.condition result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "FRIEND";
        expResult = Sense.condition.FRIEND;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "FRIENDWITHFOOD";
        expResult = Sense.condition.FRIENDWITHFOOD;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "FOEWITHFOOD";
        expResult = Sense.condition.FOEWITHFOOD;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "FOOD";
        expResult = Sense.condition.FOOD;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "ROCK";
        expResult = Sense.condition.ROCK;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "FRIEND";
        expResult = Sense.condition.FRIEND;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "MARKER0";
        expResult = Sense.condition.MARKER0;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "MARKER1";
        expResult = Sense.condition.MARKER1;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "MARKER2";
        expResult = Sense.condition.MARKER2;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "MARKER3";
        expResult = Sense.condition.MARKER3;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "MARKER4";
        expResult = Sense.condition.MARKER4;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "MARKER5";
        expResult = Sense.condition.MARKER5;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "FOEMARKER";
        expResult = Sense.condition.FOEMARKER;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "HOME";
        expResult = Sense.condition.HOME;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
        
        name = "FOEHOME";
        expResult = Sense.condition.FOEHOME;
        result = Sense.condFromString(name);
        assertEquals(expResult, result);
    }
}