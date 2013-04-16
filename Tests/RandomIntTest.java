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
public class RandomIntTest {
    
    public RandomIntTest() {
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
     * Test of nextInt method, of class RandomInt.
     */
    @Test
    public void testNextInt() {
        System.out.println("nextInt");
        RandomInt instance = new RandomInt();
        
        int expResult = 0;
        int result = instance.nextInt(1);
        assertEquals(expResult, result);
        
        expResult = 0;
        result = instance.nextInt(2);
        assertEquals(expResult, result);
        
        expResult = 6;
        result = instance.nextInt(10);
        assertEquals(expResult, result);
        
        expResult = 5575;
        result = instance.nextInt(8754);
        assertEquals(expResult, result);
        
        expResult = 100;
        result = instance.nextInt(8754);
        assertEquals(expResult, result);
        
        
        instance = new RandomInt();
        expResult = 7193;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 2932;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 10386;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 5575;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 100;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 15976;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 430;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 9740;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 9449;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
        
        expResult = 1636;
        result = instance.nextInt(16385);
        assertEquals(expResult, result);
    }
}
