/**
 * Flip.java Represents a Flip Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */

package AntAttack_Group9;

public class Flip extends Instruction {

    private int random;
    private int state1;
    private int state2;

    /**
     * 
     * @param random
     * @param initState
     * @param nextState
     */
    public Flip(int random, int s1, int s2) {
        this.random = random;
        this.state1 = s1;
        this.state2 = s2;
    }

    /**
     * 
     * @return
     */
    public int getRandom() {
        return this.random;
    }

    /**
     * 
     * @return
     */
    public int getS1() {
        return this.state1;
    }

    /**
     * 
     * @return
     */
    public int getS2() {
        return this.state2;
    }
}