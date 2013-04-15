/**
 * Sense.java Represents a Sense Instruction.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Sense extends Instruction {

    /**
     *
     */
    public enum senseDir {

        HERE,
        AHEAD,
        LEFTAHEAD,
        RIGHTAHEAD
    }

    /**
     *
     */
    public enum condition {

        FRIEND,
        FOE,
        FRIENDWITHFOOD,
        FOEWITHFOOD,
        FOOD,
        ROCK,
        MARKER0,
        MARKER1,
        MARKER2,
        MARKER3,
        MARKER4,
        MARKER5,
        FOEMARKER,
        HOME,
        FOEHOME
    }
    private senseDir senseDirVal;
    private condition condVal;
    private int s1;
    private int s2;

    /**
     *
     * @param sDir
     * @param s1
     * @param s2
     * @param con
     */
    public Sense(senseDir sDir, int s1, int s2, condition con) {
        this.senseDirVal = sDir;
        this.condVal = con;
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     *
     * @return
     */
    public condition getCondVal() {
        return condVal;
    }

    /**
     *
     * @return
     */
    public int getS1() {
        return s1;
    }

    /**
     *
     * @return
     */
    public int getS2() {
        return s2;
    }

    /**
     *
     * @return
     */
    public senseDir getSenseDirVal() {
        return senseDirVal;
    }

    /**
     *
     * @param name
     * @return
     */
    public static senseDir dirFromString(String name) {
        return getEnumFromString(senseDir.class, name);
    }

    /**
     *
     * @param name
     * @return
     */
    public static condition condFromString(String name) {
        return getEnumFromString(condition.class, name);
    }
}
