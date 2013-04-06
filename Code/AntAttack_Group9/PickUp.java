package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class PickUp extends Instruction {

    private int state1;
    private int state2;

    public PickUp(int state1, int state2) {
        this.state1 = state1;
        this.state2 = state2;
    }

    public int getS1() {
        return this.state1;
    }

    public int getS2() {
        return this.state2;
    }
}
