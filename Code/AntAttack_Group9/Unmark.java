
package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Unmark extends Instruction {
    private int state;
    private int marker;
    
    public Unmark(int state, int marker) {
        this.state = state;
        this.marker = marker;
    }
    
    public int getS1() {
        return this.state;
    }
    
    public int getMarker() {
        return this.marker;
    }
}
