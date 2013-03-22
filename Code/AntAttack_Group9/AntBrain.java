package AntAttack_Group9;
import java.util.*;
import java.io.*;

public class AntBrain {

	//FSM brain; // need to find suitable FSM library/class
	/* Alex: Perhaps we use a structure like this:
	 * BufferedReader reader; // Reads in AntBrain text file like those in examples for project
	 * FSMClassOfSomeKind fsm; // The Data structure of the fsm as read from the file
	 */
	
	int gamesPlayed;
	int gamesWon;
	int state;
	
	public AntBrain() {
		// Constructor stuff
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
