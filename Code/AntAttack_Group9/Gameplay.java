/**
 * Gameplay.java Manages an individual instance of a match between two AntBrain
 * competitors on a World.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
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
    private int redFood;
    private int blackFood;

    /**
     *
     * @param red
     * @param black
     */
    public Gameplay(AntBrain red, AntBrain black) {
        world = new World();
        this.redFood = 0;
        this.blackFood = 0;
        this.redAntBrain = red;
        this.blackAntBrain = black;
        ants = new ArrayList<>();
    }

    /**
     *
     */
    public void generateWorld() {
        world = new World();
        world.generateRandomContestWorld();
    }

    /**
     *
     */
    public void playGame(GUI gui) {
        // TEST WITH 3000 - CHANGE BACK!!!!
        gui.initaliseWorldMap(world);
        gui.updateUI(world);
        for (int i = 0; i < 300000; i++) {
            stepGame(gui);
            gui.updateUI(world);
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {}
        }
        endGame();
    }

    /**
     *
     * @param uploadWorld
     */
    public void loadWorld(World uploadWorld) {
        world = uploadWorld;
    }

    /**
     *
     */
    public void stepGame(GUI gui) {
        for (Ant a : ants) {
            if (a.isAlive()) {
                if (a.getColour()) {
                    if (world.getCell(a.getPosition()).getAdjacentAntsRed() >= 5) {
                        a.kill();
                        world.getCell(a.getPosition()).removeAnt();
                    }
                } else {
                    if (world.getCell(a.getPosition()).getAdjacentAntsBlack() >= 5) {
                        a.kill();
                        world.getCell(a.getPosition()).removeAnt();
                    }
                }

                Instruction nextInstruction = a.getInstruction();

                if (nextInstruction instanceof Flip) {
                    Flip f = (Flip) nextInstruction;
                    Random rand = new Random();//CHANGE LATER FOR FLIP
                    int n = rand.nextInt(f.getRandom());
                    if (n == 0) {
                        a.setState(f.getS1());
                    } else {
                        a.setState(f.getS2());
                    }
                }

                else if (nextInstruction instanceof Mark) {
                    Mark m = (Mark) nextInstruction;
                    if (a.getColour()) { //BLACK
                        world.getCell(a.getPosition()).addBlackMarker(m.getMarker());
                    } else {
                        world.getCell(a.getPosition()).addRedMarker(m.getMarker());
                    }
                    a.setState(m.getS1());
                }

                else if (nextInstruction instanceof Sense) {
                    Sense s = (Sense) nextInstruction;
                    if (world.checkCellStatus(world.getCell(a.getPosition()).adjacentCell(a.getDirection()), s.getCondVal(), a)) {
                        a.setState(s.getS1());
                    } else {
                        a.setState(s.getS2());
                    }
                }

                else if (nextInstruction instanceof Unmark) {
                    Unmark u = (Unmark) nextInstruction;
                    if (a.getColour()) { //BLACK
                        world.getCell(a.getPosition()).removeBlackMarker(u.getMarker());
                    } else {
                        world.getCell(a.getPosition()).removeRedMarker(u.getMarker());
                    }
                    a.setState(u.getS1());
                }

                else if (nextInstruction instanceof PickUp) {
                    PickUp p = (PickUp) nextInstruction;
                    if (world.getCell(a.getPosition()).getFood() > 0) {
                        a.setFood(true);
                        world.getCell(a.getPosition()).removeFood();
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
                        world.getCell(a.getPosition()).setFood(world.getCell(a.getPosition()).getFood() + 1);
                    }
                    a.setState(d.getS1());
                }

                else if (nextInstruction instanceof Move) {
                    Move m = (Move) nextInstruction;
                    int[] newCell = world.getCell(a.getPosition()).adjacentCell(a.getDirection());
                    if (a.getResting() <= 0) {
                        if (world.getCell(newCell) != null) {
                            if (!world.getCell(newCell).getRock() && world.getCell(newCell).getAnt() == null) {
                                world.getCell(a.getPosition()).removeAnt();
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
                a.decrementResting();
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
        for (int i = 1; i < world.height - 1; i++) {
            for (int j = 1; j < world.width - 1; j++) {
                if (!world.cells[i][j].anthill.equalsIgnoreCase("none")) {
                    if (world.cells[i][j].anthill.equalsIgnoreCase("black")) {
                        blackFood += world.cells[i][j].getFood();
                    } else if (world.cells[i][j].anthill.equalsIgnoreCase("red")) {
                        redFood += world.cells[i][j].getFood();
                    }
                }
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
        world.checkValidWorld();
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
    
    public void resetGameWorld() {
        world.resetWorld();
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
