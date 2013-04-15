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
     *
     */
    public enum direction {

        LEFT,
        RIGHT
    }
    private direction lr;
    private int state;

    /**
     *
     * @param lr
     * @param state
     */
    public Turn(direction lr, int state) {
        this.lr = lr;
        this.state = state;
    }

    /**
     *
     * @return
     */
    public direction getTurnDir() {
        return this.lr;
    }

    /**
     *
     * @return
     */
    public int getS1() {
        return this.state;
    }

    /**
     *
     * @param name
     * @return
     */
    public static direction dirFromString(String name) {
        return getEnumFromString(direction.class, name);
    }
}
