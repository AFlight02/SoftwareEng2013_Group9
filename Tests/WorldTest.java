package AntAttack_Group9;

import java.io.IOException;
import java.util.*;
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
public class WorldTest {
    
    public WorldTest() {
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
     * Test of readInWorld method, of class World.
     */
    @Test
    public void testReadInWorld() throws Exception {
        System.out.println("readInWorld");
        String worldFile = "1.world";
        World instance = new World();
        instance.readInWorld(worldFile);
    }

    /**
     * Test of checkValidWorld method, of class World.
     */
    @Test
    public void testCheckValidWorld() throws Exception {
        System.out.println("checkValidWorld");
        World instance = new World();
        instance.readInWorld("1.world");
        boolean expResult = true;
        boolean result = instance.checkValidWorld();
        assertEquals(expResult, result);
        
        instance = new World();
        instance.generateRandomContestWorld();
        expResult = true;
        result = instance.checkValidWorld();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkValidForTournament method, of class World.
     */
    @Test
    public void testCheckValidForTournament() throws Exception {
        System.out.println("checkValidForTournament");
        World instance = new World();
        instance.readInWorld("2.world");
        boolean expResult = true;
        boolean result = instance.checkValidForTournament();
        assertEquals(expResult, result);
        
        instance = new World();
        instance.generateRandomContestWorld();
        expResult = true;
        result = instance.checkValidForTournament();
        assertEquals(expResult, result);
    }

    /**
     * Test of generateRandomContestWorld method, of class World.
     */
    @Test
    public void testGenerateRandomContestWorld() {
        System.out.println("generateRandomContestWorld");
        World instance = new World();
        instance.generateRandomContestWorld();
    }

    /**
     * Test of checkCellStatus method, of class World.
     */
    @Test
    public void testCheckCellStatus() throws IOException {
        System.out.println("checkCellStatus");
        int[] cell = new int[2];
        cell[0] = 1;
        cell[1] = 1;
        Sense.condition cond = Sense.condFromString("FRIEND");
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant a = new Ant(ab,false,0);
        World instance = new World();
        
        boolean expResult = false;
        boolean result = instance.checkCellStatus(cell, cond, a);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCell method, of class World.
     */
    @Test
    public void testGetCell() throws Exception {
        System.out.println("getCell");
        int[] cell = new int[2];
        cell[0] = 0;
        cell[1] = 0;
        World instance = new World();
        instance.readInWorld("1.world");
        Cell expResult = new Cell(0,0);
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        expResult.setAnt(new Ant(ab,false,0));
        Cell result = instance.getCell(cell);
        assertEquals(expResult.getFood(), result.getFood());
    }

    /**
     * Test of printWorld method, of class World.
     */
    @Test
    public void testPrintWorld() {
        System.out.println("printWorld");
        World instance = new World();
        instance.generateRandomContestWorld();
        instance.printWorld();
    }

    /**
     * Test of placeAnts method, of class World.
     */
    @Test
    public void testPlaceAnts() throws IOException, Exception {
        System.out.println("placeAnts");
        AntBrain blackBrain = new AntBrain("cleverbrain1.brain");
        AntBrain redBrain = new AntBrain("cleverbrain2.brain");
        World instance = new World();
        instance.generateRandomContestWorld();
        //instance.readInWorld("2.world");
        List expResult = new ArrayList<Ant>();
        List result = instance.placeAnts(blackBrain, redBrain);
        for(Object l:result){
            System.out.println("HERE: "+l);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of replaceFood method, of class World.
     */
    @Test
    public void testReplaceFood() {
        System.out.println("replaceFood");
        World instance = new World();
        instance.generateRandomContestWorld();
        instance.replaceFood();
        
        int expResult = 0;
        int result = instance.getFoodNum();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnthillCells method, of class World.
     */
    @Test
    public void testGetAnthillCells() {
        System.out.println("getAnthillCells");
        World instance = new World();
        instance.generateRandomContestWorld();
        List expResult = new ArrayList<>();
        List result = instance.getAnthillCells();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFoodCells method, of class World.
     */
    @Test
    public void testGetFoodCells() {
        System.out.println("getFoodCells");
        World instance = new World();
        instance.generateRandomContestWorld();
        List expResult = new ArrayList<>();
        List result = instance.getFoodCells();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFoodNum method, of class World.
     */
    @Test
    public void testGetFoodNum() {
        System.out.println("getFoodCells");
        World instance = new World();
        instance.generateRandomContestWorld();
        int expResult = 0;
        int result = instance.getFoodNum();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetWorld method, of class World.
     */
    @Test
    public void testResetWorld() {
        System.out.println("resetWorld");
        World instance = new World();
        instance.generateRandomContestWorld();
        instance.resetWorld();
    }
}
