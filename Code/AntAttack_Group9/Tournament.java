/**
 * Tournament.java Initiates and manages a Tournament between multiple Ant Brain
 * competitors and calculates an overall winner after all matches have been
 * played.
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tournament {

    private ExecutorService gameExecutor = Executors.newSingleThreadExecutor();
    private List<AntBrain> antBrains;
    private List<World> worlds;
    private int[] scores; // Increments points per AntBrain in list, 1 = draw, 2 = win, 0 = loss
    private Future<?> gameTask;
    private JFrame winnerFrame;
    public ArrayList<AntBrain> winners;

    /**
     * Creates a new Tournament with a provided list of Worlds and initialises arrays.
     * @param worlds list of Worlds to use in this Tournament
     */
    public Tournament(List<World> worlds) {
        winners = new ArrayList();
        antBrains = new ArrayList(); //inits as an empty list
        this.worlds = worlds;
    }
    
    
    /**
     * Creates a new Tournament with a provided list of AntBrains and Worlds
     * @param antBrains the AntBrains in this Tournament
     * @param worlds the Worlds to play on
     */
    public Tournament(List<AntBrain> antBrains, List<World> worlds) {
        this.antBrains = antBrains;
        this.worlds = worlds;
        this.winners = new ArrayList();
    }

    /**
     * Begins a new Tournament in a new Thread to enable GUI and Game logic separation.
     * @param g the GUI managing this Tournament instance
     * @return true when the Tournament is begun
     */
    public boolean startNewTourn(final GUI g) {
        if (gameTask != null) {
            gameTask.cancel(true);
        }
        gameTask = gameExecutor.submit(new Runnable() {

            @Override
            public void run() {
                winners = runTournament(g);
            }
        });
        return true;
    }

    /**
     * Add a single ant brain to the Tournament.
     * @param brain the AntBrain to add to the tournament
     */
    public void addCompetitors(AntBrain brain) {
        antBrains.add(brain);
    }

    /**
     * Adds a single World to the Tournament.
     * @param w the World to add to the Tournament
     */
    public void addWorld(World w) {
        worlds.add(w);
    }

    /**
     * Sets up and runs the tournament run this after all desired ant brains
     * have been added.
     * @param gui the GUI managing this Tournament
     * @return a list of all the winning AntBrains (could be 1+ AntBrains)
     */
    public ArrayList<AntBrain> runTournament(GUI gui) {
        int numCompetitors = antBrains.size();
        scores = new int[numCompetitors]; //can init scores[] now that we know how many brains there are

        for (World wo : worlds) {
            wo.checkValidForTournament();
        }

        int gameCounter = 0;
        int totalMatches = (numCompetitors * (numCompetitors - 1) * worlds.size());

        if (numCompetitors > 1) {
            ArrayList<AntBrain> winnersList = new ArrayList<>();
            for (int i = 0; i < numCompetitors; i++) {
                for (int j = 0; j < numCompetitors; j++) {
                    for (World w : worlds) {
                        if (i != j) {
                            System.out.println("Playing game " + (++gameCounter) + " of " + totalMatches);
                            playMatch(i, j, w, gui, gameCounter, totalMatches);
                            //System.out.println("Playing game " + (gameCounter++ + 1) + "b");
                            //playMatch(j, i, copy, gui);
                        }
                    }
                }
            }
            return winnersList = declareWinner(); //then give this to the user somehow (GUI?)
        } else {
            //return error or throw exception etc. need at least 2 brains for competition
            System.out.println("Must have at least 2 ant brains to start a tournament!");
            return null;
        }
    }

    //no public access to the rest of the methods as they are only used by the Tournament class:
    /**
     * Plays one match and updates the scores.
     *
     * @param redBrain the list/score index of opponent 1
     * @param blackBrain the list/score index of opponent 2
     * @param world the world the match is played on
     */
    private void playMatch(int redBrain, int blackBrain, World world, GUI gui, int matchNum, int totalMatches) {
        Gameplay game = new Gameplay(antBrains.get(redBrain), antBrains.get(blackBrain));
        game.loadWorld(world);
        //game.generateWorld();
        game.setupGame();
        game.playGame(gui, matchNum, totalMatches);
        int winner = game.declareWinner(); //0 for Draw, 1 for Black win, 2 for Red win

        switch (winner) {
            case 0:
                scores[redBrain]++;
                scores[blackBrain]++;
                break;
            case 1:
                scores[blackBrain] += 2;
                break;
            case 2:
                scores[redBrain] += 2;
        }
    }

    /**
     * Calculates the winner(s) of the tournament and then displays a dialog box displaying
     * these.
     * @return a list of winning AntBrains
     */
    private ArrayList<AntBrain> declareWinner() {
        ArrayList<AntBrain> winningBrains = new ArrayList(); // ALEX: Again wasn't compiling due to error, reinitialised as ArrayList seemed to fix
        int highestScore = 0;
        String winnerString = "Winners:\n";

        //need to do this separately to just finding the index with highest score, to allow for a tourney draw (though unlikely)
        for (int s : scores) {
            if (s > highestScore) {
                highestScore = s;
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == highestScore) {
                winningBrains.add(antBrains.get(i));
                winnerString += antBrains.get(i).getName() + "\n";
            }
        }
        winnerFrame = new JFrame();
        JOptionPane.showMessageDialog(winnerFrame, winnerString);
        winnerFrame.pack();
        winnerFrame.setVisible(true);
        return winningBrains;
    }
}
