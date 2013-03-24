
package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Mark extends Instruction {
    private int state;
    private int marker;
    
    public Mark(int state, int marker) {
        this.state = state;
        this.marker = marker;
    }
    
    public int getState() {
        return this.state;
    }
    
    public int getMarker() {
        return this.marker;
    }
}
