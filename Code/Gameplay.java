package AntAttack_Group9
import java.util.*;

public class Gameplay {

	World world;
	int round;
	int redFood;
	int blackFood;
	Ant[] ants;
	AntBrain redAndBrain;
	AntBrain blackAntBrain;
	
	public Gameplay() {
		// Constructor
	}

	public void generateWorld() {
		// Generate a new World randomly
	}

	public void loadWorld(World uploadWorld) {
		// Update world to an existing World that is passed
	}

	public void stepGame() {
		// Go through all processes in a step of the game and repeat until all steps are done
	}

	public void endGame() {
		// On completion of all steps finish the game, total scores etc.
	}

	public void setupGame() {
		// Calls World syntax checks, AntBrain syntax checks, begins stepping through game instance
	}

	public void loadAntBrains(AntBrain red, AntBrain black) {
		// Loads red and black AntBrains
	}

	public int declareWinner() {
		// Calculates winners and returns 0 for Draw, 1 for Black win, 2 for Red win, -1 for error
	}

}
