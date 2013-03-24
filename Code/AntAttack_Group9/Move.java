
package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Move extends Instruction {
    private int state1;
    private int state2;
    
    public Move(int initState, int nextState) {
        this.state1 = initState;
        this.state2 = nextState;
    }
    
    public int getInitDir() {
        return this.state1;
    }
    public int getNextState() {
        return this.state2;
    }
}
