package AntAttack_Group9;
import java.util.*;

public class Gameplay {

	World world;
	int round;
	int redFood;
	int blackFood;
	Ant[] ants;
	AntBrain redAndBrain;
	AntBrain blackAntBrain;
	
	public Gameplay(AntBrain red, AntBrain black) { 
                // For Kirstie : Pass constructor the two AntBrains for play, pass them to loadAntBrains method OR remove that method and just load straight into params
		// Constructor
	}

	public void generateWorld() {
		// Generate a new World randomly
	}
        
        public void playGame() {
            
        }

	public void loadWorld(World uploadWorld) {
		// Update world to an existing World that is passed
	}

	public void stepGame() {
		// Go through all processes in a step of the game and repeat until all steps are done
		// Loop through all Ant in ants array:
		// Call ant.getResting(), if != 0 then do nothing
		// else call ant.getInstruction() then switch case based on instanceOf Instruction
		// Get the relevant fields from the Instruction as to what to do, eg. sense ahead, call
		// relevant methods from Cell/Ant, then pass the relevant next instruction to the ant.setState()
		// and ant.setResting() back to resting for x turns.
		// Check following pseudo code, repeat 300,000 times:
		/*
		function step(id:int) =
		  if ant_is_alive(id) then
		    let p = find_ant(id) in
		    let a = ant_at(p) in
		    if resting(a) > 0 then
		      set_resting(a, resting(a) - 1)
		    else
		      switch get_instruction(color(a), state(a)) of
		        case Sense(sensedir, st1, st2, cond):
		          let p' = sensed_cell(p, direction(a), sensedir) in
		          let st = if cell_matches(p', cond, color(a)) then st1 else st2 in
		          set_state(a, st)
		        case Mark(i, st):
		          set_marker_at(p, color(a), i);
		          set_state(a, st)
		        case Unmark(i, st):
		          clear_marker_at(p, color(a), i);
		          set_state(a, st)
		        case PickUp(st1, st2):
		          if has_food(a) || food_at(p) = 0 then
		            set_state(a, st2)
		          else begin
		            set_food_at(p, food_at(p) - 1);
		            set_has_food(a, true);
		            set_state(a, st1)
		          end
		        case Drop(st):
		          if has_food(a) then begin
		            set_food_at(p, food_at(p) + 1);
		            set_has_food(a, false)
		          end;
		          set_state(a, st)
		        case Turn(lr, st):
		          set_direction(a, turn(lr, direction(a)));
		          set_state(a, st)
		        case Move(st1, st2):
		          let newp = adjacent_cell(p, direction(a)) in
		          if rocky(newp) || some_ant_is_at(newp) then
		            set_state(a, st2)
		          else begin
		            clear_ant_at(p);
		            set_ant_at(newp, a);
		            set_state(a, st1);
		            set_resting(a, 14);
		            check_for_surrounded_ants(newp)
		          end
		        case Flip(n, st1, st2):
		          let st = if randomint = 0 then st1 else st2 in
		          set_state(a, st)
          */
		
	}

	public void endGame() {
		// On completion of all steps finish the game, total scores etc.
	}

	public void setupGame() {
		// Calls World syntax checks, AntBrain syntax checks, begins stepping through game instance
	}

	public void loadAntBrains(AntBrain red, AntBrain black) { // Kirstie: Possibly remove this method with reference to comment on constructor!
		// Loads red and black AntBrains
	}

	public int declareWinner() {
		// Calculates winners and returns 0 for Draw, 1 for Black win, 2 for Red win, -1 for error
            return -1;
	}

}
