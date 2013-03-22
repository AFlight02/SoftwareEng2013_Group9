/**
 * Ant.java
 * Represents an instance of an individual Ant agent within the AntAttack simulation. Each Ant shares a common AntBrain
 * that manages the behaviour of all Ants of a particular team throughout a Tournament and individual Game
 * 
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale, Paige Gray, Matt Chapman, Alex Flight (77525), ??James Bellamy??
 * @version 0.1
 */
package AntAttack_Group9; // Need to combine everything into said package for portability and integration of classes together
import java.util.*;

public class Ant {
	
	private AntBrain brain;
	private boolean colour;
	private boolean hasFood; // Missed an attribute in class dia...woops!
        private int resting;
	private int state;
	private int direction;
	
	public Ant(AntBrain brain, boolean colour) {
            this.brain = brain;
            this.colour = colour;
	}

	public int getState() {
            return this.state;
	}
        
        public int getResting() {
            return this.resting;
        }
        
        public int getDirection() {
            return this.direction;
	}
	
        public boolean getFood() {
            return this.hasFood;
        }
        
        public boolean getColour() {
            return this.colour;
        }
        
        public void setState(int newState) {
            this.state = newState;
        }
        
        public void setResting(int resting) {
            this.resting = resting;
        }
        
        public void setDirection(int dir) {
            this.direction = dir;
        }
	
        public void setFood(boolean b) {
            this.hasFood = b;
        }
        
}
