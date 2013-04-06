package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Turn extends Instruction {

    public enum direction {

        LEFT, RIGHT
    }
    private direction lr;
    private int state;

    public Turn(direction lr, int state) {
        this.lr = lr;
        this.state = state;
    }

    public direction getTurnDir() {
        return this.lr;
    }

    public int getS1() {
        return this.state;
    }

    public static direction dirFromString(String name) {
        return getEnumFromString(direction.class, name);
    }
}
