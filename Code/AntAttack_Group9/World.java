/**
 * World.java Represents a World within which a Game is played between two Ant
 * Brains.
 *
 * @author Software Engineering 201213 Group 9  Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 1
 */
package AntAttack_Group9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class World {

    protected Cell[][] cells;
    protected Cell[][] resetCells;
    protected int width, height;
    private List<int[]> anthillCells = new ArrayList<>();
    private List<int[]> foodSpawnCells = new ArrayList<>();

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
            cells = new Cell[height][width];

            String cellStr = "[#\\.\\+\\-[1-9]]";
            Pattern cellPat = Pattern.compile(cellStr);

            String rowStr = "(" + cellStr + "\\s){" + (width - 1) + "}" + cellStr + "\\s?";
            Pattern oddRowPat = Pattern.compile("\\s?" + rowStr); //because row count starts at 0
            Pattern evenRowPat = Pattern.compile(rowStr);

            String curLine;
            String[] rowCells;
            int rowCnt = 0;
            while ((curLine = br.readLine()) != null) {

                //check that odd or even lines behave correctly
                if (((rowCnt) % 2) == 0) { //even line
                    if (!evenRowPat.matcher(curLine).matches()) {
                        throw new Exception("Malformed world!");
                    }
                } else { //odd line
                    if (!oddRowPat.matcher(curLine).matches()) {
                        throw new Exception("Malformed world!"); //throw ALL the exceptions! \o/
                    }
                }

                //the line is valid, so we can go ahead and look at the individual characters now:
                curLine = curLine.trim();
                rowCells = curLine.split("\\s");

                for (int i = 0; i < width; i++) {
                    int[] coord = new int[2];
                    coord[0] = rowCnt;
                    coord[1] = i;
                    Matcher m = cellPat.matcher(rowCells[i]);
                    if (m.matches()) {
                        Cell newCell = new Cell(rowCnt, i);
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
        resetCells = cells.clone();
        for (int i=0; i<cells.length; i++) {
            resetCells[i] = cells[i].clone();
        }
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
            List<int[]> foodInfo = new ArrayList<>(); //[i, j, length], same as anthills
            int consecFood = 0;
            int[] curFoodCoord = new int[2];

            //anthills
            List<int[]> redHillInfo = new ArrayList<>(); //[i, j, length] for each consecutive row
            List<int[]> blackHillInfo = new ArrayList<>();
            int consecRedHill = 0;
            int consecBlackHill = 0;
            int[] curHillCoord = new int[2];

            for (int i = 1; i < height - 1; i++) { //don't check boundaries
                for (int j = 1; j < width - 1; j++) {
                    int[] currCell = new int[2];
                    currCell[0] = i;
                    currCell[1] = j;
                    if (cells[i][j].getRock()) {
                        rockCount++;
                    }
                    if(cells[i][j].getFood() > 0) {
                        foodSpawnCells.add(currCell);
                    }
                    if(!cells[i][j].anthill.equalsIgnoreCase("none")) {
                        anthillCells.add(currCell);
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
                    if (((firstCoord[0] % 2) == 1) && ((h % 2) == 1)) {
                        parityAdjuster = 1;
                    } else {
                        parityAdjuster = 0;
                    }
                    SP = (Math.abs(6 - h)) / 2 - 3 + parityAdjuster;
                    len = 13 - Math.abs(6 - h);
                    //I'll try to explain these a little better outside of the code

                    if (h > 12) { //hexagon too high!
                        return false;
                    } else if (!((consec[1] == firstCoord[1] + SP) //x coord correct
                            && (consec[0] == firstCoord[0] + h) //y coord correct
                            && (consec[2] == len))) { //length of row correct
                        return false;
                    }
                    h++;
                }
            }

            //food blobs
            int blobCount = 0; //the number of food blobs

            while (!foodInfo.isEmpty()) {
                int[] consec = foodInfo.get(0);
                foodInfo.remove(0);

                int parityAdjuster, SP, len, indexOfNextRow;
                boolean leftSlant; //for differentiating between the two 5x5 squares
                //3 possible shapes
                switch (consec[2]) {
                    case 1:
                        //must be diamond one

                        for (int h = 1; h < 9; h++) { //9 rows
                            if (((consec[0] % 2) == 1) && ((h % 2) == 1)) {
                                parityAdjuster = 1;
                            } else {
                                parityAdjuster = 0;
                            }

                            SP = (Math.abs(4 - h)) / 2 - 2 + parityAdjuster;
                            len = 5 - Math.abs(4 - h);

                            indexOfNextRow = listContains(foodInfo, consec[0] + h, consec[1] + SP, len);
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
                        parityAdjuster = consec[0] % 2; //dont need to check height because it is the constant 1 for this part

                        //don't need to calculate SP based on height at this stage because it's constant
                        indexOfNextRow = listContains(foodInfo, consec[0] + 1, consec[1] - 1 + parityAdjuster, 5);
                        if (indexOfNextRow != -1) {
                            //right-slanting! remove from list
                            leftSlant = false;
                            foodInfo.remove(indexOfNextRow);
                        } else {
                            //not right-slanting, so try left-slanting
                            indexOfNextRow = listContains(foodInfo, consec[0] + 1, consec[1] + parityAdjuster, 5);
                            if (indexOfNextRow != -1) {
                                leftSlant = true;
                                foodInfo.remove(indexOfNextRow);
                            } else {
                                return false; //invalid shape!
                            }
                        }

                        for (int h = 2; h < 5; h++) { //already found the first 2 rows
                            if (((consec[0] % 2) == 1) && ((h % 2) == 1)) {
                                parityAdjuster = 1;
                            } else {
                                parityAdjuster = 0;
                            }

                            if (leftSlant) {
                                SP = h / 2 + parityAdjuster;
                            } else {
                                SP = parityAdjuster - (h + 1) / 2;
                            }
                            indexOfNextRow = listContains(foodInfo, consec[0] + h, consec[1] + SP, 5);
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
            return true; //everything is okay!

        } else {
            return false;
        }

    }

    /**
     * Generates a random world, adhering to the contest world constraints
     */
    public void generateRandomContestWorld() {
        width = 150;
        height = 150;
        cells = new Cell[150][150];

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        //set all edges to rocky
        for (int i = 0; i < 150; i++) { //top and bottom
            cells[0][i].setRock(true);
            cells[149][i].setRock(true);
            cells[i][0].setRock(true); //square so we can shortcut it into only one loop!
            cells[i][149].setRock(true);
        }

        //place anthills
        Random r = new Random();
        int x, y;

        x = r.nextInt(134) + 5; //far left of hexagon is -3 from x and far right is +9 from x
        y = r.nextInt(134) + 2; //y can be between 2 and 135

        //just place first anthill, no need to check if there's stuff in the way
        placeAnthill("red", x, y);
        
        boolean isRoom = false;
        while (!isRoom) {
            //regen x and y for the second anthill
            x = r.nextInt(134) + 5;
            y = r.nextInt(134) + 2;
            //check there is space first
            int SP, len, parityAdjuster;

            for (int h = -1; h < 14; h++) { //-1 to 14 to allow for border
                if (((y % 2) == 1) && ((h % 2) == 1)) {
                    parityAdjuster = 1;
                } else {
                    parityAdjuster = 0;
                }
                //adjusted slightly to allow for a border of 1 around the hexagon
                SP = (Math.abs(6 - h)) / 2 - 4 + parityAdjuster;
                len = 15 - Math.abs(6 - h);

                isRoom = true;
                for (int i = 0; i < len; i++) {
                    if (!cells[y + h][x + SP + i].isEmpty()) {
                        isRoom = false;
                    }
                }
            }
            if (isRoom) {
                placeAnthill("black", x, y);
            }
        }

        //place food blobs
        //pick a random shape 1-3 then decide boundaries from there
        for (int foodNo = 0; foodNo < 11; foodNo++) {
            int shape = r.nextInt(3) + 1;

            if (shape == 1) { //diamond
                isRoom = false;
                while (!isRoom) {
                    x = r.nextInt(142) + 4; //x of diamond ranges from x-2 to x+2
                    y = r.nextInt(138) + 2; //height of diamond is 9

                    int SP, len, parityAdjuster;

                    for (int h = -1; h < 10; h++) { //remember border of 1
                        if (((y % 2) == 1) && ((h % 2) == 1)) {
                            parityAdjuster = 1;
                        } else {
                            parityAdjuster = 0;
                        }

                        //these also adjusted, like with anthills
                        SP = (Math.abs(4 - h)) / 2 - 3 + parityAdjuster;
                        len = 7 - Math.abs(4 - h);

                        isRoom = true;
                        for (int i = 0; i < len; i++) {
                            if (!cells[y + h][x + SP + i].isEmpty()) {
                                isRoom = false;
                            }
                        }
                    }
                    if (isRoom) {
                        placeFoodBlob(shape, x, y);
                    }

                }
            } else { //parallelograms
                isRoom = false;
                while (!isRoom) {
                    if (shape == 2) { //left slanting
                        x = r.nextInt(140) + 2; //x varies from x to x+6
                    } else {
                        x = r.nextInt(140) + 4; //x varies from x-2 to x+4
                    }
                    y = r.nextInt(142) + 2; //height of 5

                    int SP, parityAdjuster;

                    for (int h = -1; h < 6; h++) {
                        if (((y % 2) == 1) && ((h % 2) == 1)) {
                            parityAdjuster = 1;
                        } else {
                            parityAdjuster = 0;
                        }

                        if (shape == 2) { //left slanting
                            SP = h / 2 + parityAdjuster - 1;
                        } else {
                            SP = parityAdjuster - (h + 1) / 2 - 1;
                        }

                        isRoom = true;
                        for (int i = 0; i < 7; i++) { //length 7 because of borders
                            if (!cells[y + h][x + SP + i].isEmpty()) {
                                isRoom = false;
                            }
                        }
                    }
                    if (isRoom) {
                        placeFoodBlob(shape, x, y);
                    }
                }
            }
        }

        //place rocks
        for (int i = 0; i < 14; i++) {
            isRoom = false;
            while (!isRoom) {
                x = r.nextInt(146) + 2;
                y = r.nextInt(146) + 2;

                if (cells[x][y].isEmpty()) {
                    isRoom = true;
                    cells[x][y].setRock(true);
                }
            }
        }
        resetCells = cells.clone();
        for (int i=0; i<cells.length; i++) {
            resetCells[i] = cells[i].clone();
        }
    }

    /**
     *
     * @param cell
     * @param cond
     * @param a
     * @return
     */
    public boolean checkCellStatus(int[] cell, Sense.condition cond, Ant a) {
        // Check in boundaries
        if (cell[0] < height && cell[1] < width && cell[0] >= 0 && cell[1] >= 0) {
            Cell c = getCell(cell);
            //Friend case - Check ant exists in cell, if it is of the same colour return true
            if (cond == Sense.condition.FRIEND) {
                if (c.getAnt() != null) {
                    if (a.getColour()) {    //BLACK
                        if (c.getAnt().getColour()) { //BLACK
                            return true;
                        } else {
                            return false;
                        }
                    } else if (!a.getColour()) { //RED
                        if (!c.getAnt().getColour()) { //RED
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            //Foe case - Check if ant exists in cell, if it is of opposite colour return true
            else if (cond == Sense.condition.FOE) {
                if (c.getAnt() != null) {
                    if (a.getColour()) { //BLACK
                        if (!c.getAnt().getColour()) { //RED
                            return true;
                        } else {
                            return false;
                        }
                    } else if (!a.getColour()) { //RED
                        if (c.getAnt().getColour()) { //BLACK
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            //FriendWithFood case - check if Ant is friend, then return true if they have food
            else if (cond == Sense.condition.FRIENDWITHFOOD) {
                if (c.getAnt() != null) {
                    if (a.getColour()) { //BLACK
                        if (c.getAnt().getColour()) { //ALSO BLACK
                            return c.getAnt().getFood();
                        } else {
                            return false;
                        }
                    } else if (!a.getColour()) { //RED
                        if (!c.getAnt().getColour()) { //ALSO RED
                            return c.getAnt().getFood();
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            //FoeWithFood case - like FriendWithFood, but reversed
            else if (cond == Sense.condition.FOEWITHFOOD) {
                if (c.getAnt() != null) {
                    if (a.getColour()) {        //BLACK
                        if (!c.getAnt().getColour()) { //RED
                            return c.getAnt().getFood();
                        } else {
                            return false;
                        }
                    } else if (!a.getColour()) {    //RED
                        if (c.getAnt().getColour()) {   //BLACK
                            return c.getAnt().getFood();
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            //Food case - true if the cell contains food, false otherwise
            else if (cond == Sense.condition.FOOD) {
                if (c.getFood() > 0) {
                    return true;
                } else {
                    return false;
                }
            }
            //Rock case - true if rock, false otherwise
            else if (cond == Sense.condition.ROCK) {
                return c.getRock();
            }
            //Marker0 case - return true if 0 marker is present
            else if (cond == Sense.condition.MARKER0) {
                //BLACK - First check the ant is able to detect a friendly marker
                if (a.getColour()) {        //BLACK
                    boolean[] temp = c.getMarkers(true); //Get Black markers
                    return temp[0];
                } //RED
                else if (!a.getColour()) {  //RED
                    boolean[] temp = c.getMarkers(false); //Get Red markers
                    return temp[0];
                }
            }
            else if (cond == Sense.condition.MARKER1) {
                if (a.getColour()) {    //BLACK
                    boolean[] temp = c.getMarkers(true);
                    return temp[1];
                } 
                else if (!a.getColour()) { //RED
                    boolean[] temp = c.getMarkers(false);
                    return temp[1];
                }
            }
            else if (cond == Sense.condition.MARKER2) {
                //BLACK
                if (a.getColour()) {
                    boolean[] temp = c.getMarkers(true);
                    return temp[2];
                } //RED
                else if (!a.getColour()) {
                    boolean[] temp = c.getMarkers(false);
                    return temp[2];
                }
            }
            else if (cond == Sense.condition.MARKER3) {
                //BLACK
                if (a.getColour()) {
                    boolean[] temp = c.getMarkers(true);
                    return temp[3];
                } //RED
                else if (!a.getColour()) {
                    boolean[] temp = c.getMarkers(false);
                    return temp[3];
                }
            }
            else if (cond == Sense.condition.MARKER4) {
                //BLACK
                if (a.getColour()) {
                    boolean[] temp = c.getMarkers(true);
                    return temp[4];
                } //RED
                else if (!a.getColour()) {
                    boolean[] temp = c.getMarkers(false);
                    return temp[4];
                }
            }
            else if (cond == Sense.condition.MARKER5) {
                //BLACK
                if (a.getColour()) {
                    boolean[] temp = c.getMarkers(true);
                    return temp[5];
                } //RED
                else if (!a.getColour()) {
                    boolean[] temp = c.getMarkers(false);
                    return temp[5];
                }
            }
            else if (cond == Sense.condition.FOEMARKER) {
                boolean[] temp;
                int hasMarkers = 0;
                //BLACK
                if (a.getColour()) {
                    temp = c.getMarkers(false);
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i]) {
                            hasMarkers++;
                        }
                    }
                } //RED 
                else if (!a.getColour()) {
                    temp = c.getMarkers(true);
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i]) {
                            hasMarkers++;
                        }
                    }
                }
                if (hasMarkers > 0) {
                    return true;
                } else {
                    return false;
                }
            }
            else if (cond == Sense.condition.HOME) {
                if (a.getColour()) { //BLACK
                    if (c.getAnthill().equalsIgnoreCase("black")) {
                        return true;
                    } else {
                        return false;
                    }
                } else { //RED
                    if (c.getAnthill().equalsIgnoreCase("red")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            else if (cond == Sense.condition.FOEHOME) {
                if (a.getColour()) { //BLACK
                    if (c.getAnthill().equalsIgnoreCase("red")) {
                        return true;
                    } else {
                        return false;
                    }
                } else { //RED
                    if (c.getAnthill().equalsIgnoreCase("black")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            System.err.println("Invalid Input CHECK ME");
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
        if ((cell[0] < height && cell[1] < width) && (cell[0] >= 0 && cell[1] >= 0)) {
            return cells[cell[0]][cell[1]];
        } else {
            return null;
        }
    }

    /**
     * Prints the world to the console as it would appear in a world file
     */
    public void printWorld() {
        System.out.println(height);
        System.out.println(width);

        for (int i = 0; i < height; i++) {
            if (i % 2 == 1) { //odd row
                System.out.print(" ");
            }
            for (int j = 0; j < width; j++) {
                System.out.print(cells[i][j].getCellSymbol() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Used when generating random worlds
     *
     * @param colour
     * @param x
     * @param y
     */
    private void placeAnthill(String colour, int x, int y) {
        int SP, len, parityAdjuster;

        for (int h = 0; h < 13; h++) {
            if (((y % 2) == 1) && ((h % 2) == 1)) {
                parityAdjuster = 1;
            } else {
                parityAdjuster = 0;
            }

            SP = (Math.abs(6 - h)) / 2 - 3 + parityAdjuster;
            len = 13 - Math.abs(6 - h);

            for (int i = 0; i < len; i++) {
                cells[y + h][x + SP + i].anthill = colour;
                int[] xy = new int[2];
                xy[0] = y + h;
                xy[1] = x + SP + i;
            }
        }
    }

    private void placeFoodBlob(int type, int x, int y) {
        int SP, len, parityAdjuster;

        if (type == 1) { //diamond shape
            for (int h = 0; h < 9; h++) {
                if (((y % 2) == 1) && ((h % 2) == 1)) {
                    parityAdjuster = 1;
                } else {
                    parityAdjuster = 0;
                }

                SP = (Math.abs(4 - h)) / 2 - 2 + parityAdjuster;
                len = 5 - Math.abs(4 - h);

                for (int i = 0; i < len; i++) {
                    cells[y + h][x + SP + i].setFood(5);
                    int[] xy = new int[2];
                    xy[0] = y + h;
                    xy[1] = x + SP + i;
                }
            }
        } else { //parallelograms
            for (int h = 0; h < 5; h++) {
                if (((y % 2) == 1) && ((h % 2) == 1)) {
                    parityAdjuster = 1;
                } else {
                    parityAdjuster = 0;
                }

                if (type == 2) { //left slanting
                    SP = h / 2 + parityAdjuster;
                } else {
                    SP = parityAdjuster - (h + 1) / 2;
                }

                for (int i = 0; i < 5; i++) {
                    cells[y + h][x + SP + i].setFood(5);
                    int[] xy = new int[2];
                    xy[0] = y + h;
                    xy[1] = x + SP + i;
                }
            }
        }
    }

    public List<Ant> placeAnts(AntBrain blackBrain, AntBrain redBrain) {
        List<Ant> list = new ArrayList<>();
        int id = 0;
        for (int[] cell : anthillCells) {
            if (getCell(cell).anthill.equalsIgnoreCase("black")) {
                Ant black = new Ant(blackBrain, true, id++);
                black.setPostition(cell);
                getCell(cell).ant = black;
                list.add(black);
            } else if(getCell(cell).anthill.equalsIgnoreCase("red")){
                Ant red = new Ant(redBrain, false, id++);
                red.setPostition(cell);
                getCell(cell).ant = red;
                list.add(red);
            }
        }
        return list;
    }
    
    public void replaceFood() {
        for(int[] i : foodSpawnCells) {
            getCell(i).setFood(5);
        }
    }
    
    public List<int[]> getAnthillCells() {
        return this.anthillCells;
    }
    
    public List<int[]> getFoodCells() {
        return this.foodSpawnCells;
    }
    
    public int getFoodNum() {
        return this.foodSpawnCells.size() * 5;
    }
    
    public void resetWorld() {
        cells = resetCells.clone();
        for (int i=0; i<resetCells.length; i++) {
            cells[i] = resetCells[i].clone();
        }
        for(Cell[] c : cells) {
            for(Cell cell : c) {
                cell.setFood(0);
                cell.adjacentAntsBlack = 0;
                cell.adjacentAntsRed = 0;
                    
                if(cell.ant != null) {
                    cell.removeAnt();
                }
                for(int i=0; i<6; i++) {
                    cell.removeBlackMarker(i);
                    cell.removeRedMarker(i);
                }
                
            }
        }
    }
}
