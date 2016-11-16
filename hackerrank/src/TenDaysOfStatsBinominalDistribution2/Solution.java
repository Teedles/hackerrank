package TenDaysOfStatsBinominalDistribution2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Solution {

	public static void main (String[] Args) {
		
		double p = 0.12;
		
		int numTrials = 10;
		
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		
		double[] result = new double[numTrials+1];
		
		for (int numCorrect = 0; numCorrect <= numTrials; numCorrect++) {
		
			result[numCorrect] = binomialDist(numCorrect, numTrials, p);
		}
		
		double res = 0;
		
		// max two bad results
		for (int i = 0; i <=2; i++) {
			res += result[i];
		}
		
		System.out.println(df.format(res));
		
		res = 0;
		
		// at least two bad results
		for (int i = 2; i <=numTrials; i++) {
			res += result[i];
		}
		
		System.out.println(df.format(res));
		
		// System.out.println(Arrays.toString(result));
	}
	
	
	// Borrowed this code from the internets. My math-fu is weak :(
	/**
	 * Compute the binomial distribution function for numCorrect correct choices out of numTrials trials with probability of probValue.
	 * 
	 * @see http://mathworld.wolfram.com/BinomialDistribution.html
	 */
	public static double binomialDist(int numCorrect, int numTrials, double probValue)
	{
		BigInteger ntF = factorial(numTrials);
		BigInteger denom = factorial(numCorrect).multiply(factorial(numTrials - numCorrect));

		BigDecimal ntFBD = new BigDecimal(ntF);
		BigDecimal denomBD = new BigDecimal(denom);
		BigDecimal quotient = ntFBD.divide(denomBD, 40, RoundingMode.HALF_UP);

		BigDecimal restBD = BigDecimal.valueOf(Math.pow(probValue, numCorrect) * Math.pow((1d - probValue), numTrials - numCorrect));
		return(quotient.multiply(restBD).doubleValue());
	}
	
	/**
	 * Compute factorial of n
	 */
	public static BigInteger factorial(int n)
	{
		BigInteger res = BigInteger.ONE;

		for (int i = n; i>1; i--)
		{
			res = res.multiply(BigInteger.valueOf(i));
		}
		return(res);
	}
}
