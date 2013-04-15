/**
 * RandomInt produces a pseudo-random integer that matches the customer's random
 * integer
 *
 * @author Software Engineering 2012-13 Group 9 - Simon Bell, Kirstie Hale,
 * Paige Gray, Alex Flight
 * @version FINAL
 */
package AntAttack_Group9;

import java.math.BigInteger;
import java.util.ArrayList;

public class RandomInt {

    private ArrayList<BigInteger> s = new ArrayList<>();
    private int counter = 0;

    /**
     * Creates a new RandomInt class that returns a random integer value within a range specified.
     */
    public RandomInt() {
        s.add(new BigInteger("12345"));	// initial seed
        // calculate first 4 elements of series
        for (int i = 0; i < 3; i++) {
            // calculate si+1 from si
            s.add(s.get(i).multiply(new BigInteger("22695477")).add(new BigInteger("1")));
        }
    }

    /**
     * Return the next pseudo-random integer
     * @param n pseudo-random number will be between 0 and n
     */
    public int nextInt(int n) {
        // calcualte next element in series 
        s.add(s.get(counter + 3).multiply(new BigInteger("22695477")).add(new BigInteger("1")));
        // calculate series x from element i in s
        //BigInteger result = (s.get(counter+4).divide(new BigInteger("65536"))) .mod(new BigInteger(Integer.toString(n-1)));
        BigInteger result = (s.get(counter + 4).divide(new BigInteger("65536"))).mod(new BigInteger("16384"));
        // increment counter on every call of nextInt
        counter++;
        // convert BigInteger to int and return
        return result.intValue() % n;

    }
}
