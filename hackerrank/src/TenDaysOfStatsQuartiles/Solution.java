package TenDaysOfStatsQuartiles;

import java.util.Arrays;
import java.util.Scanner;


public class Solution {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int myArraySize = s.nextInt();
		
		int[] myArray = new int[myArraySize];
		
		for (int i = 0; i < myArraySize ; i++) {
			myArray[i] = s.nextInt(); 
		}
		
		s.close();
		
		Arrays.sort(myArray);
				
		getQuartiles(myArraySize, myArray);
	}
	
	private static void getQuartiles(int s, int[] a) {
		
		if (s %2 == 0) {
			
			// even length so median is two central values
			int[] tempQ1 = Arrays.copyOfRange(a, 0, (s/2));
			
			int[] tempQ3 = Arrays.copyOfRange(a, (s/2), s);
			
			System.out.println(getMedian(tempQ1)); 
			System.out.println(( a[(s / 2) -1] + a[(s / 2)]) / 2);
			System.out.println(getMedian(tempQ3)); 
			
		} else {
			
			int[] tempQ1 = Arrays.copyOfRange(a, 0, (s/2));
			int[] tempQ3 = Arrays.copyOfRange(a, (s/2)+1, s);
			
			System.out.println(getMedian(tempQ1));
			System.out.println(a[(s / 2)]);
			System.out.println(getMedian(tempQ3));
		}
		
	}
	
	private static int getMedian(int[] a) {
		if (a.length %2 == 0) {
			
			// even length so median is two central values
			return (( a[(a.length / 2) -1] + a[(a.length / 2)]) / 2);
			
		} else {
			
			return a[(a.length / 2)];
		}
	}
}
