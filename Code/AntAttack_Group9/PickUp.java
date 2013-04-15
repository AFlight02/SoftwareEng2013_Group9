/**
 * PickUp.java Represents a PickUp Instruction.
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class PickUp extends Instruction {

    private int state1;
    private int state2;

    /**
     * Creates a new PickUp Instruction with the next state as state1 on success, state2 on failure
     * @param state1 next state on success
     * @param state2 next state on failure
     */
    public PickUp(int state1, int state2) {
        this.state1 = state1;
        this.state2 = state2;
    }

    /**
     * Get the successful state
     * @return next state
     */
    public int getS1() {
        return this.state1;
    }

    /**
     * Get the unsuccessful state
     * @return next state
     */
    public int getS2() {
        return this.state2;
    }
}
