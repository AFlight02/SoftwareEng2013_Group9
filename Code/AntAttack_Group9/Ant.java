/**
 * Ant.java Represents an instance of an individual Ant agent within the
 * AntAttack simulation. Each Ant shares a common AntBrain that manages the
 * behaviour of all Ants of a particular team throughout a Tournament and
 * individual Game
 * 
* @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9; // Need to combine everything into said package for portability and integration of classes together

public class Ant {

    private AntBrain brain;
    private boolean colour; // true=black, false=red
    private boolean hasFood; // Missed an attribute in class dia...woops!
    private int resting;
    private int state;
    private int direction;
    private int id;
    private int[] position = new int[2]; // (col, row)
    private boolean alive;

    /**
     *
     * @param brain
     * @param colour
     * @param id
     */
    public Ant(AntBrain brain, boolean colour, int id) {
        this.brain = brain;
        this.colour = colour;
        this.id = id;
        this.hasFood = false;
        this.resting = 0;
        this.state = 0;
        this.direction = 0;
        this.alive = true;
        this.position[0] = -1;
        this.position[1] = -1;
    }

    /**
     *
     */
    public void kill() {
        this.alive = false;
    }

    /**
     *
     * @return
     */
    public boolean isAlive() {
        return alive;
    }

    public int[] getPosition() {
        return this.position;
    }

    /**
     *
     * @return
     */
    public int getResting() {
        return this.resting;
    }

    /**
     *
     */
    public void decrementResting() {
        this.resting--;
    }

    /**
     *
     * @return
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     *
     * @return
     */
    public boolean getFood() {
        return this.hasFood;
    }

    /**
     *
     * @return
     */
    public boolean getColour() {
        return this.colour;
    }

    public void setPostition(int[] newPos) {
        this.position = newPos;
    }

    /**
     *
     * @param newState
     */
    public void setState(int newState) {
        this.state = newState;
    }

    /**
     *
     * @param resting
     */
    public void setResting(int resting) {
        this.resting = resting;
    }

    /**
     *
     * @param dir
     */
    public void setDirection(int dir) {
        this.direction = dir;
    }

    /**
     *
     * @param b
     */
    public void setFood(boolean b) {
        this.hasFood = b;
    }

    /**
     *
     * @param dir
     * @return
     */
    public int turn(Turn.direction dir) {
        switch (dir) {
            case LEFT:
                return (this.direction + 5) % 6;
            case RIGHT:
                return (this.direction + 1) % 6;
            default:
                return -1;
        }
    }

    /**
     *
     * @return
     */
    public Instruction getInstruction() {
        return this.brain.getInstruction(this.state);
    }
}
