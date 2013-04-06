
package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Drop extends Instruction {
    private int state;
    
    public Drop(int state) {
        this.state = state;
    }
    
    public int getS1() {
        return this.state;
    }
}
