/**
 * PickUp.java Represents a PickUp Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */
package AntAttack_Group9;

public class PickUp extends Instruction {

    private int state1;
    private int state2;

    /**
     * 
     * @param state1
     * @param state2
     */
    public PickUp(int state1, int state2) {
        this.state1 = state1;
        this.state2 = state2;
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
