package AntAttack_Group9;
import java.util.*;

public class Cell {

	Ant ant = null; // Ensure blank when no Ants exist
    int[] pos = new int[2];
    int food;
    int adjacentAntsRed; // Counter updates each cycle, used to check if Ant dies in combat case
	int adjacentAntsBlack;
	boolean rock;
    boolean anthill; // true=black, false=red
	boolean[] markersRed = new boolean[6]; // Length 6 array, where marker num = i+1, structure is false, false, true, false etc..
	boolean[] markersBlack = new boolean[6];

	public Cell(int x, int y) {
        this.pos[0] = x;
        this.pos[1] = y;
        for(int i = 0; i < 6; i++){
            markersRed[i] = false;
            markersBlack[i] = false;
        }
	}
	
	public void setAnt(Ant ant) {
		this.ant = ant;
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

	/*public int[] returnContents() {
		// Returns attributes of this cell, may need to divide this into methods:
		// bool getRock(){}
		// int getFood(){}
		// bool[] getMarkers(bool colour){}
        int[] result = new int[1]; // Placeholder!
        return result;
	}*/
        
    public boolean[] getMarkers(boolean colour) {
        if(colour){
            return markersBlack;
        }else{
            return markersRed;
        }
    }

    public boolean getRock() {
        return rock;
    }

    public void setRock(boolean rock) {
        this.rock = rock;
    }
    
    public int getFood() {
        return food;
    }
    
    public void setFood(int food) {
        this.food = food;
    }
    
    public Ant getAnt() {
        return ant;
    }
    
    public boolean getAnthill() {
        return anthill;
    }
    
    public void setAnthill(boolean a) {
        anthill = a; //error checking or not bother?
    }

	public void removeAnt() {
		// Set ant to null
        this.ant = null;
	}

	public void addRedMarker(int markerNum) {
		// Change markersRed[markerNum] to true
        this.markersRed[markerNum] = true;
	}

	public void addBlackMarker(int markerNum) {
		// As red
        this.markersBlack[markerNum] = true;
	}

	public void calculateAdjacentAnts(World world) {
		// Iterate through World cells in radius 1 around this cell and update adjacent ants count as necessary
        
        adjacentAntsBlack = 0;
        adjacentAntsRed = 0;

        for(int i = 0; i < 6; i++){
            int[] adjCell = adjacentCell(i);
            Cell cell = world.getCell(adjCell);
            Ant adjAnt = cell.getAnt();
            if(adjAnt.getColour()){
                adjacentAntsBlack++;
            }else{
                adjacentAntsRed++;
            }
        }
	}

}
