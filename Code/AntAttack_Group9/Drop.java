/**
 * Drop.java Represents a Drop Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Drop extends Instruction {

    private int state;

    /**
     * Creates a new Drop Instruction with the next state specified
     * @param state
     */
    public Drop(int state) {
        this.state = state;
    }

    /**
     * Get the next state specified by this Drop Instruction
     * @return the next state
     */
    public int getS1() {
        return this.state;
    }
}
