/**
 * World.java Represents a World within which a Game is played between two Ant Brains.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */

package AntAttack_Group9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Alex
 */
public class World {

    Cell[][] cells;
    int width, height;
    int totalFood;

    /**
     * 
     */
    public World() {
        // Constructor
    }

    /**
     * reads in a world file, makes sure basic syntax is right
     *
     * @param worldFile
     * @throws Exception  
     */
    public void readInWorld(String worldFile) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(worldFile));
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());
            cells = new Cell[width][height];

            String cellStr = "#|\\.|+|-|[1-9]";
            Pattern cellPat = Pattern.compile(cellStr);

            String rowStr = cellStr + "{" + width + "}";
            Pattern oddRowPat = Pattern.compile("\\s" + rowStr); //because row count starts at 0
            Pattern evenRowPat = Pattern.compile(rowStr);

            String curLine;
            String[] rowCells;
            int rowCnt = 0;
            while ((curLine = br.readLine()) != null) {

                //check that odd or even lines behave correctly
                if (((rowCnt + 1) % 2) == 0) { //even line
                    if (!evenRowPat.matcher(curLine).matches()) {
                        throw new Exception("Malformed world!");
                    }
                } else { //odd line
                    if (!oddRowPat.matcher(curLine).matches()) {
                        throw new Exception("Malformed world!"); //throw ALL the exceptions! \o/
                    }
                }

                //the line is valid, so we can go ahead and look at the individual characters now:
                rowCells = curLine.split("\\s");

                for (int i = 0; i < width; i++) {
                    Matcher m = cellPat.matcher(rowCells[i]);
                    if (m.matches()) {
                        Cell newCell = new Cell(i, rowCnt);
                        switch (rowCells[i]) {
                            case "#":
                                newCell.setRock(true);
                                break;
                            case ".":
                                break;
                            case "+":
                                newCell.setAnthill("red");
                                break;
                            case "-":
                                newCell.setAnthill("black");
                                break;
                            default: //know it's 1-9
                                newCell.setFood(Integer.parseInt(rowCells[i]));
                                break;
                        }
                        cells[rowCnt][i] = newCell;
                    } else {
                        throw new Exception("Malformed world!"); //just in case..
                    }
                }
                rowCnt++;
            }
        } catch (Exception e) {
            //catches things like array boundaries if the file has specified the wrong size map
            throw new Exception("Malformed world!");
        }
    }

    /**
     * 
     */
    public void generateRandomWorld() {
        // If call to this function, create a new well formed world of Cells[]
    }

    /**
     * Call this after reading in a world - checks conditions for a regular game
     * map
     *
     * @return
     */
    public boolean checkValidWorld() {
        try {
            //surrounded by rocks
            for (int i = 0; i < width; i++) { //check top and bottom
                if (!cells[0][i].getRock() || !cells[height - 1][i].getRock()) {
                    return false;
                }
            }

            for (int i = 1; i < height - 1; i++) { //check sides
                if (!cells[i][0].getRock() || !cells[i][width - 1].getRock()) {
                    return false;
                }
            }

            //contains at least one anthill cell of each colour
            boolean foundRed = false;
            boolean foundBlack = false;
            for (Cell[] row : cells) {
                for (Cell c : row) {
                    switch (c.getAnthill()) {
                        case "red":
                            foundRed = true;
                            break;
                        case "black":
                            foundBlack = true;
                            break;
                    }
                }
            }
            if (!foundRed || !foundBlack) {
                return false;
            }

            return true;

        } catch (Exception e) {
            return false; //no world read in, so just return false (or throw exception?)
        }
    }

    /**
     * Call this after reading in a world - checks conditions for a tournament
     * map
     *
     * @return
     */
    public boolean checkValidForTournament() {
        if (checkValidWorld()) {
            //check extra tournament conditions

            //map dimensions
            if (width != 150 || height != 150) {
                return false;
            }

            //contains 14 rocks
            int rockCount = 0;

            //make a list of all the coords with food in
            List<int[]> foodInfo = Arrays.asList(); //[i, j, length], same as anthills
            int consecFood = 0;
            int[] curFoodCoord = new int[2];

            //anthills
            List<int[]> redHillInfo = Arrays.asList(); //[i, j, length] for each consecutive row
            List<int[]> blackHillInfo = Arrays.asList();
            int consecRedHill = 0;
            int consecBlackHill = 0;
            int[] curHillCoord = new int[2];

            for (int i = 1; i < width - 1; i++) { //don't check boundaries
                for (int j = 1; j < height - 1; j++) {
                    if (cells[i][j].getRock()) {
                        rockCount++;
                    }

                    switch (cells[i][j].getFood()) { //contains food
                        case 0:
                            if (consecFood > 0) { //end of a food row
                                int[] info = new int[3];
                                info[0] = curFoodCoord[0];
                                info[1] = curFoodCoord[1];
                                info[2] = consecFood;
                                foodInfo.add(info);
                                consecFood = 0; //reset count
                            }
                            break;
                        case 5:
                            if (consecFood == 0) { //start of a row
                                curFoodCoord[0] = i;
                                curFoodCoord[1] = j;
                            }
                            consecFood++;
                            break;
                        default:
                            return false; //anything other than 0 or 5 food is auto invalid!
                    }

                    switch (cells[i][j].getAnthill()) {
                        case "red":
                            //end black if exists:
                            if (consecBlackHill > 0) {
                                int[] info = new int[3];
                                info[0] = curHillCoord[0];
                                info[1] = curHillCoord[1];
                                info[2] = consecBlackHill;
                                blackHillInfo.add(info);

                                consecBlackHill = 0; //reset
                            }

                            if (consecRedHill == 0) { //start of red row
                                curHillCoord[0] = i;
                                curHillCoord[1] = j;
                            }
                            consecRedHill++;
                            break;
                        case "black":
                            //end red if exists
                            if (consecRedHill > 0) {
                                int[] info = new int[3];
                                info[0] = curHillCoord[0];
                                info[1] = curHillCoord[1];
                                info[2] = consecRedHill;
                                redHillInfo.add(info);

                                consecRedHill = 0; //reset
                            }

                            if (consecBlackHill == 0) { //start of black row
                                curHillCoord[0] = i;
                                curHillCoord[1] = j;
                            }
                            consecBlackHill++;
                            break;
                        default:
                            //end either if exists
                            if (consecRedHill > 0) {
                                int[] info = new int[3];
                                info[0] = curHillCoord[0];
                                info[1] = curHillCoord[1];
                                info[2] = consecRedHill;
                                redHillInfo.add(info);

                                consecRedHill = 0; //reset
                            } else if (consecBlackHill > 0) {
                                int[] info = new int[3];
                                info[0] = curHillCoord[0];
                                info[1] = curHillCoord[1];
                                info[2] = consecBlackHill;
                                blackHillInfo.add(info);

                                consecBlackHill = 0; //reset
                            }
                            break;
                    }
                }
            }

            if (rockCount != 14) {
                return false;
            }

            //now we know the start positions, check the anthill shapes:
            List<int[]> curInfoList; //holds the info list for the colour anthill we are checking

            for (int i = 0; i < 2; i++) { //once for each colour
                if (i == 0) {
                    curInfoList = redHillInfo;
                } else {
                    curInfoList = blackHillInfo;
                }

                int h = 0; //height (row count) --also used to determine the y coord the current row SHOULD be on
                int parityAdjuster;
                int SP; //start pos - the x offset the line SHOULD start on
                int len; //the length the line SHOULD be
                int[] firstCoord = new int[2]; //the ACTUAL start coord of the whole shape
                firstCoord[0] = curInfoList.get(0)[0];
                firstCoord[1] = curInfoList.get(0)[1];

                for (int[] consec : curInfoList) {
                    //parityAdjuster = 1 if we started on an odd row AND the height is odd
                    if (((firstCoord[1] % 2) == 1) && ((h % 2) == 1)) {
                        parityAdjuster = 1;
                    } else {
                        parityAdjuster = 0;
                    }
                    SP = (Math.abs(6 - h)) / 2 - 3 + parityAdjuster;
                    len = 13 - Math.abs(6 - h);
                    //I'll try to explain these a little better outside of the code

                    if (h > 12) { //hexagon too high!
                        return false;
                    } else if (!((consec[0] == firstCoord[0] + SP) //x coord correct
                            && (consec[1] == firstCoord[1] + h) //y coord correct
                            && (consec[2] == len))) { //length of row correct
                        return false;
                    }
                    h++;
                }
            }

            //food blobs
            int blobCount = 0; //the number of food blobs

            for (int[] consec : foodInfo) {
                int parityAdjuster, SP, len, indexOfNextRow;
                int shape; //1 = left-slant, 2 = right-slant, 3 = diamond
                //3 possible shapes
                switch (consec[2]) {
                    case 1:
                        shape = 3; //must be diamond one

                        for (int h = 1; h <= 9; h++) { //9 rows
                            if (((consec[1] % 2) == 1) && ((h % 2) == 1)) {
                                parityAdjuster = 1;
                            } else {
                                parityAdjuster = 0;
                            }

                            SP = (Math.abs(4 - h)) / 2 - 2 + parityAdjuster;
                            len = 5 - Math.abs(4 - h);

                            indexOfNextRow = listContains(foodInfo, SP, consec[1] + h, len);
                            if (indexOfNextRow != -1) {
                                //remove from list
                                foodInfo.remove(indexOfNextRow);
                            } else {
                                return false;
                            }

                        }
                        break;
                    case 5:
                        //determine shape 1 or 2
                        parityAdjuster = consec[1] % 2; //dont need to check height because it is constant 1 for this part

                        indexOfNextRow = listContains(foodInfo, consec[0] - 1 + parityAdjuster, consec[1] + 1, 5);
                        if (indexOfNextRow != -1) {
                            //right-slanting! remove from list
                            shape = 2;
                            foodInfo.remove(indexOfNextRow);
                        } else {
                            //not right-slanting, so try left-slanting
                            indexOfNextRow = listContains(foodInfo, consec[0] + 1 + parityAdjuster, consec[1] + 1, 5);
                            if (indexOfNextRow != -1) {
                                shape = 1;
                                foodInfo.remove(indexOfNextRow);
                            } else {
                                return false; //invalid shape!
                            }
                        }

                        for (int h = 2; h <= 5; h++) { //already found the first 2 rows
                            if (((consec[1] % 2) == 1) && ((h % 2) == 1)) {
                                parityAdjuster = 1;
                            } else {
                                parityAdjuster = 0;
                            }

                            if (shape == 2) {
                                SP = consec[0] - h + parityAdjuster;
                            } else {
                                SP = consec[0] + h + parityAdjuster;
                            }
                            indexOfNextRow = listContains(foodInfo, SP, consec[1] + h, 5);
                            if (indexOfNextRow != -1) {
                                //remove from list
                                foodInfo.remove(indexOfNextRow);
                            } else {
                                return false;
                            }
                        }
                        break;
                    default:
                        return false; //know straightaway that it's invalid
                }
                blobCount++;
            }

            if (blobCount != 11) {
                return false;
            }

            return true;

        } else {
            return false;
        }
    }

    /**
     *
     * @param id the ant ID being searched for
     * @return the cell containing the ant, or null if ant not found
     */
    public Cell findAnt(int id) {
        Cell found = null;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].ant.getID() == id) {
                    found = cells[i][j];
                }
            }
        }
        return found;
    }

    /**
     * 
     * @param cell
     * @param cond
     * @param a
     * @return
     */
    public boolean checkCellStatus(int[] cell, Sense.condition cond, Ant a) {
        if (cond == Sense.condition.FRIEND) {
            if (a.getColour()) {
                if (cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (cond == Sense.condition.FOE) {
            if (a.getColour()) {
                if (cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (cond == Sense.condition.FRIENDWFOOD) {
            if (a.getColour()) {
                if (cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return cells[cell[0]][cell[1]].getAnt().getFood();
                }
            } else {
                if (!cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return cells[cell[0]][cell[1]].getAnt().getFood();
                }
            }
        }
        if (cond == Sense.condition.FOEWFOOD) {
            if (a.getColour()) {
                if (!cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return cells[cell[0]][cell[1]].getAnt().getFood();
                }
            } else {
                if (cells[cell[0]][cell[1]].getAnt().getColour()) {
                    return cells[cell[0]][cell[1]].getAnt().getFood();
                }
            }
        }
        if (cond == Sense.condition.FOOD) {
            if (cells[cell[0]][cell[1]].getFood() != 0) {
                return true;
            }
        }
        if (cond == Sense.condition.ROCK) {
            return cells[cell[0]][cell[1]].getRock();
        }
        if (cond == Sense.condition.MARKER0) {
            //BLACK
            boolean[] temp = cells[cell[0]][cell[1]].getMarkers(a.getColour());
            return temp[0];
        }
        if (cond == Sense.condition.MARKER1) {
            //BLACK
            boolean[] temp = cells[cell[0]][cell[1]].getMarkers(a.getColour());
            return temp[1];
        }
        if (cond == Sense.condition.MARKER2) {
            //BLACK
            boolean[] temp = cells[cell[0]][cell[1]].getMarkers(a.getColour());
            return temp[2];
        }
        if (cond == Sense.condition.MARKER3) {
            //BLACK
            boolean[] temp = cells[cell[0]][cell[1]].getMarkers(a.getColour());
            return temp[3];
        }
        if (cond == Sense.condition.MARKER4) {
            //BLACK
            boolean[] temp = cells[cell[0]][cell[1]].getMarkers(a.getColour());
            return temp[4];
        }
        if (cond == Sense.condition.MARKER5) {
            //BLACK
            boolean[] temp = cells[cell[0]][cell[1]].getMarkers(a.getColour());
            return temp[5];
        }
        if (cond == Sense.condition.FOEMARKER) {
            boolean[] temp = cells[cell[0]][cell[1]].getMarkers(!a.getColour());
            int hasMarkers = 0;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i]) {
                    hasMarkers++;
                }
            }
            if (hasMarkers > 0) {
                return true;
            } else {
                return false;
            }
        }
        if (cond == Sense.condition.HOME) {
            if (a.getColour()) { //BLACK
                if (cells[cell[0]][cell[1]].getAnthill().equalsIgnoreCase("black")) {
                    return true;
                } else {
                    return false;
                }
            } else { //RED
                if (cells[cell[0]][cell[1]].getAnthill().equalsIgnoreCase("red")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (cond == Sense.condition.FOEHOME) {
            if (a.getColour()) { //BLACK
                if (cells[cell[0]][cell[1]].getAnthill().equalsIgnoreCase("black")) {
                    return false;
                } else {
                    return true;
                }
            } else { //RED
                if (cells[cell[0]][cell[1]].getAnthill().equalsIgnoreCase("red")) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private int listContains(List<int[]> info, int x, int y, int len) {
        for (int i = 0; i < info.size(); i++) {
            int[] row = info.get(i);
            if (row[0] == x && row[1] == y && row[2] == len) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * @param cell
     * @return
     */
    public Cell getCell(int[] cell) {
        return cells[cell[0]][cell[1]];
    }

    /**
     * 
     * @param cell
     */
    public void checkCellStatus(int cell) {
        // Return status of cell 
    }

    /**
     * 
     */
    public void countFood() {
    }

    /**
     * 
     * @param cell
     */
    public void setFoodAtCell(int cell) {
        // Modify food at cell
    }

    /**
     * 
     * @param cell
     * @param marker
     * @param colour
     */
    public void setMarkAtCell(int cell, int marker, boolean colour) {
        // Mark cell with number marker of colour where true = black, false = red (need to standardise the bool
        // representation as concrete for the project as true always == black and false always == red!)
    }

    /**
     * 
     * @param antId
     */
    public void killAnt(int antId) {
        // Use findAnt(antId) and make call to clearAntFromCell(cellId). Call Gameplay to remove Ant from list of Ants
    }

    /**
     * 
     * @param cell
     */
    public void clearAntFromCell(int cell) {
        // Remove Ant from the cell
    }

    /**
     * 
     */
    public void visualiseWorld() {
        // NEW: Call to print world to command line representation for visualisation.
        // Later: Write world state to GUI for graphical visualisation.
    }
}
