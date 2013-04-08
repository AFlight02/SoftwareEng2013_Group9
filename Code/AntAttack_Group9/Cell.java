/**
 * Cell.java Represents an individual 'Tile' in a Tournament World
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */
package AntAttack_Group9;

public class Cell {

    Ant ant = null; // Ensure blank when no Ants exist
    int[] pos = new int[2];
    int food;
    int adjacentAntsRed; // Counter updates each cycle, used to check if Ant dies in combat case
    int adjacentAntsBlack;
    boolean rock;
    String anthill; // "red"|"black"|null
    boolean[] markersRed = new boolean[6]; // Length 6 array, where marker num = i+1, structure is false, false, true, false etc..
    boolean[] markersBlack = new boolean[6];

    /**
     * 
     * @param x
     * @param y
     */
    public Cell(int x, int y) {
        // Passing default values to prevent null pointer exceptions during testing
        this.pos[0] = x;
        this.pos[1] = y;
        this.rock = false;
        this.adjacentAntsBlack = 0;
        this.adjacentAntsRed = 0;
        this.anthill = null;
        this.food = 0;
        for (int i = 0; i < 6; i++) {
            markersRed[i] = false;
            markersBlack[i] = false;
        }
    }

    /**
     * 
     * @param dir
     * @return
     */
    public int[] adjacentCell(int dir) {
        int[] adjCell = new int[2];
        switch (dir) {
            case 0:
                adjCell[0] = pos[0]++;
                adjCell[1] = pos[1];
                break;
            case 1:
                if (pos[1] % 2 == 0) {
                    adjCell[0] = pos[0];
                    adjCell[1] = pos[1]++;
                } else {
                    adjCell[0] = pos[0]++;
                    adjCell[1] = pos[1]++;
                }
                break;
            case 2:
                if (pos[1] % 2 == 0) {
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
                if (pos[1] % 2 == 0) {
                    adjCell[0] = pos[0]--;
                    adjCell[1] = pos[1]--;
                } else {
                    adjCell[0] = pos[0];
                    adjCell[1] = pos[1]--;
                }
                break;
            case 5:
                if (pos[1] % 2 == 0) {
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

    /**
     * 
     * @param pos
     * @param dir
     * @param sD
     * @return
     */
    public int[] sensedCell(int[] pos, int dir, Sense.senseDir sD) {
        int[] sensedCellPos = new int[2];
        switch (sD) {
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

    /**
     * 
     * @param world
     */
    public void calculateAdjacentAnts(World world) {

        // Iterate through World cells in radius 1 around this cell and update adjacent ants count as necessary

        adjacentAntsBlack = 0;
        adjacentAntsRed = 0;

        for (int i = 0; i < 6; i++) {
            int[] adjCell = adjacentCell(i);
            Cell cell = world.getCell(adjCell);
            Ant adjAnt = cell.getAnt();
            if (adjAnt.getColour()) {
                adjacentAntsBlack++;
            } else {
                adjacentAntsRed++;
            }
        }
    }

    /**
     * 
     * @return
     */
    public int getAdjacentAntsBlack() {
        return adjacentAntsBlack;
    }

    /**
     * 
     * @return
     */
    public int getAdjacentAntsRed() {
        return adjacentAntsRed;
    }

    /**
     * 
     * @return
     */
    public boolean getRock() {
        return rock;
    }

    /**
     * 
     * @param rock
     */
    public void setRock(boolean rock) {
        this.rock = rock;
    }

    /**
     * 
     * @return
     */
    public int getFood() {
        return food;
    }

    /**
     * 
     * @param food
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * 
     */
    public void removeFood() {
        if (food >= 0) {
            food--;
        }
    }

    /**
     * 
     * @return
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * 
     * @param ant
     */
    public void setAnt(Ant ant) {
        // Update Ant on this cell
        this.ant = ant;
    }

    /**
     * 
     */
    public void removeAnt() {
        // Set ant to null
        this.ant = null;
    }

    /**
     * 
     * @return
     */
    public String getAnthill() {
        return anthill;
    }

    /**
     * 
     * @param a
     */
    public void setAnthill(String a) {
        anthill = a; //error checking or not bother?
    }

    /**
     * 
     * @param colour
     * @return
     */
    public boolean[] getMarkers(boolean colour) {
        if (colour) {
            return markersBlack;
        } else {
            return markersRed;
        }
    }

    /**
     * 
     * @param markerNum
     */
    public void addRedMarker(int markerNum) {
        // Change markersRed[markerNum] to true
        this.markersRed[markerNum] = true;
    }

    /**
     * 
     * @param markerNum
     */
    public void addBlackMarker(int markerNum) {
        // As red
        this.markersBlack[markerNum] = true;
    }

    /**
     * 
     * @param markerNum
     */
    public void removeRedMarker(int markerNum) {
        // Change markersRed[markerNum] to true
        this.markersRed[markerNum] = false;
    }

    /**
     * 
     * @param markerNum
     */
    public void removeBlackMarker(int markerNum) {
        // As red
        this.markersBlack[markerNum] = false;
    }
    
    public boolean isEmpty() {
        if(!this.rock && this.ant == null && this.anthill == null) {
            return true;
        } else {
            return false;
        }
    }
}
