/**
 * Move.java Represents a Move Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */

package AntAttack_Group9;

public class Move extends Instruction {

    private int state1;
    private int state2;

    /**
     * 
     * @param initState
     * @param nextState
     */
    public Move(int initState, int nextState) {
        this.state1 = initState;
        this.state2 = nextState;
    }

    /**
     * 
     * @return
     */
    public int getS1() {
        return this.state1;
    }

    /**
     * 
     * @return
     */
    public int getS2() {
        return this.state2;
    }
}
