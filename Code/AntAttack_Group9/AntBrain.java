/**
 * AntBrain.java Represents the FSM of an individual AntBrain competitor, reads
 * in a raw text representation of an Ant Brain and converts it into a list of
 * discrete instructions.
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AntBrain {

    private ArrayList<Instruction> fsm = new ArrayList();
    private String name;

    /**
     * Constructor initialises an AntBrain from a given filename located within the root folder; it then
     * proceeds to check the syntax of the file before reading it line by line and translating it into Instructions, these
     * instructions are then added to an ArrayList of Instructions that represent the whole AntBrain.
     * @param antBrainFile filename of the AntBrain to load
     */
    public AntBrain(String antBrainFile) throws IOException {
        // Printing out diagnostic file copy of each Instrctuion
        try (PrintWriter out = new PrintWriter(new FileWriter(antBrainFile + "output.txt"), true)) {
            name = antBrainFile;
            try {
                // First check the syntax of the brain file is correct, if not throw an error.
                if (checkAntBrainSyntax(antBrainFile)) {
                    BufferedReader br = new BufferedReader(new FileReader(antBrainFile));
                    String currLine;
                    while ((currLine = br.readLine()) != null) {
                        currLine = currLine.toLowerCase();
                        String[] input = currLine.split("\\s");
                        Instruction i = null;
                        // Switch-case based upon first string in the current line
                        switch (input[0]) {
                            case "sense":
                                String markerNum = "";
                                if (input.length >= 6) {
                                    String regexMarker = "[0-5]";
                                    Pattern p1 = Pattern.compile(regexMarker);
                                    Matcher m = p1.matcher(input[5]);
                                    if (m.matches()) {
                                        markerNum = input[5];
                                    }
                                }
                                String cond = input[4] + markerNum;
                                i = new Sense(Sense.dirFromString(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Sense.condFromString(cond));
                                out.write(input[0] + " " + input[1] + " " + input[2] + " " + input[3] + " " + cond + "\n");
                                break;
                            case "mark":
                                i = new Mark(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                out.write(input[0] + " " + input[1] + " " + input[2] + "\n");
                                break;
                            case "unmark":
                                i = new Unmark(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                out.write(input[0] + " " + input[1] + " " + input[2] + "\n");
                                break;
                            case "pickup":
                                i = new PickUp(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                out.write(input[0] + " " + input[1] + " " + input[2] + "\n");
                                break;
                            case "drop":
                                i = new Drop(Integer.parseInt(input[1]));
                                out.write(input[0] + " " + input[1] + "\n");
                                break;
                            case "turn":
                                i = new Turn(Turn.dirFromString(input[1]), Integer.parseInt(input[2]));
                                out.write(input[0] + " " + input[1] + " " + input[2] + "\n");
                                break;
                            case "move":
                                i = new Move(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                out.write(input[0] + " " + input[1] + " " + input[2] + "\n");
                                break;
                            case "flip":
                                i = new Flip(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                                out.write(input[0] + " " + input[1] + " " + input[2] + " " + input[3] + "\n");
                                break;
                        }
                        fsm.add(i);
                    }
                } else {
                    // Exception should be thrown by checkAntBrainSyntax by this point
                    System.out.println("Error in ant brain file");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Check if ant brain file is syntactically correct iterates through passed
     * file line by line
     *
     * @param antBrainFile the ant brain file (.brain) to check
     * @return boolean true if ant brain is syntactically correct false if not
     * @throws Exception
     */
    public boolean checkAntBrainSyntax(String antBrainFile) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(antBrainFile));
        String currLine;
        int lineNumber = 0;

        // process each line in ant brain file
        while ((currLine = br.readLine()) != null) {

            lineNumber++;
            // convert current line to lowercase
            currLine = currLine.toLowerCase();

            // store variables used for regular expression
            String st = "[0-9][0-9]{0,3}";
            String i = "[0-5]";
            String comment = "(\\s*?;.*$)?"; // ignore everything after ;

            // check Sense
            String regex = "((sense)*\\s(here|ahead|leftahead|rightahead)\\s(" + st + ")\\s(" + st + ")\\s(friend|foe|friendwithfood|foewithfood|food|rock|(marker\\s" + i + ")|foemarker|home|foehome))" + comment;

            // check Mark and Unmark
            regex += "|((mark|unmark)*\\s(" + i + ")\\s(" + st + "))" + comment;

            // check PickUp and Move
            regex += "|((pickup|move)*\\s(" + st + ")\\s(" + st + "))" + comment;

            // check Drop
            regex += "|((drop)*\\s(" + st + "))" + comment;

            // check Turn
            regex += "|((turn)*\\s(left|right)\\s(" + st + "))" + comment;

            // check Flip
            regex += "|((flip)*\\s([0-9]*)\\s(" + st + ")\\s(" + st + "))" + comment;

            // perform regular expression
            Pattern p1 = Pattern.compile(regex);
            Matcher m = p1.matcher(currLine);

            // if current line doesn't match regular expression, print error
            // to terminal and return false (could possibly throw exception here?)
            if (!m.matches()) {
                //System.out.println("Error in java brain file "+file+" at line "+lineNumber+ ": "+currLine);
                throw new Exception("Error in java brain file " + antBrainFile + " at line " + lineNumber + ": " + currLine);
                //return false;
            }
        }
        return true;
    }

    /**
     * Returns the Instruction within the ArrayList of Instructions at the specified index, where index == state number.
     * @param currState the next state number
     * @return Instruction at the specified state
     */
    public Instruction getInstruction(int currState) {
        return fsm.get(currState);
    }

     /**
     * Returns the name of this AntBrain (the original file name of the brain file).
     * @return the name of this AntBrain 
     */
    public String getName() {
        return name;
    }
}
