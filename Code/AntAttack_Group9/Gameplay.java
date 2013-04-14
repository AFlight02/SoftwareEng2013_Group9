/**
* Gameplay.java Manages an individual instance of a match between two AntBrain
* competitors on a World.
*
* @author Software Engineering 2012-13 Group 9 Simon Bell, Kirstie Hale,
* Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
* @version 1
*/
package AntAttack_Group9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Gameplay {

   private World world;
   private List<Ant> ants;
   private AntBrain redAntBrain;
   private AntBrain blackAntBrain;
   private RandomInt random;
   private Random rand;
   private int redFood;
   private int blackFood;

   /**
    *
    * @param red
    * @param black
    */
   public Gameplay(AntBrain red, AntBrain black) {
       this.world = new World();
       this.random = new RandomInt();
       this.rand = new Random();
       this.redFood = 0;
       this.blackFood = 0;
       this.redAntBrain = red;
       this.blackAntBrain = black;
       this.ants = new ArrayList<>();
   }

   /**
    *
    */
   public void generateWorld() {
       this.world = new World();
       this.world.generateRandomContestWorld();
       this.world.checkValidForTournament();
   }

   /**
    *
    */
   public void playGame(GUI gui) {
       // TEST WITH 3000 CHANGE BACK!!!!
       gui.initaliseWorldMap(world);
       System.out.println("Total Food:" + world.getFoodNum());
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {}
       gui.updateUI(world);
       for (int i = 0; i < 300000; i++) {
           stepGame(gui);
           gui.updateUI(world);
           try {
                Thread.sleep(5);
            } catch (InterruptedException e) {}
       }
       endGame();
       gui.updateUI(world);
   }

   /**
    *
    * @param uploadWorld
    */
   public void loadWorld(World uploadWorld) {
       this.world = uploadWorld;
   }

   /**
    *
    */
   public void stepGame(GUI gui) {
       for (Ant a : ants) {
           Instruction nextInstruction = a.getInstruction();
           Cell currCell = world.getCell(a.getPosition());
           if (a.getColour()) {
                   if (currCell.getAdjacentAntsRed() >= 5) {
                       a.kill();
                       currCell.incrementFood();
                       currCell.removeAnt();
                   }
               } else {
                   if (currCell.getAdjacentAntsBlack() >= 5) {
                       a.kill();
                       currCell.incrementFood();
                       currCell.removeAnt();
                   }
               }
           if (a.isAlive()) {
               a.decrementResting();
               
               if (nextInstruction instanceof Flip) {
                   Flip f = (Flip) nextInstruction;
                   int n = rand.nextInt(f.getRandom());
                   //System.out.println(n);
                   if (n == 0) {
                       a.setState(f.getS1());
                   } else {
                       a.setState(f.getS2());
                   }
               }

               else if (nextInstruction instanceof Mark) {
                   Mark m = (Mark) nextInstruction;
                   if (a.getColour()) { //BLACK
                       currCell.addBlackMarker(m.getMarker());
                   } else {
                       currCell.addRedMarker(m.getMarker());
                   }
                   a.setState(m.getS1());
               }

               else if (nextInstruction instanceof Sense) {
                   Sense s = (Sense) nextInstruction;
                   if (world.checkCellStatus(currCell.adjacentCell(a.getDirection()), s.getCondVal(), a)) {
                       a.setState(s.getS1());
                   } else {
                       a.setState(s.getS2());
                   }
               }

               else if (nextInstruction instanceof Unmark) {
                   Unmark u = (Unmark) nextInstruction;
                   if (a.getColour()) { //BLACK
                       currCell.removeBlackMarker(u.getMarker());
                   } else {
                       currCell.removeRedMarker(u.getMarker());
                   }
                   a.setState(u.getS1());
               }

               else if (nextInstruction instanceof PickUp) {
                   PickUp p = (PickUp) nextInstruction;
                   if (currCell.getFood() > 0 && !a.getFood()) {
                       a.setFood(true);
                       currCell.decrementFood();
                       a.setState(p.getS1());
                   } else {
                       a.setState(p.getS2());
                   }
               }

               else if (nextInstruction instanceof Turn) {
                   Turn t = (Turn) nextInstruction;
                   a.setDirection(a.turn(t.getTurnDir()));
                   a.setState(t.getS1());
               }

               else if (nextInstruction instanceof Drop) {
                   Drop d = (Drop) nextInstruction;
                   if (a.getFood()) {
                       currCell.incrementFood();
                   }
                   a.setState(d.getS1());
               }

               else if (nextInstruction instanceof Move) {
                   Move m = (Move) nextInstruction;
                   int[] newCell = currCell.adjacentCell(a.getDirection());
                   if (a.getResting() <= 0) {
                       if (world.getCell(newCell) != null) {
                           if (!world.getCell(newCell).getRock() && world.getCell(newCell).getAnt() == null) {
                               currCell.removeAnt();
                               world.getCell(newCell).setAnt(a);
                               a.setResting(15);
                               a.setPostition(newCell);
                               a.setState(m.getS1());
                           } else {
                               a.setState(m.getS2());
                           }
                       } else {
                           a.setState(m.getS2());
                       }

                   } else {
                       a.setState(m.getS2());
                   }
               }
               else {
                   System.err.println("Unrecognised Instruction" + nextInstruction);
               }
           } else {
               world.getCell(a.getPosition()).removeAnt();
               ants.remove(a);
           }
       }
   }

   /**
    *
    */
   public void endGame() {
       blackFood = 0;
       redFood = 0;
       for(int[] i : world.getAnthillCells()) {
           if (world.getCell(i).anthill.equalsIgnoreCase("black")) {
               blackFood += world.getCell(i).getFood();
           } else if (world.getCell(i).anthill.equalsIgnoreCase("red")) {
               redFood += world.getCell(i).getFood();
           }
       }
       System.out.println("Black Food: " + blackFood);
       System.out.println("Red Food: " + redFood);
   }

   /**
    *
    */
   public void setupGame() {
       ants.clear();
       world.resetWorld();
       world.replaceFood();
       ants = world.placeAnts(blackAntBrain, redAntBrain);
   }

   /**
    *
    * @param red
    * @param black
    */
   public void loadAntBrains(AntBrain red, AntBrain black) {
       redAntBrain = red;
       blackAntBrain = black;
   }

   /**
    *
    * @return
    */
   public int declareWinner() {
       int winner;
       if (redFood > blackFood) {
           winner = 2;
       } else if (blackFood > redFood) {
           winner = 1;
       } else if (blackFood == redFood) {
           winner = 0;
       } else {
           winner = -1;
       }
       // Calculates winners and returns 0 for Draw, 1 for Black win, 2 for Red win, -1 for error
       return winner;
   }
}

