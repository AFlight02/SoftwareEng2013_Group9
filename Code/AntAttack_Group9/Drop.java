
package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Drop implements Instruction {
    private int state;
    
    public Drop(int state) {
        this.state = state;
    }
    
    public int getNextState() {
        return this.state;
    }
}
