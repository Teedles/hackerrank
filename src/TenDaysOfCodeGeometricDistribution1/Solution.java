package TenDaysOfCodeGeometricDistribution1;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
    		double q = (double) 1/ 3;
		double n = 5;
		double result = 0;

		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		
		// g(n,p) = q^(n-1)*p
			result = Math.pow((1-q), n-1) * q;
			System.out.println(df.format(result));	
    }
}