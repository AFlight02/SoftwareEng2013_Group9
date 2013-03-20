package AntAttack_Group9
import java.util.*;

public class Cell {

	Ant ant = null; // Ensure blank when no Ants exist
	boolean rock;
	int food;
	boolean[] markersRed; // Length 6 array, where marker num = i+1, structure is false, false, true, false etc..
	boolean[] markersBlack;
	int adjacentAntsRed; // Counter updates each cycle, used to check if Ant dies in combat case
	int adjacentAntsBlack;

	public Cell() {
		// Cell constructor
	}
	
	public void setAnt() {
		// Update Ant on this cell
	}

	public int[] returnContents() {
		// Returns attributes of this cell, may need to divide this into methods:
		// bool getRock(){}
		// int getFood(){}
		// bool[] getMarkers(bool colour){}
	}

	public void removeAnt() {
		// Set ant to null
	}

	public void addRedMarker(int markerNum) {
		// Change markersRed[markerNum] to true
	}

	public void addBlackMarker(int markerNum) {
		// As red
	}

	public void calculateAdjacentAnts() {
		// Iterate through World cells in radius 1 around this cell and update adjacent ants count as necessary
	}

}
