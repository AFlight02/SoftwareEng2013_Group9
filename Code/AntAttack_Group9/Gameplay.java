/**
 * Gameplay.java Manages an individual instance of a match between two AntBrain
 * competitors on a World.
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Gameplay {

    private World world;
    private List<Ant> ants;
    protected AntBrain redAntBrain;
    protected AntBrain blackAntBrain;
    private RandomInt random;
    private Random rand;
    protected int redFood;
    protected int blackFood;
    protected int numRedAnts;
    protected int numBlackAnts;

    /**
     * Initialises a new Gameplay instance with black and red AntBrains to default values.
     * @param red the Red AntBrain
     * @param black the Black AntBrain
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
     * Calls to generate a new world randomly and then checks that it is valid for a Tournament.
     */
    public void generateWorld() {
        this.world = new World();
        this.world.generateRandomContestWorld();
        this.world.checkValidForTournament();
    }

    /**
     * Initiates a game loop with the specified GUI, match number and total number of matches to play.
     * @param gui the GUI to display this game
     * @param matchNum the number of the current match this Gameplay is managing
     * @param totalMatches the total number of matches to be played
     */
    public void playGame(GUI gui, int matchNum, int totalMatches) {
        gui.initaliseWorldMap(world, matchNum, totalMatches);
        System.out.println("Total Food:" + world.getFoodNum());
        try {
            // Sleep thread for 3 seconds between each game for the player to observe the results
            Thread.sleep(3000); // 3000ms
        } catch (InterruptedException e) {
        }
        gui.updateUI(world, this);
        for (int i = 0; i < 300000; i++) {
            stepGame();
            gui.updateUI(world, this);
            //Use the following block to create a realtime simulation (lasts a 300,000 x number of ms specified in sleep(n))
//           try {
//                Thread.sleep(5); // 2ms sleep time between steps = 300,000x2ms = 10 minutes per game
//            } catch (InterruptedException e) {}
        }
        endGame();
        gui.updateUI(world, this);
    }

    /**
     * Load a World into this Gameplay instance.
     * @param uploadWorld the World to add to this Game
     */
    public void loadWorld(World uploadWorld) {
        this.world = uploadWorld;
    }

    /**
     * Initiate a single 'step' of the game that loops through all Ants in the World, performs checks to 
     * see if they're alive, if they should die, then what they should do on this turn based on the Instruction 
     * returned by their current state, before setting their state for the next turn and advancing to the next Ant in the list.
     */
    public void stepGame() {
        for (Ant a : ants) {
            // Get the Ant's next Instrcution
            Instruction nextInstruction = a.getInstruction();
            Cell currCell = world.getCell(a.getPosition());
            currCell.calculateAdjacentAnts(world);
            if (a.getColour()) { //BLACK
                if (currCell.getAdjacentAntsRed() >= 5) {
                    a.kill();
                    numBlackAnts--;
                    currCell.incrementFood();
                    currCell.removeAnt();
                    ants.remove(a);
                    break;
                }
            } else {
                if (currCell.getAdjacentAntsBlack() >= 5) {
                    a.kill();
                    numRedAnts--;
                    currCell.incrementFood();
                    currCell.removeAnt();
                    ants.remove(a);
                    break;
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
                } else if (nextInstruction instanceof Mark) {
                    Mark m = (Mark) nextInstruction;
                    if (a.getColour()) { //BLACK
                        currCell.addBlackMarker(m.getMarker());
                    } else {
                        currCell.addRedMarker(m.getMarker());
                    }
                    a.setState(m.getS1());
                } else if (nextInstruction instanceof Sense) {
                    Sense s = (Sense) nextInstruction;
                    if (world.checkCellStatus(currCell.sensedCell(a.getPosition(), a.getDirection(), s.getSenseDirVal()), s.getCondVal(), a)) {
                        a.setState(s.getS1());
                    } else {
                        a.setState(s.getS2());
                    }
                } else if (nextInstruction instanceof Unmark) {
                    Unmark u = (Unmark) nextInstruction;
                    if (a.getColour()) { //BLACK
                        currCell.removeBlackMarker(u.getMarker());
                    } else {
                        currCell.removeRedMarker(u.getMarker());
                    }
                    a.setState(u.getS1());
                } else if (nextInstruction instanceof PickUp) {
                    PickUp p = (PickUp) nextInstruction;
                    if (currCell.getFood() > 0 && !a.getFood()) {
                        a.setFood(true);
                        currCell.decrementFood();
                        a.setState(p.getS1());
                    } else {
                        a.setState(p.getS2());
                    }
                } else if (nextInstruction instanceof Turn) {
                    Turn t = (Turn) nextInstruction;
                    a.setDirection(a.turn(t.getTurnDir()));
                    a.setState(t.getS1());
                } else if (nextInstruction instanceof Drop) {
                    Drop d = (Drop) nextInstruction;
                    if (a.getFood()) {
                        a.setFood(false);
                        currCell.incrementFood();
                    }
                    a.setState(d.getS1());
                } else if (nextInstruction instanceof Move) {
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
                } else {
                    System.err.println("Unrecognised Instruction" + nextInstruction);
                }
            } else {
                world.getCell(a.getPosition()).removeAnt();
                ants.remove(a);
            }
        }
    }

    /**
     * Call to count all the food at the end of a Game for determining the winner.
     */
    public void endGame() {
        blackFood = 0;
        redFood = 0;
        for (int[] i : world.getAnthillCells()) {
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
     * Setup the Game for play by first clearing all the Ants currently held in the list of Ants,
     * resetting the counts of Ants to 0, resetting the World, replacing food in the World, then replacing Ants
     * in the World.
     */
    public void setupGame() {
        ants.clear();
        numBlackAnts = 0;
        numRedAnts = 0;
        world.resetWorld();
        world.replaceFood();
        ants = world.placeAnts(blackAntBrain, redAntBrain);
        for (Ant a : ants) {
            if (a.getColour()) { //BLACK
                numBlackAnts++;
            } else {
                numRedAnts++;
            }
        }
    }

    /**
     * Load the specified red and black AntBrains into the Gameplay instance
     * @param red the red AntBrain
     * @param black the black AntBrain
     */
    public void loadAntBrains(AntBrain red, AntBrain black) {
        redAntBrain = red;
        blackAntBrain = black;
    }

    /**
     * Checks to see whether the black or red brain holds the most food in the World and 
     * returns the winner.
     * @return an integer representing the index of the winner in the Tournament
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
