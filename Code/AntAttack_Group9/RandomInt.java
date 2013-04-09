/** RandomInt produces a pseudo-random integer that
 *  matches the customer's random integer
 *
 * @author Sotware Engineering - Group 9 - Simon
 * @version 9-Apr-2013
 */

package AntAttack_Group9;

import java.util.*;
import java.math.BigInteger;

public class RandomInt {

  private ArrayList<BigInteger> s = new ArrayList<BigInteger>();
	private int counter = 0;

	public RandomInt(){
		
		s.add(new BigInteger("12345"));	// initial seed

		// calculate first 4 elements of series
		for(int i = 0; i < 3; i++){

			// calculate si+1 from si
			s.add(s.get(i) . multiply(new BigInteger ("22695477")) .add(new BigInteger ("1")));

		}

	}
	
	/** return the next pseudo-random integer
     * 
     * @param n pseudo-random number will be between 0 and n 
     */
	public int nextInt(int n)
	{

		// calcualte next element in series 
		s.add(s.get(counter+3) . multiply(new BigInteger ("22695477")) .add(new BigInteger ("1")));
		
		// calculate series x from element i in s
		//BigInteger result = (s.get(counter+4).divide(new BigInteger("65536"))) .mod(new BigInteger(Integer.toString(n-1)));
		BigInteger result = (s.get(counter+4).divide(new BigInteger("65536"))) .mod(new BigInteger("16384"));
		
		// increment counter on every call of nextInt
		counter++;

		// convert BigInteger to int and return
		return result.intValue() % n;

	}

	/* example usage:
	public static void main(String[] args){

		RandomInt ri = new RandomInt();

		System.out.println(ri.nextInt(16385)); // returns 7193
		System.out.println(ri.nextInt(16385)); // returns 2932
		System.out.println(ri.nextInt(16385)); // returns 10386
		System.out.println(ri.nextInt(16385)); // returns 5575
		System.out.println(ri.nextInt(16385)); // returns 100

	}
	*/
}
