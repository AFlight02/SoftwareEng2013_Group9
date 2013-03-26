package AntAttack_Group9;
import AntAttack_Group9.Sense.condition;
import AntAttack_Group9.Sense.senseDir;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class AntBrain {

	//FSM brain; // need to find suitable FSM library/class
	/* Alex: Perhaps we use a structure like this:
	 * BufferedReader reader; // Reads in AntBrain text file like those in examples for project
	 * FSMClassOfSomeKind fsm; // The Data structure of the fsm as read from the file
	 */
	ArrayList<Instruction> fsm = new ArrayList();
	int gamesPlayed;
	int gamesWon;
	int state;
	
	public AntBrain(String antBrainFile) {
            try {
                if(checkAntBrainSyntax(antBrainFile)){
                    BufferedReader br = new BufferedReader(new FileReader(antBrainFile));
                    String currLine;
                    while((currLine = br.readLine()) != null) {
                        String[] input = currLine.split("\\s");
                        Instruction i = null;
                        switch(input[0]) {
                            case "sense":
                                i = new Sense(Sense.dirFromString(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Sense.condFromString(input[4]));
                                break;
                            case "mark":
                                i = new Mark(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                break;
                            case "unmark":
                                i = new Unmark(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                break;
                            case "pickUp":
                                i = new PickUp(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                break;
                            case "drop":
                                i = new Drop(Integer.parseInt(input[1]));
                                break;
                            case "turn":
                                i = new Turn(Turn.dirFromString(input[1]), Integer.parseInt(input[2]));
                                break;
                            case "move":
                                i = new Move(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                                break;
                            case "flip":
                                i = new Flip(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                                break;
                        }
                        fsm.add(i);
                    }
                }else{
                    // exception should be thrown by checkAntBrainSyntax by this point
                    System.out.println("Error in ant brain file");
                }
            }
            catch (Exception e) {
                System.err.println("Error: " + e.getMessage()); 
            }
	}
    
    /**
     * Check if ant brain file is syntactically correct
     * iterates through passed file line by line
     * @param antBrainFile the ant brain file (.brain) to check
     * @return boolean true if ant brain is syntactically correct false if not
     */
	public boolean checkAntBrainSyntax(String antBrainFile) throws Exception {
		
        BufferedReader br = new BufferedReader(new FileReader(file));
        String currLine;
        
        // process each line in ant brain file
        while((currLine = br.readLine()) != null) {
            
            // convert current line to lowercase
            currLine = currLine.toLowerCase();
            
            // store variables used for regular expression
            String st = "[0-9][0-9]{0,3}";
            String i = "[0-5]";
            
            // check Sense
            String regex = "((sense)*\\s(here|ahead|leftahead|rightahead)\\s("+st+")\\s("+st+")\\s(friend|foe|friendwithfood|foewithfood|food|rock|(marker\\s"+i+")|foemarker|home|foehome))";
            
            // check Mark and Unmark
            regex += "|((mark|unmark)*\\s("+i+")\\s("+st+"))";

            // check PickUp and Move
            regex += "|((pickup|move)*\\s("+st+")\\s("+st+"))";

            // check Drop
            regex += "|((drop)*\\s("+st+"))";

            // check Turn
            regex += "|((turn)*\\s(left|right)\\s("+st+"))";

            // check Flip
            regex += "|((flip)*\\s([0-9]*)\\s("+st+")\\s("+st+"))";

            // perform regular expression
            Pattern p1 = Pattern.compile(regex);
            Matcher m = p1.matcher(currLine);
            
            // if current line doesn't match regular expression, print error
            // to terminal and return false (could possibly throw exception here?)
            if(!m.matches()){
                //System.out.println("Error in java brain file "+file+" at line "+lineNumber+ ": "+currLine);
                throw new Exception("Error in java brain file "+file+" at line "+lineNumber+ ": "+currLine);
                //return false;
            }
        }
        return true;
	}

	public int getInstruction(int currState) {
		// Pass state to FSM, get the next state, update state attrib, return int representing instruction:
		// 0 = wait, 1 = turn left, 2 = turn right, 3 = move, etc...
            return 0;
	}

}
