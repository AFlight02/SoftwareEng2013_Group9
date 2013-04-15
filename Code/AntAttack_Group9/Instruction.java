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
    // Allows for retrieval of ENUM vals of each Instruction subclass for use as parameters on creation of new instructions
    // in the FSM

    /**
     *
     * @param <T>
     * @param c
     * @param string
     * @return
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
