/**
 * AntAttack.java Main file for interacting with and utilising the Ant Tournament
 * system.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */
package AntAttack_Group9;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AntAttack {

    private static GUI gui;
    private static Tournament tournament;

    /**
     * 
     */
    public AntAttack() {
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        AntBrain placeholder = new AntBrain("cleverbrain1.brain");
        World testWorld = new World();
        /*
        testWorld.cells = new Cell[2][3];
        testWorld.height = 2;
        testWorld.width = 3;
        testWorld.cells[0][0] = new Cell(0,0);
        testWorld.cells[0][0].rock = true;
        testWorld.cells[0][1] = new Cell(0,1);
        testWorld.cells[0][2] = new Cell(0,2);
        testWorld.cells[0][2].ant = new Ant(placeholder, true, 0);
        testWorld.cells[1][0] = new Cell(1,0);
        testWorld.cells[1][0].ant = new Ant(placeholder, false, 0);
        testWorld.cells[1][1] = new Cell(1,1);
        testWorld.cells[1][1].food = 3;
        testWorld.cells[1][2] = new Cell(1,2);
        testWorld.cells[1][2].food = 1;
        */
        testWorld.generateRandomContestWorld();
        testWorld.printWorld();
        
        AntAttack newGame = new AntAttack();
        newGame.newTournament();
        newGame.newGame();

        gui = new GUI(testWorld);
        gui.initHex();
    }

    /**
     * 
     */
    public void initialiseGUI() {
        // Call to GUI constructor and initialisation methods.
    }

    /**
     * 
     */
    public void newTournament() {
        List<World> w = new ArrayList();
        tournament = new Tournament(w);
        AntBrain b = new AntBrain("cleverbrain1.brain");
        tournament.addCompetitors(b);
        tournament.addCompetitors(b);
        tournament.runTournament();
    }

    /**
     * 
     */
    public void newGame() {
        tournament.runTournament();
    }

    /**
     * 
     */
    public void newWorld() {
        tournament.worlds.add(null);
    }
}
