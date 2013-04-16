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
public class AntBrainTest {
    
    public AntBrainTest() {
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
     * Test of checkAntBrainSyntax method, of class AntBrain.
     */
    @Test
    public void testCheckAntBrainSyntax() throws Exception {
        System.out.println("checkAntBrainSyntax");
        String antBrainFile = "cleverbrain1.brain";
        AntBrain instance = new AntBrain(antBrainFile);
        boolean expResult = true;
        boolean result = instance.checkAntBrainSyntax(antBrainFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstruction method, of class AntBrain.
     */
    @Test
    public void testGetInstruction() {
        System.out.println("getInstruction");
        String antBrainFile = "cleverbrain1.brain";
        AntBrain instance = new AntBrain(antBrainFile);
        int currState = 0;
        Sense expResult = new Sense(Sense.dirFromString("here"),32, 1,Sense.condFromString("home"));
        Sense result = (Sense)instance.getInstruction(0);
        
        assertEquals(expResult.getCondVal(), result.getCondVal());
        assertEquals(expResult.getS1(), result.getS1());
        assertEquals(expResult.getS2(), result.getS2());
        assertEquals(expResult.getSenseDirVal(), result.getSenseDirVal());
    }

    /**
     * Test of getName method, of class AntBrain.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String antBrainFile = "cleverbrain1.brain";
        AntBrain instance = new AntBrain(antBrainFile);
        String expResult = antBrainFile;
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}
