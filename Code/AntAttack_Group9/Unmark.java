/**
 * Unmark.java Represents an Unmark Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */
package AntAttack_Group9;

public class Unmark extends Instruction {

    private int state;
    private int marker;

    /**
     * 
     * @param state
     * @param marker
     */
    public Unmark(int marker, int state) {
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
