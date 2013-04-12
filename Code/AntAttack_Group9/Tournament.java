/**
* Tournament.java Initiates and manages a Tournament between multiple Ant Brain
* competitors and calculates an overall winner after all matches have been played.
*
* @author Software Engineering 2012-13 Group 9 Simon Bell, Kirstie Hale,
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
   
   /**
    * 
    * @param worlds
    */
   public Tournament(List<World> worlds) {
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

   public void addWorld(World w) {
       worlds.add(w);
   }
   /**
    * Sets up and runs the tournament run this after all desired ant brains
    * have been added.
    */
   public List<AntBrain> runTournament(GUI gui) {
       int numCompetitors = antBrains.size();
       scores = new int[numCompetitors]; //can init scores[] now that we know how many brains there are

       for(World wo : worlds) {
           wo.checkValidForTournament();
       }
       
       int gameCounter = 0;
       
       if (numCompetitors > 1) {
           List<AntBrain> winners = new ArrayList<>();

           for (int i = 0; i < numCompetitors; i++) {
               for (int j = 0; j < numCompetitors; j++) {
                   for (World w : worlds) {
                       if (i != j) {
                           System.out.println("Playing game " + (++gameCounter) + " of " + (numCompetitors*(numCompetitors 1)*worlds.size()));
                           playMatch(i, j, w, gui);
                           //System.out.println("Playing game " + (gameCounter++ + 1) + "b");
                           //playMatch(j, i, copy, gui);
                       }
                   }
               }
           }
           return winners = declareWinner(); //then give this to the user somehow (GUI?)
       } else {
           //return error or throw exception etc. need at least 2 brains for competition
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
       Gameplay game = new Gameplay(antBrains.get(redBrain), antBrains.get(blackBrain));
       game.loadWorld(world);
       //game.generateWorld();
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
    * Calculates the winner(s) of the tournament 
    * @return winningBrains the brain(s) with the highest score
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
