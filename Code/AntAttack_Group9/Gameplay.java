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

public class Gameplay {

    World world;
    int round;
    int redFood;
    int blackFood;
    List<Ant> ants;
    AntBrain redAntBrain;
    AntBrain blackAntBrain;
    double interpolation = 0;
    final int TICKS_PER_SECOND = 25;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;

    /**
     *
     * @param red
     * @param black
     */
    public Gameplay(AntBrain red, AntBrain black) {
        world = new World();
        this.round = 0;
        this.redFood = 0;
        this.blackFood = 0;
        this.redAntBrain = red;
        this.blackAntBrain = black;
        ants = new ArrayList<Ant>();
        loadAntBrains(redAntBrain, blackAntBrain);
    }

    /**
     *
     */
    public void generateWorld() {
// Generate a new World randomly
    }

    /**
     *
     */
    public void playGame(GUI gui) {
        // TEST WITH 3000 - CHANGE BACK!!!!
        //world.printWorld();
        for (int i = 0; i < 3000; i++) {
            stepGame(gui);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {}
            //System.out.println("Step " + i);
        }
        //world.printWorld();
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
        for (int i = 0; i < world.cells.length; i++) {
            for (int j = 0; j < world.cells[i].length; j++) {
                if (world.cells[i][j].ant != null) {
                    if (world.cells[i][j].ant.getColour()) {
                        if (world.cells[i][j].getAdjacentAntsRed() >= 5) {
                            world.cells[i][j].ant.kill();
                            world.cells[i][j].removeAnt();
                        }
                    } else {
                        if (world.cells[i][j].getAdjacentAntsBlack() >= 5) {
                            world.cells[i][j].ant.kill();
                            world.cells[i][j].removeAnt();
                        }
                    }
                }
            }
        }

        for (Ant a : ants) {
            if (a.isAlive()) {
                if (a.getResting() <= 0) {
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

                    if (nextInstruction instanceof Mark) {
                        Mark m = (Mark) nextInstruction;
                        if (a.getColour()) { //BLACK
                            world.getCell(a.getPosition()).addBlackMarker(m.getMarker());
                        } else {
                            world.getCell(a.getPosition()).addRedMarker(m.getMarker());
                        }
                        a.setState(m.getS1());
                    }

                    if (nextInstruction instanceof Sense) {
                        Sense s = (Sense) nextInstruction;
                        if (world.checkCellStatus(world.getCell(a.getPosition()).adjacentCell(a.getDirection()), s.getCondVal(), a)) {
                            a.setState(s.getS1());
                        } else {
                            a.setState(s.getS2());
                        }
                    }

                    if (nextInstruction instanceof Unmark) {
                        Unmark u = (Unmark) nextInstruction;
                        if (a.getColour()) { //BLACK
                            world.getCell(a.getPosition()).removeBlackMarker(u.getMarker());
                        } else {
                            world.getCell(a.getPosition()).removeRedMarker(u.getMarker());
                        }
                        a.setState(u.getS1());
                    }

                    if (nextInstruction instanceof PickUp) {
                        PickUp p = (PickUp) nextInstruction;
                        if (world.getCell(a.getPosition()).getFood() > 0) {
                            a.setFood(true);
                            world.getCell(a.getPosition()).removeFood();
                            a.setState(p.getS1());
                        } else {
                            a.setState(p.getS2());
                        }
                    }

                    if (nextInstruction instanceof Turn) {
                        Turn t = (Turn) nextInstruction;
                        a.setDirection(a.turn(t.getTurnDir()));
                        a.setState(t.getS1());
                    }

                    if (nextInstruction instanceof Drop) {
                        Drop d = (Drop) nextInstruction;
                        if (a.getFood()) {
                            world.getCell(a.getPosition()).setFood(world.getCell(a.getPosition()).getFood() + 1);
                        }
                        a.setState(d.getS1());
                    }

                    if (nextInstruction instanceof Move) {
                        Move m = (Move) nextInstruction;
                        int[] newCell = world.getCell(a.getPosition()).adjacentCell(a.getDirection());
                        if (a.getResting() <= 0) {
                            if(world.getCell(newCell) != null) {
                                if (!world.getCell(newCell).getRock() && world.getCell(newCell).getAnt() == null) {
                                    world.getCell(a.getPosition()).removeAnt();
                                    world.getCell(newCell).setAnt(a);
                                    a.setResting(15);
                                    a.setPostition(newCell);
                                    a.setState(m.getS1());
                                }
                            }

                        } else {
                            a.setState(m.getS2());
                        }
                    }
                }
                a.decrementResting();
            }
        }
        gui.updateUI(world);
    }

    /**
     *
     */
    public void endGame() {
// On completion of all steps finish the game, total scores etc.
    }

    /**
     *
     */
    public void setupGame() {
        world.checkValidWorld();
        world.placeAnts();
        int id = 0;
        for(int i=1; i<world.height - 1; i++) {
            for(int j=1; j<world.width - 1; j++) {
                if(world.cells[i][j].ant != null) {
                    if(world.cells[i][j].ant.getColour()) {
                        world.cells[i][j].ant.setBrain(blackAntBrain);
                        world.cells[i][j].ant.setId(id++);
                        ants.add(world.cells[i][j].ant);
                    } else if(world.cells[i][j].anthill.equalsIgnoreCase("red")) {
                        world.cells[i][j].ant.setBrain(redAntBrain);
                        world.cells[i][j].ant.setId(id++);
                        ants.add(world.cells[i][j].ant);
                    }
                }
            }
        }
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
