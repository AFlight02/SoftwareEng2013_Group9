/**
 * Mark.java Represents a Mark Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Mark extends Instruction {

    private int state;
    private int marker;

    /**
     *
     * @param state
     * @param marker
     */
    public Mark(int marker, int state) {
        this.state = state;
        this.marker = marker;
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
     * @return
     */
    public int getMarker() {
        return this.marker;
    }
}
