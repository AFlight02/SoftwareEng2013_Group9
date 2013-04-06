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
    public Flip(int random, int initState, int nextState) {
        this.random = random;
        this.state1 = initState;
        this.state2 = nextState;
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
    /*
     * TODO - Specification is unclear, what's i? Why do it this way, seems like
     * it's just there to annoy...! public int randomInt(int n) { int seed =
     * 12345; List<Integer> s = null; s.add(seed); List<Integer> x = null;
     * for(int i=0; i<99+4; i++) { int next = s.get(i) * 22695477 + 1;
     * s.add(next); } for(int j=0; j<99; j++) { int next = (s.get(j+4)/65536) %
     * 16384; x.add(next); } return x.get(0) % n; }
     */
}
