/**
 * Instruction.java Superclass for all Instructions that are contained within an
 * AntBrain FSM.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Instruction {

    /**
     * Default constructor for Instruction superclass
     */
    public Instruction() {}

    /**
     * Common method for getting an Enum value from Instruction sub-classes by passing a String.
     * @param c the Enum type taken from an Instruction subclass (eg. Sense.direction)
     * @param string the String to match against an Enum
     * @return the Enum matching the specified String
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if (c != null && string != null) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
            }
        }
        return null;
    }
}
