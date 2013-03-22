package AntAttack_Group9;
import java.util.*;

public class Tournament {

	List<AntBrain> antBrains;
	Gameplay currGame;
	int[][] matches; // 2D array where [i][j], i = antBrain index, j = antBrain to play against initialise all to 0:
	/*
	 * 0	0=-1,1=0,2=0,3=0,...
	 * 1	0=0,1=-1,2=0,3=0,...
	 * 2	0=0,1=0,2=-1,3=0,...
	 * 3	0=0,1=0,2=0,3=-1,...
	 * ...  0=0,1=0,2=0,3=0,...=-1
	 */
	int[] scores; // Increments points per AntBrain in list, 1 = draw, 2 = win, 0 = loss
	
	public Tournament() {
		// Constructor creates tournment
	}

	public void addCompetitors(AntBrain brain) {
		// Adds AntBrains, requires at least 2 for a Tournament of course
	}

	public void setUpMatches() {
		// Uses indexes in antBrains list. Will be 2n^2 matches (or not, need to calculate!)
	}

	public void playMatch() {
		// Takes match, increments the second value in the 2D array to match up if >= 0 already (avoids match against
		// itself!) until <=2 where each game has been played twice with the teams switching colour
	}

	public void declareWinner() {
		// Returns message of overall highest points in score[] where index of score[] is index of Brain in antBrains list
	}

}
