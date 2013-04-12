/**
* AntBrain.java Represents the FSM of an individual AntBrain competitor, reads in 
* a raw text representation of an Ant Brain and converts it into a list of
* discrete instructions.
*
* @author Software Engineering 2012-13 Group 9 Simon Bell, Kirstie Hale,
* Paige Gray, Matt Chapman, Alex Flight, ??James Bellamy??
* @version 1
*/
package AntAttack_Group9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* 
* @author Alex
*/
public final class AntBrain {
   
   private ArrayList<Instruction> fsm = new ArrayList();
   private String name;

   /**
    * 
    * @param antBrainFile
    */
   public AntBrain(String antBrainFile) {
       name = antBrainFile;
       try {
           if (checkAntBrainSyntax(antBrainFile)) {
               BufferedReader br = new BufferedReader(new FileReader(antBrainFile));
               String currLine;
               while ((currLine = br.readLine()) != null) {
                   currLine = currLine.toLowerCase();
                   String[] input = currLine.split("\\s");
                   Instruction i = null;
                   switch (input[0]) {
                       case "sense":
                           String markerNum = "";
                           if(input.length >= 6) {
                               markerNum = input[5];
                           }
                           String cond = input[4] + markerNum;
                           i = new Sense(Sense.dirFromString(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), Sense.condFromString(cond));
                           break;
                       case "mark":
                           i = new Mark(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                           break;
                       case "unmark":
                           i = new Unmark(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                           break;
                       case "pickup":
                           i = new PickUp(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                           break;
                       case "drop":
                           i = new Drop(Integer.parseInt(input[1]));
                           break;
                       case "turn":
                           i = new Turn(Turn.dirFromString(input[1]), Integer.parseInt(input[2]));
                           break;
                       case "move":
                           i = new Move(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                           break;
                       case "flip":
                           i = new Flip(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                           break;
                   }
                   fsm.add(i);
               }
           } else {
               // exception should be thrown by checkAntBrainSyntax by this point
               System.out.println("Error in ant brain file");
           }
       } catch (Exception e) {
           System.err.println("Error: " + e.getMessage());
       }
   }

   /**
    * Check if ant brain file is syntactically correct iterates through passed
    * file line by line
    *
    * @param antBrainFile the ant brain file (.brain) to check
    * @return boolean true if ant brain is syntactically correct false if not
    * @throws Exception  
    */
   public boolean checkAntBrainSyntax(String antBrainFile) throws Exception {

       BufferedReader br = new BufferedReader(new FileReader(antBrainFile));
       String currLine;
       int lineNumber = 0;

       // process each line in ant brain file
       while ((currLine = br.readLine()) != null) {

           lineNumber++;
           // convert current line to lowercase
           currLine = currLine.toLowerCase();

           // store variables used for regular expression
           String st = "[0-9][0-9]{0,3}";
           String i = "[0-5]";
           String comment = "(\\s*?;.*$)?"; // ignore everything after ;

           // check Sense
           String regex = "((sense)*\\s(here|ahead|leftahead|rightahead)\\s(" + st + ")\\s(" + st + ")\\s(friend|foe|friendwithfood|foewithfood|food|rock|(marker\\s" + i + ")|foemarker|home|foehome))" + comment;

           // check Mark and Unmark
           regex += "|((mark|unmark)*\\s(" + i + ")\\s(" + st + "))" + comment;

           // check PickUp and Move
           regex += "|((pickup|move)*\\s(" + st + ")\\s(" + st + "))" + comment;

           // check Drop
           regex += "|((drop)*\\s(" + st + "))" + comment;

           // check Turn
           regex += "|((turn)*\\s(left|right)\\s(" + st + "))" + comment;

           // check Flip
           regex += "|((flip)*\\s([0-9]*)\\s(" + st + ")\\s(" + st + "))" + comment;

           // perform regular expression
           Pattern p1 = Pattern.compile(regex);
           Matcher m = p1.matcher(currLine);

           // if current line doesn't match regular expression, print error
           // to terminal and return false (could possibly throw exception here?)
           if (!m.matches()) {
               //System.out.println("Error in java brain file "+file+" at line "+lineNumber+ ": "+currLine);
               throw new Exception("Error in java brain file " + antBrainFile + " at line " + lineNumber + ": " + currLine);
               //return false;
           }
       }
       return true;
   }

   /**
    * 
    * @param currState
    * @return
    */
   public Instruction getInstruction(int currState) {
       return fsm.get(currState);
   }
   
   public String getName() {
       return name;
   }
}
