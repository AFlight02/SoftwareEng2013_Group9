/**
 * Unmark.java Represents an Unmark Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Unmark extends Instruction {

    private int state;
    private int marker;

    /**
     * Unmark Instruction takes a marker number and the next state.
     * @param state the next state after the unmark
     * @param marker the marker number to remove
     */
    public Unmark(int marker, int state) {
        this.state = state;
        this.marker = marker;
    }

    /**
     * Get the next state after a marker is removed.
     * @return next state
     */
    public int getS1() {
        return this.state;
    }

    /**
     * Get the marker number to remove.
     * @return marker number to remove
     */
    public int getMarker() {
        return this.marker;
    }
}
