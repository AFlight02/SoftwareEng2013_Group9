/**
 * GUI.java Creates a visual representation of a Game in progress.
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 0.1
 */
package AntAttack_Group9;

public class GUI {

    private World world;
    private HexGrid hexgrid;
    // Need window elems plus way of representing each component in the game graphically in a window like an
    // overhead map of some kind. See Google AI challenge as referenced in project for inspiration.

    /**
     *
     */
    public GUI(World w) {
        world = w;
        hexgrid = new HexGrid();
    }

    public void initHex() {
        hexgrid.initGame(world);
        hexgrid.createAndShowGUI();
    }

    /**
     *
     */
    public void beginNewTournament() {
        // Call to AntAttack newTournament on button press
    }

    /**
     *
     */
    public void uploadAntBrain() {
        // Again button press calls to upload an AntBrain
    }

    /**
     *
     */
    public void uploadWorld() {
        // Button press calls AntAttack to add world
    }

    /**
     *
     */
    public void showWinner() {
        // Call to show winner after game has finished
    }

    /**
     *
     */
    public void beginNewGame() {
        // Begin a new game in AntAttack
    }

    /**
     *
     */
    public void initialiseUIElements() {
        // Display all buttons, display, windows etc.
    }

    /**
     *
     */
    public void updateUI(World w) {
        hexgrid.updateGrid(w);
    }
}
