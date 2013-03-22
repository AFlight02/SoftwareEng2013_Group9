/**
 * Ant.java
 * Represents an instance of an individual Ant agent within the AntAttack simulation. Each Ant shares a common AntBrain
 * that manages the behaviour of all Ants of a particualr team throughout a Tournament and individual Game
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale, Paige Gray, Matt Chapman, Alex Flight (77525), ??James Bellamy??
 * @version 0.1
 */
package AntAttack_Group9 // Need to combine everything into said package for portability and integration of classes together
import java.util.*;

public class Ant {
	
	AntBrain brain;
	boolean colour;
	boolean hasFood; // Missed an attribute in class dia...woops!
	int state;
	int direction;
	
	public AntAttack() {
		// Constrcutor
	}

	public void getState() {
		// Return state
	}

	public void changeDir(int newDirection) {
		// Update direction with passed new directon
	}
	
	public void move() {
		// Remove Ant from the Cell it currently resides in, add to the cell one cell in front of direction
	}

	public void pickUpFood() {
		// Change hasFood to true
	}

	public void dropFood() {
		// Change hasFood to false
	}

	public int markCell() {
		// Return marker number to mark the current Cell
	}

	public void senseCell() {
		// Get the attributes of cell in front, make call to change state based on what is sensed
	}

	public void updateState() {
		// Call to brain with current state as param
		// state = brain.getState(state);
		// Update state with result
	}
	
}
