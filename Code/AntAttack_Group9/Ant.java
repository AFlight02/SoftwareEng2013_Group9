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
     * Constructor initialises an Ant with a given AntBrain, colour (true=black, false=red) and 
     * an ID number, additionally initialises all attributes to their defaults.
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
     * Sets the Ant to alive = false.
     */
    public void kill() {
        this.alive = false;
    }

    /**
     * Returns the alive state of this Ant.
     * @return true if dlive, false if dead.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Returns the coordinate position of the Ant in the World.
     * @return An int array of coordinates [x,y]
     */
    public int[] getPosition() {
        return this.position;
    }

    /**
     * Returns the resting state of this Ant.
     * @return int indicating how many turns this Ant has left to rest before moving
     */
    public int getResting() {
        return this.resting;
    }

    /**
     * Decreases the resting attribute of this Ant by 1.
     */
    public void decrementResting() {
        this.resting--;
    }

    /**
     * Returns the current direction the Ant is facing.
     * @return int from 0-5 representing the direction the Ant is facing
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * Returns whether this Ant has food.
     * @return true if the Ant has food, false otherwise
     */
    public boolean getFood() {
        return this.hasFood;
    }

    /**
     * Returns the colour of this Ant.
     * @return true if the Ant is Black, false if the Ant is Red
     */
    public boolean getColour() {
        return this.colour;
    }

    /**
     * Updates the position of this Ant to the new coordinates given.
     * @param newPos int[2] array of Cell coordiante in the World [x,y]
     */
    public void setPostition(int[] newPos) {
        this.position = newPos;
    }

    /**
     * Sets this Ant's state to the one specified by an Instruction.
     * @param newState int of the next state the Ant should be in
     */
    public void setState(int newState) {
        this.state = newState;
    }

    /**
     * Sets the Ant's resting value to a new value.
     * @param resting int indicating how many turns this Ant should rest before moving
     */
    public void setResting(int resting) {
        this.resting = resting;
    }

    /**
     * Sets the Ant's new direction.
     * @param dir int indicating the new direction the Ant should face
     */
    public void setDirection(int dir) {
        this.direction = dir;
    }

    /**
     * Sets the food boolean this Ant to true or false.
     * @param b true to indicate the Ant has food, false to indicate that it does not
     */
    public void setFood(boolean b) {
        this.hasFood = b;
    }

    /**
     * Cause this Ant to return the direction it would be facing if it turned Left or Right
     * @param dir the direction to turn, LEFT or RIGHT, taken from Turn.direction Enums
     * @return the new direction the Ant would be facing after turning that direction
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
     * Returns the Instruction that is located in the AntBrain at this Ant's current state.
     * @return the Ant's next Instruction
     */
    public Instruction getInstruction() {
        return this.brain.getInstruction(this.state);
    }
}
