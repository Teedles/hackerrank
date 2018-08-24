package TenDaysOfStatsBinominalDistribution1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Solution {

	public static void main (String[] Args) {
		
	double m = 1.09;
	double f = 1;
	
	double p = m / ( m + f);

		
		int numTrials = 6;
		
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		
		double result = 0;
		
		for (int numCorrect = 3; numCorrect <= numTrials; numCorrect++) {
		
			result += binomialDist(numCorrect, numTrials, p);
		}
		
		System.out.println(df.format(result));
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
