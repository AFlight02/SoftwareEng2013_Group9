/**
 * AntAttack.java Main file for interacting with and utilising the Ant Tournament
 * system.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */
package AntAttack_Group9;

import java.util.ArrayList;
import java.util.List;

public class AntAttack {

    GUI gui;
    Tournament tournament;
    int newAttr; // Error in class diagram, will fix! Remove this on Friday.

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
        AntAttack newGame = new AntAttack();
        newGame.newTournament();
        newGame.newGame();
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
