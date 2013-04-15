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
     * Creates a new Mark Instruction with the specified marker number and next state.
     * @param state next state
     * @param marker marker number
     */
    public Mark(int marker, int state) {
        this.state = state;
        this.marker = marker;
    }

    /**
     * Returns the next state
     * @return next state
     */
    public int getS1() {
        return this.state;
    }

    /**
     * Returns the marker number
     * @return marker number
     */
    public int getMarker() {
        return this.marker;
    }
}
