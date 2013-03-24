package AntAttack_Group9;
import AntAttack_Group9.Sense.condition;
import AntAttack_Group9.Sense.senseDir;
import java.util.*;
import java.io.*;

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
            }
            catch (Exception e) {
                System.err.println("Error: " + e.getMessage()); 
            }
	}

	public void checkAntBrainSyntax(File brainFile) {
		// Syntax analysis iterates through passed file line by line
		// Valid syntax: COMMAND_TOKEN NEW_STATE etc.
	}

	public int getInstruction(int currState) {
		// Pass state to FSM, get the next state, update state attrib, return int representing instruction:
		// 0 = wait, 1 = turn left, 2 = turn right, 3 = move, etc...
            return 0;
	}

}
