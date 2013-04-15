/**
 * Turn.java Represents a Turn Instruction.
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Turn extends Instruction {

    /**
     * Direction enums represent Left and Right turns.
     */
    public enum direction {

        LEFT,
        RIGHT
    }
    private direction lr;
    private int state;

    /**
     * Turn Instruction takes a direction and the next state.
     * @param lr the direction to turn
     * @param state the next state after the turn
     */
    public Turn(direction lr, int state) {
        this.lr = lr;
        this.state = state;
    }

    /**
     * Get the turn direction enum
     * @return enum representing turn direction
     */
    public direction getTurnDir() {
        return this.lr;
    }

    /**
     * Get the next state
     * @return next state
     */
    public int getS1() {
        return this.state;
    }

  /**
     * Get a direction enum by passing a String for comparison.
     * @param name the name of the direction as a String
     * @return the direction as an enum
     */
    public static direction dirFromString(String name) {
        return getEnumFromString(direction.class, name);
    }
}
