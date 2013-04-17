package AntAttack_Group9;

import java.io.IOException;
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
    public void testKill() throws IOException {
        System.out.println("kill");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,1);

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
    public void testIsAlive() throws IOException {
        System.out.println("isAlive");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);

        boolean expResult = true;
        boolean result = instance.isAlive();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Ant.
     */
    @Test
    public void testGetPosition() throws IOException {
        System.out.println("getPosition");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
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
    public void testGetResting() throws IOException {
        System.out.println("getResting");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
        int expResult = 0;
        int result = instance.getResting();
        assertEquals(expResult, result);
    }

    /**
     * Test of decrementResting method, of class Ant.
     */
    @Test
    public void testDecrementResting() throws IOException {
        System.out.println("decrementResting");
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(ab,true,0);
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
    public void testGetDirection() throws IOException {
        System.out.println("getDirection");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
        int expResult = 0;
        int result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFood method, of class Ant.
     */
    @Test
    public void testGetFood() throws IOException {
        System.out.println("getFood");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
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
    public void testGetColour() throws IOException {
        System.out.println("getColour");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
        boolean expResult = true;
        boolean result = instance.getColour();
        assertEquals(expResult, result);
        
        instance = new Ant(b,false,0);
        expResult = false;
        result = instance.getColour();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostition method, of class Ant.
     */
    @Test
    public void testSetPostition() throws IOException {
        System.out.println("setPostition");
        int[] newPos = new int[2];
        newPos[0] = 1;
        newPos[1] = 1;
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(ab,true,0);
        instance.setPostition(newPos);
        
        // check getter to ensure setter works
        int[] result = instance.getPosition();
        assertArrayEquals(newPos, result);
    }

    /**
     * Test of setState method, of class Ant.
     */
    @Test
    public void testSetState() throws IOException {
        System.out.println("setState");
        int newState = 9;
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
        instance.setState(newState);
    }

    /**
     * Test of setResting method, of class Ant.
     */
    @Test
    public void testSetResting() throws IOException {
        System.out.println("setResting");
        int resting = 5;
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(ab,true,0);
        instance.setResting(resting);
        int result = instance.getResting();
        assertEquals(resting, result);
    }

    /**
     * Test of setDirection method, of class Ant.
     */
    @Test
    public void testSetDirection() throws IOException {
        System.out.println("setDirection");
        int dir = 5;
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
        instance.setDirection(dir);
        int result = instance.getDirection();
        assertEquals(dir, result);
    }

    /**
     * Test of setFood method, of class Ant.
     */
    @Test
    public void testSetFood() throws IOException {
        System.out.println("setFood");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
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
    public void testTurn() throws IOException {
        System.out.println("turn");
        
        AntBrain b = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(b,true,0);
        
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
    public void testGetInstruction() throws IOException {
        System.out.println("getInstruction");
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant instance = new Ant(ab,true,0);
        Sense expResult = new Sense(Sense.dirFromString("here"),32, 1,Sense.condFromString("home"));
        Sense result = (Sense)instance.getInstruction();
        assertEquals(expResult.getCondVal(), result.getCondVal());
        assertEquals(expResult.getS1(), result.getS1());
        assertEquals(expResult.getS2(), result.getS2());
        assertEquals(expResult.getSenseDirVal(), result.getSenseDirVal());
    }
}
