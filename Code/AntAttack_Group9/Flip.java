/**
 * Flip.java Represents a Flip Instruction.
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Flip extends Instruction {

    private int random;
    private int state1;
    private int state2;

    /**
     * Creates a new Flip Instruction
     * @param random the upper limit for use in a random calculation
     * @param s1 the state to advance to if a random function on random returns 0
     * @param s2 the state to advance to if a random function on random does not return 0
     */
    public Flip(int random, int s1, int s2) {
        this.random = random;
        this.state1 = s1;
        this.state2 = s2;
    }

    /**
     * Return the random element of this Flip
     * @return random number bound
     */
    public int getRandom() {
        return this.random;
    }

    /**
     * Return the if random = 0 state
     * @return the first state
     */
    public int getS1() {
        return this.state1;
    }

    /**
     * Return the if random != 0 state
     * @return the second state
     */
    public int getS2() {
        return this.state2;
    }
}