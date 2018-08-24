package TenDaysOfStatsStdDev;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int myArraySize = s.nextInt();
		
		int[] myArray = new int[myArraySize];

		for (int i = 0; i < myArraySize ; i++) {
			myArray[i] = s.nextInt(); 
		}
		
		BigDecimal myMean = getMyMean(myArray);
		BigDecimal myStdDev = getStdDec(myMean, myArray);
		
		System.out.println(myStdDev);
	}

	private static BigDecimal getStdDec(BigDecimal myMean, int[] myArray) {
		BigDecimal total = new BigDecimal(0).setScale(1, RoundingMode.HALF_EVEN);
		BigDecimal aLength = new BigDecimal(myArray.length).setScale(1, RoundingMode.HALF_EVEN);
		
		for (int i = 0; i<myArray.length; i++) {
			
			BigDecimal temp = new BigDecimal(myArray[i]).setScale(1, RoundingMode.HALF_EVEN);
			
			temp = temp.subtract(myMean);
			temp = temp.pow(2);
			
			total = total.add(temp);
		}
		
		total = total.divide(aLength);
		
			
		return bigSqrt(total);
	}

	private static BigDecimal getMyMean(int[] myArray) {
		int total = 0;
		
		for (int i =0; i<myArray.length; i++) {
			total += myArray[i];
		}
		
		return new BigDecimal(total / myArray.length).setScale(1, RoundingMode.HALF_EVEN);
	}

	private static final BigDecimal SQRT_DIG = new BigDecimal(150);
	private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

	/**
	 * Private utility method used to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti 
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
	    BigDecimal fx = xn.pow(2).add(c.negate());
	    BigDecimal fpx = xn.multiply(new BigDecimal(2));
	    BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
	    xn1 = xn.add(xn1.negate());
	    BigDecimal currentSquare = xn1.pow(2);
	    BigDecimal currentPrecision = currentSquare.subtract(c);
	    currentPrecision = currentPrecision.abs();
	    if (currentPrecision.compareTo(precision) <= -1){
	        return xn1;
	    }
	    return sqrtNewtonRaphson(c, xn1, precision).setScale(1, RoundingMode.HALF_EVEN);
	}

	/**
	 * Uses Newton Raphson to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti 
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	public static BigDecimal bigSqrt(BigDecimal c){
	    return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
	}
	
}
