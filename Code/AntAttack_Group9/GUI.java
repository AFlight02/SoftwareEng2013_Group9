/**
 * GUI.java Creates a visual representation of a Game in progress.
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
 * @version 0.1 TODO
 */
package AntAttack_Group9;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {

    private World world;
    private int gameState;
    private Image blank;
    private Image redAnt;
    private Image blackAnt;
    private Image food1;
    private Image food2;
    private Image food3;
    private Image food4;
    private Image food5;
    private Image redAnthill;
    private Image blackAnthill;
    private Image rock;
    // Need window elems plus way of representing each component in the game graphically in a window like an
    // overhead map of some kind. See Google AI challenge as referenced in project for inspiration.
    /**
     * 
     */
    public GUI (World world) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200,200);
        this.setLayout(new FlowLayout());
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("blank.png"));
            blank = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("redAnt.png"));
            redAnt = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("blackAnt.png"));
            blackAnt = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("food1.png"));
            food1 = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("food2.png"));
            food2 = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("food3.png"));
            food3 = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("food4.png"));
            food4 = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("food5.png"));
            food5 = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("anthillRed.png"));
            redAnthill = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("anthillBlack.png"));
            blackAnthill = img;
        } catch (IOException e) {
            
        }
        try {
            img = ImageIO.read(new File("rock.png"));
            rock = img;
        } catch (IOException e) {
            
        }
        this.setVisible(true);
    }
    
    public void update(World w) {
        world = w;
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        
        this.getContentPane().add(panel);
        this.pack();
        for(int i=0; i<world.height; i++) {
            for(int j=0; j<world.width; j++) {
                ImageIcon img = new ImageIcon();
                if(world.cells[i][j].rock) {
                    img.setImage(rock);
                } else if(world.cells[i][j].ant != null) {
                    if(world.cells[i][j].ant.getColour()) {
                        img.setImage(blackAnt);
                    } else {
                        img.setImage(redAnt);
                    }
                } else if(world.cells[i][j].anthill != null) {
                    if(world.cells[i][j].anthill.equalsIgnoreCase("black")){
                        img.setImage(blackAnthill);
                    } else {
                        img.setImage(redAnthill);
                    }
                } else if(world.cells[i][j].food != 0) {
                    if(world.cells[i][j].food == 1) {
                        img.setImage(food1);
                    }
                    if(world.cells[i][j].food == 2) {
                        img.setImage(food2);
                    }
                    if(world.cells[i][j].food == 3) {
                        img.setImage(food3);
                    }
                    if(world.cells[i][j].food == 4) {
                        img.setImage(food4);
                    }
                    if(world.cells[i][j].food == 5) {
                        img.setImage(food5);
                    }
                } else {
                    img.setImage(blank);
                }
                JLabel label = new JLabel();
                label.setIcon(img);
                panel.add(label);
            }
        }
        

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
    public void updateUI() {
        // On game step(), update graphical display of the game world
    }
}
