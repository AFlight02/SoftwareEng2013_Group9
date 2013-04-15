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
     * Enums representing each valid Sense Direction
     */
    public enum senseDir {

        HERE,
        AHEAD,
        LEFTAHEAD,
        RIGHTAHEAD
    }

    /**
     * Enums representing each valid Sense Condition
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
     * Initialise a new Sense Instruction with a Sense Direction, Sense Condition, next state if condition
     * is true and next state if condition is false.
     * @param sDir the senseDir enum
     * @param s1 the next state on condition = true
     * @param s2 the next state on condition = false
     * @param con the Sense Condition enum
     */
    public Sense(senseDir sDir, int s1, int s2, condition con) {
        this.senseDirVal = sDir;
        this.condVal = con;
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Get the enum value of the Sense Condition
     * @return sense condition
     */
    public condition getCondVal() {
        return condVal;
    }

    /**
     * Get the success state returned when the Condition it met.
     * @return next state
     */
    public int getS1() {
        return s1;
    }

    /**
     * Get the unsuccessful state returned when Condition is NOT met.
     * @return next state
     */
    public int getS2() {
        return s2;
    }

    /**
     * Get the senseDir enum value
     * @return senseDir enum
     */
    public senseDir getSenseDirVal() {
        return senseDirVal;
    }

    /**
     * Get a senseDir enum by passing a String for comparison.
     * @param name the name of the sense Direction as a String
     * @return the senseDir as an enum
     */
    public static senseDir dirFromString(String name) {
        return getEnumFromString(senseDir.class, name);
    }

    /**
     * Get a sense Conition enum by passing a String for comparison.
     * @param name the name of the sense Condition as a String
     * @return the Condition as an enum
     */
    public static condition condFromString(String name) {
        return getEnumFromString(condition.class, name);
    }
}
