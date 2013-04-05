package AntAttack_Group9;
import java.util.*;

import AntAttack_Group9.Sense.senseDir;

public class Gameplay {

World world;
int round;
int redFood;
int blackFood;
List<Ant> ants;
AntBrain redAntBrain;
AntBrain blackAntBrain;

public Gameplay(AntBrain red, AntBrain black) {
                // For Kirstie : Pass constructor the two AntBrains for play, pass them to loadAntBrains method OR remove that method and just load straight into params
// Constructor
	world = new World();
	this.round = 0;
	this.redFood = 0;
	this.blackFood = 0;
	this.redAntBrain = red;
	this.blackAntBrain = black;
	ants = new ArrayList<Ant>();
	loadAntBrains(redAntBrain, blackAntBrain);
	
	
}

public void generateWorld() {
// Generate a new World randomly
	world.generateRandomWorld();
}
        
public void playGame() {
        //new AntAttack();
    for(int i=0; i<300000; i++) {
        stepGame();
    }

}

public void loadWorld(World uploadWorld) {
	world = uploadWorld;
	
}


public void stepGame() {


	for(int i=0; i<world.cells.length; i++){
		for(int j=0; j<world.cells[i].length; j++) {
			if(world.cells[i][j].ant != null){
				if(world.cells[i][j].ant.getColour()){
					if(world.cells[i][j].getAdjacentAntsRed() >= 5){
						world.cells[i][j].ant.kill();
						world.cells[i][j].removeAnt();	
					}
				} else {
					if(world.cells[i][j].getAdjacentAntsBlack() >= 5){
						world.cells[i][j].ant.kill();
						world.cells[i][j].removeAnt();	
					}
				}
			}
		}
	}

	for(Ant a: ants ){
		if(a.isAlive()){
			if(a.getResting() == 0){
				Instruction  nextInstruction = a.getInstruction();
				
				if(nextInstruction instanceof Flip) {
					Flip f = (Flip) nextInstruction;
					Random rand = new Random();//CHANGE LATER FOR FLIP
					int n = rand.nextInt(f.getRandom());
					if(n == 0) {
						a.setState(f.getS1());
					} else {
						a.setState(f.getS2());
					}
				}
				
				if(nextInstruction instanceof Mark){
					Mark m = (Mark) nextInstruction;
					if(a.getColour()){ //BLACK
						world.findAnt(a.getID()).addBlackMarker(m.getMarker());						
					}
					else {
						world.findAnt(a.getID()).addRedMarker(m.getMarker());
					}
					a.setState(m.getNextState());		
				}
				
				if(nextInstruction instanceof Sense){
					Sense s = (Sense) nextInstruction;
					if(world.checkCellStatus(world.findAnt(a.getID()).adjacentCell(a.getDirection()), s.getCondVal(), a)) {
						a.setState(s.getS1());
					} else {
						a.setState(s.getS2());
					}
				}
				
				if(nextInstruction instanceof Unmark){
					Unmark u = (Unmark) nextInstruction;
					if(a.getColour()){ //BLACK
						world.findAnt(a.getID()).removeBlackMarker(u.getMarker());						
					}
					else {
						world.findAnt(a.getID()).removeRedMarker(u.getMarker());
					}
					a.setState(u.getState());		
				}
				
				if(nextInstruction instanceof PickUp){
					PickUp p = (PickUp) nextInstruction;
					if(world.findAnt(a.getID()).getFood() > 0){
						a.setFood(true);
						world.findAnt(a.getID()).removeFood();
						a.setState(p.getInitialState());
						
					}
					else{
						a.setState(p.getNextState());
					}
				}
				
				
				if(nextInstruction instanceof Turn){
					Turn t = (Turn) nextInstruction;
					a.setDirection(a.turn(t.getTurnDir()));
					a.setState(t.getNextState());
					
					
				}
				
				if(nextInstruction instanceof Drop){
					Drop d = (Drop) nextInstruction;
					if(a.getFood()){
                                            world.findAnt(a.getID()).setFood(world.findAnt(a.getID()).getFood()+ 1);
					}
					a.setState(d.getNextState());
					
				}
				
				if(nextInstruction instanceof Move){
					Move m = (Move) nextInstruction;
					int[] loc = world.findAnt(a.getID()).adjacentCell(a.getDirection());
					if(a.getResting() <= 0){
						if(!world.getCell(loc).getRock() && world.getCell(loc).getAnt() == null){
							world.getCell(loc).setAnt(a);
							world.findAnt(a.getID()).removeAnt();
							a.setResting(15);
							a.setState(m.getInitDir());
							
						}
						
					}
					else{
						a.setState(m.getNextState());
					}
					
					
				}
				
			

			}
			a.decrementResting();
		}
	}

}

public void endGame() {
// On completion of all steps finish the game, total scores etc.
}

public void setupGame() {
	world.checkValidWorld();

// Calls World syntax checks, AntBrain syntax checks, begins stepping through game instance
}

public void loadAntBrains(AntBrain red, AntBrain black) { // Kirstie: Possibly remove this method with reference to comment on constructor!
// Loads red and black AntBrains
	redAntBrain = red;
	blackAntBrain = black;
	
}

public int declareWinner() {
	int winner;
	if(redFood > blackFood){
		winner = 2;
	}
	else if(blackFood > redFood){
		winner = 1;
	}
	else if(blackFood == redFood){
		winner = 0;
	}
	else{
		winner = -1;
	}
// Calculates winners and returns 0 for Draw, 1 for Black win, 2 for Red win, -1 for error
            return winner;
}

}
