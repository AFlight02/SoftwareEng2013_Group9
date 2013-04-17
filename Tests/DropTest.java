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
public class DropTest {
    
    public DropTest() {
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
     * Test of getS1 method, of class Drop.
     */
    @Test
    public void testGetS1() {
        System.out.println("getS1");
        Drop instance = new Drop(3);
        int expResult = 3;
        int result = instance.getS1();
        assertEquals(expResult, result);
    }
}