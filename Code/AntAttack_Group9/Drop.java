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
     *
     * @param state
     */
    public Drop(int state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public int getS1() {
        return this.state;
    }
}
