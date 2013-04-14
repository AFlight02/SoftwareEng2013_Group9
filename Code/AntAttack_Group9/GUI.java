/**
 * GUI.java Creates a visual representation of a Game in progress.
 * 
* @author Software Engineering 2012-13 Group 9 Simon Bell, Kirstie Hale, Paige
 * Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 0.1
 */
package AntAttack_Group9;

public final class GUI {

    private HexGrid hexgrid;

    /**
     *
     */
    public GUI(World w) {
        hexgrid = new HexGrid(this);
        hexgrid.createAndShowGUI();
    }

    public void initaliseWorldMap(World w, int matchNum, int totalMatches) {
        hexgrid.initGame(w, matchNum, totalMatches);
    }

    /**
     *
     */
    public void updateUI(World w, Gameplay g) {
        hexgrid.updateGrid(w, g);
    }
}
