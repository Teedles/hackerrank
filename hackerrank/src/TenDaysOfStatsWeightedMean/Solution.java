package TenDaysOfStatsWeightedMean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int myArraySize = s.nextInt();
		
		int[] myArray = new int[myArraySize];
		int[] myArrayWeights = new int[myArraySize];
		
		for (int i = 0; i < myArraySize ; i++) {
			myArray[i] = s.nextInt(); 
		}
		
		for (int i = 0; i < myArraySize ; i++) {
			myArrayWeights[i] = s.nextInt(); 
		}
		
		s.close();
		
		System.out.println(getWeightedMean(myArray, myArrayWeights));
	}
	
	private static BigDecimal getWeightedMean(int[] v, int[] w) {
		
		double tempValue =0.0;
		double tempWeight =0.0;
		
		for (int i=0; i<v.length; i++) {
			tempValue += v[i] * w[i];
			tempWeight += w[i];
		}
		
		BigDecimal result = new BigDecimal(tempValue / tempWeight).setScale(1, RoundingMode.HALF_EVEN);
		
		return result;
	}
}
