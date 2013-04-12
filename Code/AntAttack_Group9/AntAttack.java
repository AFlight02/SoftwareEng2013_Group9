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

    private static GUI gui;
    private static Tournament tournament;
    private static World testWorld;
    /**
     * 
     */
    public AntAttack() {
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        testWorld = new World();
        //testWorld.generateRandomContestWorld();
        testWorld.readInWorld("1.world");
        testWorld.checkValidForTournament();
        //testWorld.printWorld();
        initialiseGUI();
        newTournament(); 
    }

    /**
     * 
     */
    public static void initialiseGUI() {
        gui = new GUI(testWorld);
        gui.initaliseWorldMap(testWorld);
    }

    /**
     * 
     */
    public static void newTournament() {
        List<World> w = new ArrayList();
        tournament = new Tournament(w);
        AntBrain a = new AntBrain("cleverbrain1.brain");
        AntBrain b = new AntBrain("sample.ant");
        AntBrain c = new AntBrain("cleverbrain2.brain");
        AntBrain d = new AntBrain("cleverbrain3.brain");
        AntBrain e = new AntBrain("cleverbrain4.brain");
        AntBrain f = new AntBrain("horseshoe.brain");
        AntBrain g = new AntBrain("sampleant.brain");
        AntBrain h = new AntBrain("snakebrain.brain");
        AntBrain i = new AntBrain("solution-1.brain");
        
        //My test should walk around a lot
        AntBrain walk = new AntBrain("walker.brain");
        
        tournament.addCompetitors(a);
        tournament.addCompetitors(c);
//        tournament.addCompetitors(c);
//        tournament.addCompetitors(d);
//        tournament.addCompetitors(e);
//        tournament.addCompetitors(f);
//        tournament.addCompetitors(g);
//        tournament.addCompetitors(h);
//        tournament.addCompetitors(i);
        tournament.worlds.add(testWorld);
        
        List<AntBrain> winners = tournament.runTournament(gui);
        for(AntBrain br : winners) {
            System.out.println("Winner: " + br.getName());
        }
    }

    /**
     * 
     */
    public static void newWorld() {
        tournament.worlds.add(null);
    }
}
