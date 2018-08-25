package TenDaysOfStatsGeometricDistribution2;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/*
 * The probability that a machine produces a defective product is 1/3.
 * What is the probability that the 1st defect is found during the first 5 inspections?
 * 
 * Input:
 * 1 3
 * 5
 * 
 */

public class Solution {

	public static void main(String[] args) {
		
		double q = (double) 1/ 3;
		double result = 0;

		// round to 3 places hr requirement
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		
		// g(n,p) = q^(n-1)*p
		// except now we have to do it for 5 tries
		for (int n = 1; n < 6; n++) {
			result += Math.pow((1-q), 5-n) * q;
		}
		
		System.out.println(df.format(result));
	}
}
