
package AntAttack_Group9;

/**
 *
 * @author Alex
 */
public class Sense implements Instruction {
    private enum senseDir {
        HERE, AHEAD, LEFTAHEAD, RIGHTAHEAD
    }
    private enum condition {
        FRIEND, FOE, FRIENDWFOOD, FOEWFOOD, FOOD, ROCK, MARKER0, MARKER1, MARKER2, 
        MARKER3, MARKER4, MARKER5, FOEMARKER, HOME, FOEHOME
    }
    
    private senseDir senseDirVal;
    private condition condVal;
    private int s1;
    private int s2;
    
    public Sense(senseDir sDir, int s1, int s2, condition con) {
        this.senseDirVal = sDir;
        this.condVal = con;
        this.s1 = s1;
        this.s2 = s2;
    }
    
     public condition getCondVal() {
        return condVal;
    }

    public int getS1() {
        return s1;
    }

    public int getS2() {
        return s2;
    }

    public senseDir getSenseDirVal() {
        return senseDirVal;
    }
}
