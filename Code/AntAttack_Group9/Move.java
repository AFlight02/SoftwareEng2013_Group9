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
     *
     * @param initState
     * @param nextState
     */
    public Move(int s1, int s2) {
        this.state1 = s1;
        this.state2 = s2;
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
