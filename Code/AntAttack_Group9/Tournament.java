package AntAttack_Group9;
import java.util.*;

public class Tournament {

	List<AntBrain> antBrains;
	List<World> worlds;
        int[] scores; // Increments points per AntBrain in list, 1 = draw, 2 = win, 0 = loss
	
        /*
         * RE: new thoughts -- was looking through/writing stuff for this class earlier and came to the same conclusions.
         * Also, in the spec it says that there will be several game worlds for everyone to play a match in,
         * which with the matches[][] array method would mean also keeping track of which worlds had been played
         * - Far easier to just iterate everything :-p
         */
        
        //two constructors: so you can either initialize a tourney with a list of brains, or add them later (or both).
        public Tournament(List<World> worlds) {
            antBrains = Arrays.asList(); //inits as an empty list
            this.worlds = worlds;
        }
        
	public Tournament(List<AntBrain> antBrains, List<World> worlds) {
            this.antBrains = antBrains;
            this.worlds = worlds;
	}

        /**
         * Add a single ant brain to the Tournament (before playing matches)
         * @param brain the AntBrain to add to the tournament
         */
	public void addCompetitors(AntBrain brain) {
            antBrains.add(brain);
	}
        
        /**
         * Sets up and runs the tournament - run this after all desired ant brains have been added.
         */
        public void runTournament() {
            int numCompetitors = antBrains.size();
            scores = new int[numCompetitors]; //can init scores[] now that we know how many brains there are
            
            if(numCompetitors > 1) {
                List<AntBrain> winners;
                
                for(int i = 0; i < numCompetitors; i++) {
                    for(int j = 0; j < numCompetitors; j++) {
                        for(World w: worlds) {
                            if(i != j) {
                                playMatch(i, j, w);
                                playMatch(j, i, w);
                            }
                        }
                    }
                }
                winners = declareWinner(); //then give this to the user somehow (GUI?)
            } else {
                //return error or throw exception etc. - need at least 2 brains for competition
                System.out.println("Must have at least 2 ant brains to start a tournament!");
            }
        }
        
        //no public access to the rest of the methods as they are only used by the Tournament class:

        /**
         * Plays one match and updates the scores
         * @param redBrain the list/score index of opponent 1
         * @param blackBrain the list/score index of opponent 2
         * @param world the world the match is played on
         */
	private void playMatch(int redBrain, int blackBrain, World world) {
            //code to run a game. Update this later once World class has been written
            Gameplay game = new Gameplay();
            game.playGame();
            
            int winner = game.declareWinner(); //0 for Draw, 1 for Black win, 2 for Red win
            
            switch(winner) {
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
         * Calculates the winner(s) of the tournament
         * @return winningBrains the brain(s) with the highest score
         */
	private List<AntBrain> declareWinner() {
            List<AntBrain> winningBrains = Arrays.asList();
            int highestScore = 0;
            
            //need to do this separately to just finding the index with highest score, to allow for a tourney draw (though unlikely)
            for(int s: scores) {
                if (s > highestScore) {
                    highestScore = s;
                }
            }
            
            for(int i = 0; i < scores.length; i++) {
                if(scores[i] == highestScore) {
                    winningBrains.add(antBrains.get(i));
                }
            }
            
            return winningBrains;
	}

}