
package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Turn implements Instruction {
    private enum leftOrRight {
        LEFT, RIGHT
    }
    private leftOrRight lr;
    private int state;
    
    public Turn(leftOrRight lr, int state) {
        this.lr = lr;
        this.state = state;
    }
    
    public leftOrRight getTurnDir() {
        return this.lr;
    }
    public int getNextState() {
        return this.state;
    }
}
