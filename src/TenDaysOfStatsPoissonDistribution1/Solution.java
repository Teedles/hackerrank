package TenDaysOfStatsPoissonDistribution1;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Solution {

	public static void main(String[] args) {
		// round to 3 places hr requirement
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		
		/*
		 * Poisson
		 * P(k,l) = l^k * e^-l / k!
		 */

		double l = 2.5;
		double k = 5;
		double result = 0;
		
		result = Math.pow(l, k) * Math.exp(-l) / factorial(BigInteger.valueOf((long) k)).doubleValue();
		
		System.out.println(df.format(result));
	
	}

	// taken from le internets
	// credit: https://stackoverflow.com/users/2430549/holdoffhunger
	public static BigInteger factorial(BigInteger number) {
	    BigInteger result = BigInteger.valueOf(1);

	    for (long factor = 2; factor <= number.longValue(); factor++) {
	        result = result.multiply(BigInteger.valueOf(factor));
	    }

	    return result;
	}
	
}
