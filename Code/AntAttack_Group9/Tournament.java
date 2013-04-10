/**
 * Tournament.java Initiates and manages a Tournament between multiple Ant Brain
 * competitors and calculates an overall winner after all matches have been played.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */
package AntAttack_Group9;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

    List<AntBrain> antBrains;
    List<World> worlds;
    int[] scores; // Increments points per AntBrain in list, 1 = draw, 2 = win, 0 = loss

    /*
     * RE: new thoughts -- was looking through/writing stuff for this class
     * earlier and came to the same conclusions. Also, in the spec it says that
     * there will be several game worlds for everyone to play a match in, which
     * with the matches[][] array method would mean also keeping track of which
     * worlds had been played - Far easier to just iterate everything :-p
     */
    //two constructors: so you can either initialize a tourney with a list of brains, or add them later (or both).
    /**
     * 
     * @param worlds
     */
    public Tournament(List<World> worlds) {
        // ALEX: altered to create a new ArrayList instead of Arrays.asList() as it was throwing errors, couldn't pinpoint why?
        antBrains = new ArrayList<>(); //inits as an empty list
        this.worlds = worlds;
    }

    /**
     * 
     * @param antBrains
     * @param worlds
     */
    public Tournament(List<AntBrain> antBrains, List<World> worlds) {
        this.antBrains = antBrains;
        this.worlds = worlds;
    }

    /**
     * Add a single ant brain to the Tournament (before playing matches)
     *
     * @param brain the AntBrain to add to the tournament
     */
    public void addCompetitors(AntBrain brain) {
        antBrains.add(brain);
    }

    /**
     * Sets up and runs the tournament - run this after all desired ant brains
     * have been added.
     */
    public List<AntBrain> runTournament(GUI gui) {
        int numCompetitors = antBrains.size();
        scores = new int[numCompetitors]; //can init scores[] now that we know how many brains there are

        int gameCounter = 0;
        
        if (numCompetitors > 1) {
            List<AntBrain> winners;

            for (int i = 0; i < numCompetitors; i++) {
                for (int j = 0; j < numCompetitors; j++) {
                    for (World w : worlds) {
                        if (i != j) {
                            System.out.println("Playing game " + gameCounter++);
                            playMatch(i, j, w, gui);
                            playMatch(j, i, w, gui);
                        }
                    }
                }
            }
            return winners = declareWinner(); //then give this to the user somehow (GUI?)
        } else {
            //return error or throw exception etc. - need at least 2 brains for competition
            System.out.println("Must have at least 2 ant brains to start a tournament!");
            return null;
        }
    }

    //no public access to the rest of the methods as they are only used by the Tournament class:
    /**
     * Plays one match and updates the scores
     *
     * @param redBrain the list/score index of opponent 1
     * @param blackBrain the list/score index of opponent 2
     * @param world the world the match is played on
     */
    private void playMatch(int redBrain, int blackBrain, World world, GUI gui) {
        //code to run a game. Update this later once World class has been written
        Gameplay game = new Gameplay(antBrains.get(redBrain), antBrains.get(blackBrain));
        game.loadWorld(world);
        game.setupGame();
        game.playGame(gui);

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

    /*
     * Calculates the winner(s) of the tournament @return winningBrains the
     * brain(s) with the highest score
     */
    private List<AntBrain> declareWinner() {
        List<AntBrain> winningBrains = new ArrayList<>(); // ALEX: Again wasn't compiling due to error, reinitialised as ArrayList seemed to fix
        int highestScore = 0;

        //need to do this separately to just finding the index with highest score, to allow for a tourney draw (though unlikely)
        for (int s : scores) {
            if (s > highestScore) {
                highestScore = s;
            }
        }

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == highestScore) {
                winningBrains.add(antBrains.get(i));
            }
        }

        return winningBrains;
    }
}