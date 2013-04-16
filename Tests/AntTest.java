package AntAttack_Group9;

import java.util.concurrent.locks.Condition;
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
public class AntTest {
    
    public AntTest() {
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
     * Test of kill method, of class Ant.
     */
    @Test
    public void testKill() {
        System.out.println("kill");
        
        Ant instance = new Ant(true);

        boolean expResult = true;
        boolean result = instance.isAlive();
        assertEquals(expResult, result);
        
        instance.kill();
        expResult = false;
        result = instance.isAlive();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isAlive method, of class Ant.
     */
    @Test
    public void testIsAlive() {
        System.out.println("isAlive");
        
        Ant instance = new Ant(true);

        boolean expResult = true;
        boolean result = instance.isAlive();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Ant.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        
        Ant instance = new Ant(true);

        int expResult = 0;
        int result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Ant.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Ant instance = new Ant(true);
        int[] result = instance.getPosition();
        assertEquals(-1, result[0]);
        assertEquals(-1, result[1]);
        
        int[] newPos = new int[2];
        newPos[0] = 1;
        newPos[1] = 1;
        instance.setPostition(newPos);
        result = instance.getPosition();
        assertEquals(1, result[0]);
        assertEquals(1, result[1]);
    }

    /**
     * Test of getResting method, of class Ant.
     */
    @Test
    public void testGetResting() {
        System.out.println("getResting");
        Ant instance = new Ant(true);
        int expResult = 0;
        int result = instance.getResting();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID method, of class Ant.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Ant instance = new Ant(true);
        int expResult = -1;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of decrementResting method, of class Ant.
     */
    @Test
    public void testDecrementResting() {
        System.out.println("decrementResting");
        Ant instance = new Ant(true);
        instance.setResting(5);
        instance.decrementResting();
        
        int expResult = 4;
        int result = instance.getResting();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDirection method, of class Ant.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Ant instance = new Ant(true);
        int expResult = 0;
        int result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFood method, of class Ant.
     */
    @Test
    public void testGetFood() {
        System.out.println("getFood");
        Ant instance = new Ant(true);
        boolean expResult = false;
        boolean result = instance.getFood();
        assertEquals(expResult, result);
        
        instance.setFood(true);
        expResult = true;
        result = instance.getFood();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColour method, of class Ant.
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour");
        Ant instance = new Ant(true);
        boolean expResult = true;
        boolean result = instance.getColour();
        assertEquals(expResult, result);
        
        instance = new Ant(false);
        expResult = false;
        result = instance.getColour();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Ant.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int newId = 0;
        Ant instance = new Ant(true);
        
        instance.setId(newId);
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        
        instance.setId(9);
        expResult = 9;
        result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostition method, of class Ant.
     */
    @Test
    public void testSetPostition() {
        System.out.println("setPostition");
        int[] newPos = new int[2];
        newPos[0] = 1;
        newPos[1] = 1;
        Ant instance = new Ant(true);
        instance.setPostition(newPos);
        
        // check getter to ensure setter works
        int[] result = instance.getPosition();
        assertArrayEquals(newPos, result);
    }

    /**
     * Test of setBrain method, of class Ant.
     */
    @Test
    public void testSetBrain() {
        System.out.println("setBrain");
        AntBrain newBrain = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(true);
        instance.setBrain(newBrain);
    }

    /**
     * Test of setState method, of class Ant.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        int newState = 9;
        Ant instance = new Ant(true);
        instance.setState(newState);
        int result = instance.getState();
        assertEquals(newState, result);
    }

    /**
     * Test of setResting method, of class Ant.
     */
    @Test
    public void testSetResting() {
        System.out.println("setResting");
        int resting = 5;
        Ant instance = new Ant(true);
        instance.setResting(resting);
        int result = instance.getResting();
        assertEquals(resting, result);
    }

    /**
     * Test of setDirection method, of class Ant.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        int dir = 5;
        Ant instance = new Ant(true);
        instance.setDirection(dir);
        int result = instance.getDirection();
        assertEquals(dir, result);
    }

    /**
     * Test of setFood method, of class Ant.
     */
    @Test
    public void testSetFood() {
        System.out.println("setFood");
        Ant instance = new Ant(true);
        boolean expResult = false;
        boolean result = instance.getFood();
        assertEquals(expResult, result);
        
        instance.setFood(true);
        expResult = true;
        result = instance.getFood();
        assertEquals(expResult, result);
    }

    /**
     * Test of turn method, of class Ant.
     */
    @Test
    public void testTurn() {
        System.out.println("turn");
        Ant instance = new Ant(true);
        
        Turn.direction dir = Turn.dirFromString("left");
        int expResult = 5;
        int result = instance.turn(dir);
        assertEquals(expResult, result);
        
        dir = Turn.dirFromString("right");
        expResult = 1;
        result = instance.turn(dir);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstruction method, of class Ant.
     */
    @Test
    public void testGetInstruction() {
        System.out.println("getInstruction");
        Ant instance = new Ant(true);
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        instance.setBrain(ab);
        Sense expResult = new Sense(Sense.dirFromString("here"),32, 1,Sense.condFromString("home"));
        Sense result = (Sense)instance.getInstruction();
        assertEquals(expResult.getCondVal(), result.getCondVal());
        assertEquals(expResult.getS1(), result.getS1());
        assertEquals(expResult.getS2(), result.getS2());
        assertEquals(expResult.getSenseDirVal(), result.getSenseDirVal());
    }
}
