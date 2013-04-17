package AntAttack_Group9;

import AntAttack_Group9.Sense.senseDir;
import java.io.IOException;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class CellTest {
    
    public CellTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of adjacentCell method, of class Cell.
     */
    @Test
    public void testAdjacentCell() {
        System.out.println("adjacentCell");
        Cell instance = new Cell(2,2);
        
        int[] result = instance.adjacentCell(0);
        assertEquals(2, result[0]);
        assertEquals(3, result[1]);
        
        result = instance.adjacentCell(1);
        assertEquals(3, result[0]);
        assertEquals(2, result[1]);
        
        result = instance.adjacentCell(2);
        assertEquals(3, result[0]);
        assertEquals(1, result[1]);
        
        result = instance.adjacentCell(3);
        assertEquals(2, result[0]);
        assertEquals(1, result[1]);
        
        result = instance.adjacentCell(4);
        assertEquals(1, result[0]);
        assertEquals(1, result[1]);
        
        result = instance.adjacentCell(5);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        
        instance = new Cell(1,1);
        
        result = instance.adjacentCell(0);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        
        result = instance.adjacentCell(1);
        assertEquals(2, result[0]);
        assertEquals(2, result[1]);
        
        result = instance.adjacentCell(2);
        assertEquals(2, result[0]);
        assertEquals(1, result[1]);
        
        result = instance.adjacentCell(3);
        assertEquals(1, result[0]);
        assertEquals(0, result[1]);
        
        result = instance.adjacentCell(4);
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
        
        result = instance.adjacentCell(5);
        assertEquals(0, result[0]);
        assertEquals(2, result[1]);
        
    }
    
    
    /**
     * Test of sensedCell method, of class Cell.
     */
    @Test
    public void testSensedCell() throws IOException {
        System.out.println("sensedCell");
        Cell instance = new Cell(2,2);
        int[] pos = new int[2];
        pos[0] = 2;
        pos[1] = 2;
        int dir = 0;
        Sense.senseDir sD = Sense.dirFromString("here");
        int[] result = instance.sensedCell(pos, dir, sD);
        assertEquals(2, result[0]);
        assertEquals(2, result[1]);
        
        sD = Sense.dirFromString("ahead");
        result = instance.sensedCell(pos, dir, sD);
        assertEquals(2, result[0]);
        assertEquals(3, result[1]);
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant ant = new Ant(ab, true, 0);
        instance.setAnt(ant);
        
        sD = Sense.dirFromString("leftahead");
        result = instance.sensedCell(pos, dir, sD);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        
        sD = Sense.dirFromString("RIGHTAHEAD");
        result = instance.sensedCell(pos, dir, sD);
        assertEquals(3, result[0]);
        assertEquals(2, result[1]);
        
        
    }

    /**
     * Test of calculateAdjacentAnts method, of class Cell.
     */
    @Test
    public void testCalculateAdjacentAnts() throws Exception {
        System.out.println("calculateAdjacentAnts");
        World world = new World();
        world.generateRandomContestWorld();
        Cell instance = new Cell(2,2);
        
        Cell cell0 = new Cell(2,3);
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant a0 = new Ant(ab,true,0);
        cell0.setAnt(a0);
        world.setCell(2,3,cell0);
        
        Cell cell1 = new Cell(3,2);
        Ant a1 = new Ant(ab,true,0);
        cell1.setAnt(a1);
        world.setCell(3,2,cell1);
        
        Cell cell2 = new Cell(3,1);
        Ant a2 = new Ant(ab,true,0);
        cell2.setAnt(a2);
        world.setCell(3,1,cell2);
        
        Cell cell3 = new Cell(2,1);
        Ant a3 = new Ant(ab,true,0);
        cell3.setAnt(a3);
        world.setCell(2,1,cell3);
        
        Cell cell4 = new Cell(1,1);
        Ant a4 = new Ant(ab,true,0);
        cell4.setAnt(a4);
        world.setCell(1,1,cell4);
        
        Cell cell5 = new Cell(1,2);
        Ant a5 = new Ant(ab,true,0);
        cell5.setAnt(a5);
        world.setCell(1,2,cell5);
        
        instance.calculateAdjacentAnts(world);
        int expRes = 6;
        int result = instance.getAdjacentAntsBlack();
        assertEquals(expRes, result);
        
    }

    /**
     * Test of getAdjacentAntsBlack method, of class Cell.
     */
    @Test
    public void testGetAdjacentAntsBlack() {
        System.out.println("getAdjacentAntsBlack");
        Cell instance = new Cell(0,0);
        int expResult = 0;
        int result = instance.getAdjacentAntsBlack();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdjacentAntsRed method, of class Cell.
     */
    @Test
    public void testGetAdjacentAntsRed() {
        System.out.println("getAdjacentAntsRed");
        Cell instance = new Cell(0,0);
        int expResult = 0;
        int result = instance.getAdjacentAntsRed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRock method, of class Cell.
     */
    @Test
    public void testGetRock() {
        System.out.println("getRock");
        Cell instance = new Cell(0,0);
        boolean expResult = false;
        boolean result = instance.getRock();
        assertEquals(expResult, result);
        
        instance.setRock(true);
        expResult = true;
        result = instance.getRock();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRock method, of class Cell.
     */
    @Test
    public void testSetRock() {
        System.out.println("setRock");
        
        Cell instance = new Cell(0,0);
        instance.setRock(false);
        boolean expResult = false;
        boolean result = instance.getRock();
        assertEquals(expResult, result);
        
        instance.setRock(true);
        expResult = true;
        result = instance.getRock();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFood method, of class Cell.
     */
    @Test
    public void testGetFood() {
        System.out.println("getFood");
        Cell instance = new Cell(0,0);
        int expResult = 0;
        int result = instance.getFood();
        assertEquals(expResult, result);
        
        instance.setFood(5);
        expResult = 5;
        result = instance.getFood();
        assertEquals(expResult, result);
        
        instance.setFood(100);
        expResult = 100;
        result = instance.getFood();
        assertEquals(expResult, result);
        
        instance.setFood(1000000000);
        expResult = 1000000000;
        result = instance.getFood();
        assertEquals(expResult, result);
        
        instance.setFood(0);
        instance.setFood(-100);
        expResult = 0;
        result = instance.getFood();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setFood method, of class Cell.
     */
    @Test
    public void testSetFood() {
        System.out.println("setFood");
        int food = 0;
        Cell instance = new Cell(0,0);
        instance.setFood(food);
        int expResult = 0;
        int result = instance.getFood();
        assertEquals(expResult, result);
    }

    /**
     * Test of decrementFood method, of class Cell.
     */
    @Test
    public void testDecrementFood() {
        System.out.println("removeFood");
        Cell instance = new Cell(0,0);
        instance.setFood(100);
        instance.decrementFood();
        int expResult = 99;
        int result = instance.getFood();
        assertEquals(expResult, result);
        
        instance.setFood(0);
        instance.setFood(-1);
        instance.decrementFood();
        expResult = 0;
        result = instance.getFood();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnt method, of class Cell.
     */
    @Test
    public void testGetAnt() throws IOException {
        System.out.println("getAnt");
        Cell instance = new Cell(0,0);
        Ant expResult = null;
        Ant result = instance.getAnt();
        assertEquals(expResult, result);
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant ant = new Ant(ab, true, 0);
        instance.setAnt(ant);
        
        expResult = ant;
        result = instance.getAnt();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAnt method, of class Cell.
     */
    @Test
    public void testSetAnt() throws IOException {
        System.out.println("setAnt");
        Cell instance = new Cell(0,0);

        Ant expResult = null;
        Ant result = instance.getAnt();
        assertEquals(expResult, result);
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant ant = new Ant(ab, true, 0);
        instance.setAnt(ant);
        
        expResult = ant;
        result = instance.getAnt();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of removeAnt method, of class Cell.
     */
    @Test
    public void testRemoveAnt() throws IOException {
        System.out.println("removeAnt");
        Cell instance = new Cell(0,0);

        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant ant = new Ant(ab, true, 0);
        instance.setAnt(ant);
        instance.removeAnt();
        
        Ant expResult = null;
        Ant result = instance.getAnt();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAnthill method, of class Cell.
     */
    @Test
    public void testGetAnthill() {
        System.out.println("getAnthill");
        Cell instance = new Cell(0,0);
        String expResult = "none";
        String result = instance.getAnthill();
        assertEquals(expResult, result);
        
        instance.setAnthill("red");
        expResult = "red";
        result = instance.getAnthill();
        assertEquals(expResult, result);
        
        instance.setAnthill("black");
        expResult = "black";
        result = instance.getAnthill();
        assertEquals(expResult, result);
        
        instance.setAnthill("something else");
        expResult = "none";
        result = instance.getAnthill();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnthill method, of class Cell.
     */
    @Test
    public void testSetAnthill() {
        System.out.println("setAnthill");
        Cell instance = new Cell(0,0);
        String expResult = "none";
        String result = instance.getAnthill();
        assertEquals(expResult, result);
        
        instance.setAnthill("red");
        expResult = "red";
        result = instance.getAnthill();
        assertEquals(expResult, result);
        
        instance.setAnthill("black");
        expResult = "black";
        result = instance.getAnthill();
        assertEquals(expResult, result);
        
        instance.setAnthill("something else");
        expResult = "none";
        result = instance.getAnthill();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMarkers method, of class Cell.
     */
    @Test
    public void testGetMarkers() {
        System.out.println("getMarkers");
        boolean colour = false;
        Cell instance = new Cell(0,0);
        boolean[] expResult = new boolean[6];
        boolean[] result = instance.getMarkers(colour);
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        assertEquals(expResult[2], result[2]);
        assertEquals(expResult[3], result[3]);
        assertEquals(expResult[4], result[4]);
        assertEquals(expResult[5], result[5]);
        
        instance.addRedMarker(3);
        result = instance.getMarkers(colour);
        assertEquals(true, result[3]);
    }

    /**
     * Test of addRedMarker method, of class Cell.
     */
    @Test
    public void testAddRedMarker() {
        System.out.println("addRedMarker");
        Cell instance = new Cell(0,0);
        instance.addRedMarker(0);
        instance.addRedMarker(1);
        instance.addRedMarker(2);
        instance.addRedMarker(3);
        instance.addRedMarker(4);
        instance.addRedMarker(5);
        
        boolean[] result = instance.getMarkers(false);
        assertEquals(true, result[0]);
        assertEquals(true, result[1]);
        assertEquals(true, result[2]);
        assertEquals(true, result[3]);
        assertEquals(true, result[4]);
        assertEquals(true, result[5]);
    }

    /**
     * Test of addBlackMarker method, of class Cell.
     */
    @Test
    public void testAddBlackMarker() {
        System.out.println("addBlackMarker");
        Cell instance = new Cell(0,0);
        instance.addBlackMarker(0);
        instance.addBlackMarker(1);
        instance.addBlackMarker(2);
        instance.addBlackMarker(3);
        instance.addBlackMarker(4);
        instance.addBlackMarker(5);
        
        boolean[] result = instance.getMarkers(true);
        assertEquals(true, result[0]);
        assertEquals(true, result[1]);
        assertEquals(true, result[2]);
        assertEquals(true, result[3]);
        assertEquals(true, result[4]);
        assertEquals(true, result[5]);
    }

    /**
     * Test of removeRedMarker method, of class Cell.
     */
    @Test
    public void testRemoveRedMarker() {
        System.out.println("removeRedMarker");
        Cell instance = new Cell(0,0);
        instance.addRedMarker(0);
        instance.addRedMarker(1);
        instance.addRedMarker(2);
        instance.addRedMarker(3);
        instance.addRedMarker(4);
        instance.addRedMarker(5);
        
        boolean[] result = instance.getMarkers(false);
        assertEquals(true, result[0]);
        assertEquals(true, result[1]);
        assertEquals(true, result[2]);
        assertEquals(true, result[3]);
        assertEquals(true, result[4]);
        assertEquals(true, result[5]);
        
        instance.removeRedMarker(0);
        instance.removeRedMarker(1);
        instance.removeRedMarker(2);
        instance.removeRedMarker(3);
        instance.removeRedMarker(4);
        instance.removeRedMarker(5);
        
        result = instance.getMarkers(false);
        assertEquals(false, result[0]);
        assertEquals(false, result[1]);
        assertEquals(false, result[2]);
        assertEquals(false, result[3]);
        assertEquals(false, result[4]);
        assertEquals(false, result[5]);
    }

    /**
     * Test of removeBlackMarker method, of class Cell.
     */
    @Test
    public void testRemoveBlackMarker() {
        System.out.println("removeBlackMarker");
        Cell instance = new Cell(0,0);
        instance.addBlackMarker(0);
        instance.addBlackMarker(1);
        instance.addBlackMarker(2);
        instance.addBlackMarker(3);
        instance.addBlackMarker(4);
        instance.addBlackMarker(5);
        
        boolean[] result = instance.getMarkers(true);
        assertEquals(true, result[0]);
        assertEquals(true, result[1]);
        assertEquals(true, result[2]);
        assertEquals(true, result[3]);
        assertEquals(true, result[4]);
        assertEquals(true, result[5]);
        
        instance.removeBlackMarker(0);
        instance.removeBlackMarker(1);
        instance.removeBlackMarker(2);
        instance.removeBlackMarker(3);
        instance.removeBlackMarker(4);
        instance.removeBlackMarker(5);
        
        result = instance.getMarkers(true);
        assertEquals(false, result[0]);
        assertEquals(false, result[1]);
        assertEquals(false, result[2]);
        assertEquals(false, result[3]);
        assertEquals(false, result[4]);
        assertEquals(false, result[5]);
    }

    /**
     * Test of isEmpty method, of class Cell.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Cell instance = new Cell(0,0);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        
        instance.setAnthill("red");
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCellSymbol method, of class Cell.
     */
    @Test
    public void testGetCellSymbol() throws IOException {
        System.out.println("getCellSymbol");
        Cell instance = new Cell(0,0);
        
        String expResult = ".";
        String result = instance.getCellSymbol();
        assertEquals(expResult, result);
        
        expResult = "#";
        instance.setRock(true);
        result = instance.getCellSymbol();
        assertEquals(expResult, result);
        instance.setRock(false);
        
        instance.setFood(5);
        expResult = "5";
        result = instance.getCellSymbol();
        assertEquals(expResult, result);
        instance.setFood(0);
        
        AntBrain ab = new AntBrain("cleverbrain1.brain");
        Ant ant = new Ant(ab, true, 0);
        instance.setAnt(ant);
        expResult = "a";
        result = instance.getCellSymbol();
        assertEquals(expResult, result);
        instance.removeAnt();
        
        instance.setAnthill("red");
        expResult = "+";
        result = instance.getCellSymbol();
        assertEquals(expResult, result);
        instance.setAnthill("none");
        
        instance.setAnthill("black");
        expResult = "-";
        result = instance.getCellSymbol();
        assertEquals(expResult, result);
        instance.setAnthill("none");
        
    }
}
