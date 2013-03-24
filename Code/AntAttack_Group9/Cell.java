package AntAttack_Group9;
import java.util.*;

public class Cell {

	Ant ant = null; // Ensure blank when no Ants exist
        int[] pos = new int[2];
        int food;
        int adjacentAntsRed; // Counter updates each cycle, used to check if Ant dies in combat case
	int adjacentAntsBlack;
	boolean rock;
	boolean[] markersRed; // Length 6 array, where marker num = i+1, structure is false, false, true, false etc..
	boolean[] markersBlack;

	public Cell(int x, int y) {
            this.pos[0] = x;
            this.pos[1] = y;
	}
	
	public void setAnt() {
		// Update Ant on this cell
	}
        
        public int[] adjacentCell(int dir) {
            int[] adjCell = new int[2];
            switch(dir) {
                case 0: 
                    adjCell[0] = pos[0]++;
                    adjCell[1] = pos[1];
                    break;
                case 1:
                    if(pos[1]%2 == 0) {
                        adjCell[0] = pos[0];
                        adjCell[1] = pos[1]++;
                    } else {
                        adjCell[0] = pos[0]++;
                        adjCell[1] = pos[1]++;
                    }
                    break;
                case 2:
                    if(pos[1]%2 == 0) {
                        adjCell[0] = pos[0]--;
                        adjCell[1] = pos[1]++;
                    } else {
                        adjCell[0] = pos[0];
                        adjCell[1] = pos[1]++;
                    }
                    break;
                case 3:
                    adjCell[0] = pos[0]--;
                    adjCell[1] = pos[1];
                    break;
                case 4:
                    if(pos[1]%2 == 0) {
                        adjCell[0] = pos[0]--;
                        adjCell[1] = pos[1]--;
                    } else {
                        adjCell[0] = pos[0];
                        adjCell[1] = pos[1]--;
                    }
                    break;
                case 5:
                    if(pos[1]%2 == 0) {
                        adjCell[0] = pos[0];
                        adjCell[1] = pos[1]--;
                    } else {
                        adjCell[0] = pos[0]++;
                        adjCell[1] = pos[1]--;
                    }
                    break;
            }
            return adjCell;
        }
        
        public int[] sensedCell(int[] pos, int dir, Sense.senseDir sD) {
            int[] sensedCellPos = new int[2];
            switch(sD) {
                case HERE:
                    sensedCellPos = pos;
                    break;
                case AHEAD:
                    sensedCellPos = adjacentCell(dir);
                    break;
                case LEFTAHEAD:
                    sensedCellPos = adjacentCell(ant.turn(Turn.direction.LEFT));
                    break;
                case RIGHTAHEAD:
                    sensedCellPos = adjacentCell(ant.turn(Turn.direction.RIGHT));
                    break;
            }
            return sensedCellPos;
        }

	public int[] returnContents() {
		// Returns attributes of this cell, may need to divide this into methods:
		// bool getRock(){}
		// int getFood(){}
		// bool[] getMarkers(bool colour){}
            int[] result = new int[1]; // Placeholder!
            return result;
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
