package AntAttack_Group9;

import java.io.File;
import java.util.*;

public class AntAttack {

    GUI gui;
    Tournament tournament;
    int newAttr; // Error in class diagram, will fix! Remove this on Friday.

    public AntAttack() {
    }

    public static void main(String[] args) {
        AntAttack newGame = new AntAttack();
        newGame.newTournament();
        newGame.newGame();
    }

    public void initialiseGUI() {
        // Call to GUI constructor and initialisation methods.
    }

    public void newTournament() {
        List<World> w = new ArrayList();
        tournament = new Tournament(w);
        AntBrain b = new AntBrain("cleverbrain1.brain");
        tournament.addCompetitors(b);
        tournament.addCompetitors(b);
        tournament.runTournament();
    }

    public void newGame() {
        tournament.runTournament();
    }

    public void newWorld() {
        tournament.worlds.add(null);
    }
}
