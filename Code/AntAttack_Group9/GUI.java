/**
 * GUI.java Creates a visual representation of a Game in progress.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

public final class GUI {

    protected HexGrid hexgrid;

    /**
     * Initialises the GUI elements of the game provided with a World
     * @param w a World instance to load into the GUI
     */
    public GUI(World w) {
        hexgrid = new HexGrid(this);
        hexgrid.createAndShowGUI();
    }

    /**
     * Initialises a World map in the HexGrid with the current World, match number and
     * the number of total matches to be played.
     * @param w the World to display on the map
     * @param matchNum the match number
     * @param totalMatches the number of total matches to be played
     */
    public void initaliseWorldMap(World w, int matchNum, int totalMatches) {
        hexgrid.initGame(w, matchNum, totalMatches);
    }

    /**
     * Updates the UI with a new World and Gameplay instance
     * @param w the new World object
     * @param g the new Gameplay object
     */
    public void updateUI(World w, Gameplay g) {
        hexgrid.updateGrid(w, g);
    }
}
