/**
 * Cell.java Represents an individual 'Tile' in a Tournament World
 * 
* @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public class Cell {

    protected Ant ant = null; // Ensure blank when no Ants exist
    protected String anthill; // "red"|"black"|"none" //null value causes errors when using switch statements
    protected int food;
    protected boolean rock;
    protected int adjacentAntsRed; // Counter updates each cycle, used to check if Ant dies in combat case
    protected int adjacentAntsBlack;
    private int[] pos = new int[2];
    private boolean[] markersRed = new boolean[6]; // Length 6 array, where marker num = i, structure is false, false, true, false etc..
    private boolean[] markersBlack = new boolean[6];

    /**
     * Cell constructor initiates an empty cell at the specified coordinates in the World.
     * @param x the x coordinate of the Cell
     * @param y the y coordinate of the Cell
     */
    public Cell(int x, int y) {
        // Passing default values to prevent null pointer exceptions during testing
        this.pos[0] = x;
        this.pos[1] = y;
        this.rock = false;
        this.adjacentAntsBlack = 0;
        this.adjacentAntsRed = 0;
        this.anthill = "none";
        this.food = 0;
        for (int i = 0; i < 6; i++) {
            markersRed[i] = false;
            markersBlack[i] = false;
        }
    }

    /**
     * Get the adjacent Cell coordinates to this Cell in the specified direction
     * @param dir the direction of the adjacent Cell to find
     * @return coordinates [x,y] of the adjacent Cell in the direction specified
     */
    public int[] adjacentCell(int dir) {
        int[] adjCell = new int[2];
        switch (dir) {
            case 0:
                adjCell[0] = pos[0];
                adjCell[1] = pos[1] + 1;
                break;
            case 1:
                if (pos[0] % 2 == 0) {
                    adjCell[0] = pos[0] + 1;
                    adjCell[1] = pos[1];
                } else {
                    adjCell[0] = pos[0] + 1;
                    adjCell[1] = pos[1] + 1;
                }
                break;
            case 2:
                if (pos[0] % 2 == 0) {
                    adjCell[0] = pos[0] + 1;
                    adjCell[1] = pos[1] - 1;
                } else {
                    adjCell[0] = pos[0] + 1;
                    adjCell[1] = pos[1];
                }
                break;
            case 3:
                adjCell[0] = pos[0];
                adjCell[1] = pos[1] - 1;
                break;
            case 4:
                if (pos[0] % 2 == 0) {
                    adjCell[0] = pos[0] - 1;
                    adjCell[1] = pos[1] - 1;
                } else {
                    adjCell[0] = pos[0] - 1;
                    adjCell[1] = pos[1];
                }
                break;
            case 5:
                if (pos[0] % 2 == 0) {
                    adjCell[0] = pos[0] - 1;
                    adjCell[1] = pos[1];
                } else {
                    adjCell[0] = pos[0] - 1;
                    adjCell[1] = pos[1] + 1;
                }
                break;
        }
        return adjCell;
    }

    /**
     * Returns the coordinates of a Cell based on the passed Sense Direction.
     * @param pos the position of the Cell being sense from
     * @param dir the direction of the Ant sensing from the specified Cell
     * @param sD the Sense Direction Enum from the Sense.senseDir Class
     * @return [x,y] coordinates of the sensed Cell
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
                sensedCellPos = adjacentCell((dir + 5) % 6);
                break;
            case RIGHTAHEAD:
                sensedCellPos = adjacentCell((dir + 1) % 6);
                break;
        }
        return sensedCellPos;
    }

    /**
     * Finds the Cells surrounding this one and calculates how many Black and Red Ants surround it.
     * @param world the World this Cell is contained within
     */
    public void calculateAdjacentAnts(World world) {
        // Iterate through World cells in radius 1 around this cell and update adjacent ants count as necessary
        adjacentAntsBlack = 0;
        adjacentAntsRed = 0;

        for (int i = 0; i < 6; i++) {
            int[] adjCell = adjacentCell(i);
            Cell cell = world.getCell(adjCell);
            if (cell != null && cell.getAnt() != null) {
                Ant adjAnt = cell.getAnt();
                if (adjAnt.getColour()) { //BLACK
                    adjacentAntsBlack++;
                } else if (!adjAnt.getColour()) {
                    adjacentAntsRed++;
                }
            }
        }
    }

    /**
     * Returns the number of Black Ants adjacent to this cell.
     * @return number of adjacent Black Ants
     */
    public int getAdjacentAntsBlack() {
        return adjacentAntsBlack;
    }

    /**
     * Returns the number of Red Ants adjacent to this cell.
     * @return number of adjacent Red Ants
     */
    public int getAdjacentAntsRed() {
        return adjacentAntsRed;
    }

    /**
     * Returns whether this Cell contains a Rock
     * @return true if this Cell contains a Rock, false otherwise
     */
    public boolean getRock() {
        return rock;
    }

    /**
     * Sets whether a Rock is in this Cell
     * @param rock true to set a Rock in this Cell, false to remove a Rock
     */
    public void setRock(boolean rock) {
        this.rock = rock;
    }

    /**
     * Returns the amount of food in the Cell
     * @return amount of food particles in this Cell
     */
    public int getFood() {
        return food;
    }

    /**
     * Updates the number of food particles in this Cell
     * @param food the total food to put in this Cell
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * Returns the Ant in this Cell
     * @return the Ant present in this Cell
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * Adds an Ant to this Cell
     * @param ant the Ant to add to this Cell
     */
    public void setAnt(Ant ant) {
        // Update Ant on this cell
        this.ant = ant;
    }

    /**
     * Sets the Ant in this Cell to null, thus removing them.
     */
    public void removeAnt() {
        // Set ant to null
        this.ant = null;
    }

    /**
     * Returns the Anthill on this Cell
     * @return "black", "red" or "none"
     */
    public String getAnthill() {
        return anthill;
    }

    /**
     * Sets the Anthill value of this Cell
     * @param a set Anthill of the Cell to "red", "black" or "none"
     */
    public void setAnthill(String a) {
        anthill = a;
    }

    /**
     * Returns all the markers on the Cell with a specified colour.
     * @param colour true = black markers, false = red markers
     * @return an array of the markers of the specified colour on this Cell
     */
    public boolean[] getMarkers(boolean colour) {
        if (colour) {
            return markersBlack;
        } else {
            return markersRed;
        }
    }

    /**
     * Adds a Red marker of the specified number to this Cell
     * @param markerNum the marker to add to this Cell
     */
    public void addRedMarker(int markerNum) {
        // Change markersRed[markerNum] to true
        this.markersRed[markerNum] = true;
    }

    /**
     * Adds a Black marker of the specified number to this Cell
     * @param markerNum the marker to add to this Cell
     */
    public void addBlackMarker(int markerNum) {
        // As red
        this.markersBlack[markerNum] = true;
    }

    /**
     * Remove specified Red marker from the Cell.
     * @param markerNum the marker to remove
     */
    public void removeRedMarker(int markerNum) {
        // Change markersRed[markerNum] to true
        this.markersRed[markerNum] = false;
    }

    /**
     * Remove specified Red marker from the Cell.
     * @param markerNum the marker to remove
     */
    public void removeBlackMarker(int markerNum) {
        // As red
        this.markersBlack[markerNum] = false;
    }

    /**
     * Checks if this Cell is empty (does not have a rock, Ant, anthill or food)
     * @return true if the Cell is empty, false if not
     */
    public boolean isEmpty() {
        if (!this.rock && this.ant == null && this.anthill.equals("none") && this.food <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Increases the amount of food in this Cell by one
     */
    public void incrementFood() {
        this.food++;
    }

    /**
     * Decreases the amount of food in this Cell by one if it already has food within it
     */
    public void decrementFood() {
        if (this.food > 0) {
            this.food--;
        }
    }

    /**
     * Used for printing out a world to a file
     *
     * @return string representing the contents of this Cell
     */
    public String getCellSymbol() {
        if (rock) {
            return "#";
        } else if (ant != null) {
            return "a";
        } else if (food != 0) {
            return food + "";
        } else {
            switch (anthill) {
                case "red":
                    return "+";
                case "black":
                    return "-";
                default:
                    return ".";
            }
        }
    }
}
