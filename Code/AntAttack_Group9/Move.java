/**
 * Move.java Represents a Move Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Move extends Instruction {

    private int state1;
    private int state2;

    /**
     * Creates a new Move Instruction with state 1 on success, state on failure
     * @param s1 next state of successful move
     * @param s2 next state of unsuccessful move
     */
    public Move(int s1, int s2) {
        this.state1 = s1;
        this.state2 = s2;
    }

    /**
     * Gets the next state if move was successful
     * @return next state
     */
    public int getS1() {
        return this.state1;
    }

    /**
     * Gets the next state if the move was not successful
     * @return next state
     */
    public int getS2() {
        return this.state2;
    }
}
