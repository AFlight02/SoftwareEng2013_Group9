/**
 * HexGrid.java Creates the graphical representation of the Game World.
 * 
* Adapted from the code available at
 * http://www.quarkphysics.ca/scripsi/hexgrid/ by Michael Harwood Adapted by
 * Alex Flight
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HexGrid {

    final static Color COLOURBLACK = Color.BLACK;
    final static Color COLOURRED = Color.RED;
    final static Color COLOURGREY = Color.DARK_GRAY;
    final static Color COLOURCELL = Color.ORANGE;
    final static Color COLOURGRID = Color.BLACK;
    final static Color COLOURBLUE = Color.BLUE;
    final static int EMPTY = 0;
    final static int BSIZE = 150; //board size.
    final static int HEXSIZE = 6;    //hex size in pixels
    final static int BORDERS = 15;
    final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS * 3 + 200; //screen size (vertical dimension).
    public static boolean XYVertex = true;   //true: x,y are the co-ords of the first vertex.
//false: x,y are the co-ords of the top left rect. co-ord.
    private static int s = 0;	// length of one side
    private static int t = 0;	// short side of 30o triangle outside of each hex
    private static int r = 0;	// radius of inscribed circle (centre to middle of each side). r= h/2
    private static int h = 0;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
    private int match;
    private int totalMatches;
    private DrawingPanel panel;
    private JPanel controlPanel;
    private JFrame frame;
    private JFrame controlFrame;
    private Container content;
    private Container controlContent;
    // Variables declaration - do not modify
    private javax.swing.JButton addPlayerButton;
    private javax.swing.JButton addWorldButton;
    private javax.swing.JButton beginTournamentButton;
    private javax.swing.JLabel blackAntLabel;
    private javax.swing.JLabel blackBrainLabel;
    private javax.swing.JLabel blackFoodLabel;
    private javax.swing.JLabel matchLabel;
    private javax.swing.JButton newTournButton;
    private javax.swing.JLabel redAntLabel;
    private javax.swing.JLabel redBrainLabel;
    private javax.swing.JLabel redFoodLabel;
    private javax.swing.JLabel worldLabel;
    private javax.swing.JLabel numBrainsLabel;
    private javax.swing.JLabel winner;
    private javax.swing.JFileChooser chooseWorld;
    private javax.swing.JFileChooser chooseBrain;
    // End of variables declaration
    private GUI gui;
    private World world;
    private Tournament tourn;
    private ArrayList<World> worlds;
    private ArrayList<AntBrain> brains;

    /**
     * Initialises a new HexGrid
     * @param g the GUI managing this HexGrid
     */
    public HexGrid(GUI g) {
        gui = g;
        worlds = new ArrayList();
        brains = new ArrayList();
    }

    /**
     * Initialise an individual game in the HexGrid.
     * @param w the World being played on
     * @param matchNum the match number
     * @param total the total number of matches to be played
     */
    public void initGame(World w, int matchNum, int total) {
        world = w;
        match = matchNum;
        totalMatches = total;
        setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
        setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
    }

    /**
     * Sets up the HexGrid GUI and initialises all its components.
     */
    public void createAndShowGUI() {
        panel = new DrawingPanel();
        controlPanel = new JPanel();
        frame = new JFrame("Ant Attack - World");
        controlFrame = new JFrame("Ant Attack - Controls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = frame.getContentPane();
        content.add(panel);

        initComponents();

        controlFrame.setSize(250, 400);
        controlFrame.setResizable(false);
        controlFrame.setLocation(980, 10);
        controlFrame.setVisible(true);

        frame.setSize((int) (SCRSIZE / 1.22), (int) (SCRSIZE / 1.2));
        frame.setResizable(false);
        frame.setLocation(10, 10);
        frame.setVisible(true);
    }

    /**
     * Updates the HexGrid with an up-to-date representation of the World and Gameplay information.
     * @param w the World to render
     * @param g the Gameplay currently in progress
     */
    public void updateGrid(World w, Gameplay g) {
        world = w;
        panel.updatePanel();
        //content = frame.getContentPane();
        //content.add(panel);
        matchLabel.setText("Match " + match + " of " + totalMatches);
        worldLabel.setText("World " + world.worldName);
        redBrainLabel.setText("Red Brain: " + g.redAntBrain.getName());
        blackBrainLabel.setText("Black Brain: " + g.blackAntBrain.getName());
        blackAntLabel.setText("Black Ants: " + g.numBlackAnts);
        redAntLabel.setText("Red Ants: " + g.numRedAnts);
        redFoodLabel.setText("Red Food: " + g.redFood);
        blackFoodLabel.setText("Black Food: " + g.blackFood);
        numBrainsLabel.setText("Number of Brains(2 Minimum): " + brains.size());
    }

    /**
     * Controls the drawing of the hexagonal grid representing the World that extends
     * the JPanel class.
     */
    class DrawingPanel extends JPanel {

        /**
         * Creates new DrawingPanel instance
         */
        public DrawingPanel() {
            setBackground(Color.WHITE);
        }

        /**
         * Calls the repaint() function on this JPanel to update the HexGrid
         */
        public void updatePanel() {
            repaint();
        }

        /**
         * Paints the Cells of the World as polygons on a 2D graphics object.
         * @param g graphics object
         */
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            super.paintComponent(g2);

            for (int i = 0; i < world.height; i++) {
                for (int j = 0; j < world.width; j++) {
                    drawHex(i, j, g2);
                    Cell c = world.cells[i][j];
                    if (c.ant != null && c.ant.isAlive()) {
                        if (c.ant.getColour()) {      //BLACK
                            fillHex(i, j, 1, g2);
                        } else {                     //RED
                            fillHex(i, j, 2, g2);
                        }
                    } else if (!c.anthill.equalsIgnoreCase("none")) {
                        if (c.anthill.equalsIgnoreCase("black")) {
                            fillHex(i, j, 4, g2);
                        } else if (c.anthill.equalsIgnoreCase("red")) {
                            fillHex(i, j, 5, g2);
                        }
                    } else if (c.food > 0) {
                        fillHex(i, j, 3, g2);
                    } else if (c.rock) {
                        fillHex(i, j, 6, g2);
                    } else {
                        fillHex(i, j, 0, g2);
                    }
                }
            }
        }
    } // end of DrawingPanel class

    /**
     * Flags the start of the polygons to be drawn from the top left if true, offsets if false
     * @param b whether or not to draw hexagons from the top left
     */
    public static void setXYasVertex(boolean b) {
        XYVertex = b;
    }

    /**
     * This functions takes the Side length in pixels and uses that as the basic
     * dimension of the hex. It calculates all other needed constants from this
     * dimension.
     * @param side the side length in pixels
     */
    public static void setSide(int side) {
        s = side;
        t = (int) (s / 2);	//t = s sin(30) = (int) CalculateH(s);
        r = (int) (s * 0.8660254037844);	//r = s cos(30) = (int) CalculateR(s);
        h = 2 * r;
    }

    /**
     * Takes the Height of a hexagon and uses that to calculate other hexagon dimensions.
     * @param height the Height of a hexagon
     */
    public static void setHeight(int height) {
        h = height;	// h = basic dimension: height (distance between two adj centresr aka size)
        r = h / 2;	// r = radius of inscribed circle
        s = (int) (h / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        t = (int) (r / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
    }

    /**
     * *******************************************************
     * Name: hex() Parameters: (x0,y0) This point is normally the top left
     * corner of the rectangle enclosing the hexagon. However, if XYVertex is
     * true then (x0,y0) is the vertex of the top left corner of the hexagon.
     * Returns: a polygon containing the six points. Called from: drawHex(),
     * fillhex() Purpose: This function takes two points that describe a hexagon
     * and calculates all six of the points in the hexagon.
     * *******************************************************
     */
    public static Polygon hex(int x0, int y0) {

        int y = y0 + BORDERS;
        int x = x0 + BORDERS; // + (XYVertex ? t : 0); //Fix added for XYVertex = true.
        // NO! Done below in cx= section
        if (s == 0 || h == 0) {
            System.out.println("ERROR: size of hex has not been set");
            return new Polygon();
        }

        int[] cx, cy;

        //I think that this XYvertex stuff is taken care of in the int x line above. Why is it here twice?
        if (XYVertex) {
            cx = new int[]{x, x + s, x + s + t, x + s, x, x - t}; //this is for the top left vertex being at x,y. Which means that some of the hex is cutoff.
        } else {
            //cx = new int[]{x + t, x + s + t, x + s + t + t, x + s + t, x + t, x};	//this is for the whole hexagon to be below and to the right of this point
            cx = new int[]{x + r, x + h, x + h, x + r, x, x};	//this is for the whole hexagon to be below and to the right of this point
        }
        //cy = new int[]{y, y, y + r, y + r + r, y + r + r, y + r};
        cy = new int[]{y, y + t, y + t + s, y + 2 * t + s, y + t + s, y + t};

        Polygon rotated = new Polygon(cx, cy, 6);
        return rotated;
    }

    /**
     * ******************************************************************
     * Name: drawHex() Parameters: (i,j) : the x,y coordinates of the initial
     * point of the hexagon g2: the Graphics2D object to draw on. Returns: void
     * Calls: hex() Purpose: This function draws a hexagon based on the initial
     * point (x,y). The hexagon is drawn in the colour specified in
     * hexgame.COLOURELL.
     * *******************************************************************
     */
    public static void drawHex(int i, int j, Graphics2D g2) {
        //int x = i * (s + t);
        //int y = j * h + (i % 2) * h / 2;
        int x;
        if (i % 2 != 0) {
            x = j * h + r;
        } else {
            x = j * h;
        }
        int y = i * (h - (t / 2));
        Polygon poly = hex(x, y);
        g2.setColor(COLOURGRID);
        g2.drawPolygon(poly);
    }

    /**
     * *************************************************************************
     * Name: fillHex() Parameters: (i,j) : the x,y coordinates of the initial
     * point of the hexagon n : an integer number to indicate a letter to draw
     * in the hex g2 : the graphics context to draw on Return: void Called from:
     * Calls: hex() Purpose: This draws a filled in polygon based on the
     * coordinates of the hexagon. The colour depends on whether n is negative
     * or positive. The colour is set by hexgame.COLOURONE and
     * hexgame.COLOURTWO. The value of n is converted to letter and drawn in the
     * hexagon.
     * ***************************************************************************
     */
    public static void fillHex(int i, int j, int n, Graphics2D g2) {
        int x;
        if (i % 2 != 0) {
            x = j * h + r;
        } else {
            x = j * h;
        }
        int y = i * (h - (t / 2));

        switch (n) {
            // BLANK
            case 0:
                g2.setColor(COLOURCELL);
                g2.fillPolygon(hex(x, y));
                break;
            // BLACK ANT
            case 1:
                g2.setColor(COLOURBLACK);
                g2.fillPolygon(hex(x, y));
                break;
            // RED ANT
            case 2:
                g2.setColor(COLOURRED);
                g2.fillPolygon(hex(x, y));
                break;
            // FOOD
            case 3:
                g2.setColor(COLOURBLUE);
                g2.fillPolygon(hex(x, y));
                break;
            // BLACK ANTHILL
            case 4:
                g2.setColor(COLOURBLACK);
                g2.drawString("H", x + r + BORDERS - 3, y + r + BORDERS + 4);
                break;
            // RED ANTHILL
            case 5:
                g2.setColor(COLOURRED);
                g2.drawString("H", x + r + BORDERS - 3, y + r + BORDERS + 4);
                break;
            // ROCK
            case 6:
                g2.setColor(COLOURGREY);
                g2.fillPolygon(hex(x, y));
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        matchLabel = new javax.swing.JLabel();
        newTournButton = new javax.swing.JButton();
        addPlayerButton = new javax.swing.JButton();
        addWorldButton = new javax.swing.JButton();
        beginTournamentButton = new javax.swing.JButton();
        worldLabel = new javax.swing.JLabel();
        redBrainLabel = new javax.swing.JLabel();
        blackBrainLabel = new javax.swing.JLabel();
        blackAntLabel = new javax.swing.JLabel();
        redAntLabel = new javax.swing.JLabel();
        redFoodLabel = new javax.swing.JLabel();
        blackFoodLabel = new javax.swing.JLabel();
        chooseWorld = new javax.swing.JFileChooser();
        chooseBrain = new javax.swing.JFileChooser();
        numBrainsLabel = new javax.swing.JLabel();
        winner = new javax.swing.JLabel();


        newTournButton.setText("New Tournament");
        newTournButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTournButtonActionPerformed(evt);
            }
        });

        addPlayerButton.setText("Add Competitor");
        addPlayerButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerButtonActionPerformed(evt);
            }
        });

        addWorldButton.setText("Add World");
        addWorldButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWorldButtonActionPerformed(evt);
            }
        });

        beginTournamentButton.setText("Begin Tournament");
        beginTournamentButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginTournamentButtonActionPerformed(evt);
            }
        });

        chooseWorld.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    chooseWorldActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(HexGrid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        chooseBrain.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    chooseBrainActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(HexGrid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        matchLabel.setText("Match " + match + " of " + totalMatches);
        worldLabel.setText("World ");
        redBrainLabel.setText("Red Brain: ");
        blackBrainLabel.setText("Black Brain: ");
        blackAntLabel.setText("Black Ants: ");
        redAntLabel.setText("Red Ants: ");
        redFoodLabel.setText("Red Food: ");
        blackFoodLabel.setText("Black Food: ");
        numBrainsLabel.setText("Number of Brains(2 Minimum): ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(controlFrame.getContentPane());
        controlFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(21, 21, 21).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(addWorldButton).addComponent(redFoodLabel).addComponent(redAntLabel).addComponent(blackAntLabel).addComponent(redBrainLabel).addComponent(worldLabel).addComponent(matchLabel).addComponent(beginTournamentButton).addComponent(numBrainsLabel).addComponent(blackBrainLabel).addComponent(blackFoodLabel).addComponent(newTournButton).addComponent(addPlayerButton).addComponent(winner)).addGap(18, 18, 18).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(newTournButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(addPlayerButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(addWorldButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(beginTournamentButton).addGap(18, 18, 18).addComponent(matchLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(worldLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(numBrainsLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(blackBrainLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(redBrainLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(blackAntLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(redAntLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(blackFoodLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(redFoodLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(winner))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        controlFrame.pack();
    }// </editor-fold>

    /**
     * Add Player button toggles an Open File Dialogue that asks for an AntBrain file.
     * @param evt event triggering this action, pressing 'Add Player'
     */
    private void addPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        chooseBrain.showOpenDialog(controlFrame);
    }

    /**
     * New Tournament button toggles a new Tournament to be set up by reinitialising Brain and
     * World lists.
     * @param evt button press to trigger this action
     */
    private void newTournButtonActionPerformed(java.awt.event.ActionEvent evt) {
        worlds = new ArrayList<>();
        brains = new ArrayList<>();
    }

    /**
     * Add World button toggles an Open File Dialogue to ask for a World file.
     * @param evt pressing the 'Add World' button triggers this
     */
    private void addWorldButtonActionPerformed(java.awt.event.ActionEvent evt) {
        chooseWorld.showOpenDialog(controlFrame);
    }

    /**
     * When a World is chosen by the File Dialogue read in that World file and add it to the list
     * of Worlds in the Tournament.
     * @param evt choosing a World file from the file chooser
     * @throws Exception 
     */
    private void chooseWorldActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        World w = new World();
        w.readInWorld(chooseWorld.getSelectedFile().getName());
        worlds.add(w);
    }

    /**
     * When an AntBrain is chosen by the File Dialogue, read in that file and add it to the list of
     * AntBrains in the Tournament.
     * @param evt choosing a World from the file chooser
     * @throws Exception 
     */
    private void chooseBrainActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        AntBrain b = new AntBrain(chooseBrain.getSelectedFile().getName());
        brains.add(b);
        numBrainsLabel.setText("Number of Brains(2 Minimum): " + brains.size());
    }

    /**
     * Pressing the 'Begin Tournament' button causes a new Tournament to be initiated with the 
     * chosen AntBrains and Worlds and the begins the Tournament in a new Thread.
     * @param evt button press of 'Begin Tournament'
     */
    private void beginTournamentButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tourn = new Tournament(brains, worlds);
        tourn.startNewTourn(gui);
    }
}
