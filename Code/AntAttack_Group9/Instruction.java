package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Instruction {
    // Allows for retrieval of ENUM vals of each Instruction subclass for use as parameters on creation of new instructions
    // in the FSM

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
