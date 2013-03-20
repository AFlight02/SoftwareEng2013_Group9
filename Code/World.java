package AntAttack_Group9
import java.util.*;

public class World {

	Cell[] cells;
	int totalFood;
	
	public World() {
		// Constructor
	}

	public void generateRandomWorld() {
		// If call to this function, create a new well formed world of Cells[]
	}

	public void checkValidWorld() {
		// Iterate through Cells, check each is as expected
	}

	public int findAnt(int id) {
		// Iterate through Cells for Ant of id, return cell index
	}

	public void checkCellStatus(int cell) {
		// Return status of cell 
	}

	public void setFoodAtCell(int cell) {
		// Modify food at cell
	}

	public void setMarkAtCell(int cell, int marker, bool colour) {
		// Mark cell with number marker of colour where true = black, false = red (need to standardise the bool
		// representation as concrete for the project as true always == black and false always == red!)
	}

	public void killAnt(int antId) {
		// Use findAnt(antId) and make call to clearAntFromCell(cellId). Call Gameplay to remove Ant from list of Ants
	}

	public void clearAntFromCell(int cell) {
		// Remove Ant from the cell
	}

}
