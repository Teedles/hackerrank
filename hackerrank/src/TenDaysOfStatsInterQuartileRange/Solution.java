package TenDaysOfStatsInterQuartileRange;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int myArraySize = s.nextInt();

		int[] lowerHalf;
		int[] upperHalf;

		ArrayList<Integer> myList = new ArrayList<Integer>();

		int[] myArray = new int[myArraySize];
		int[] myArrayFrequencies = new int[myArraySize];

		for (int i = 0; i < myArraySize ; i++) {
			myArray[i] = s.nextInt(); 
		}

		for (int i = 0; i < myArraySize ; i++) {
			myArrayFrequencies[i] = s.nextInt(); 
		}

		s.close();

		for (int i = 0; i < myArraySize ; i++) {
			for (int f = 0; f < myArrayFrequencies[i] ; f++) {
				myList.add(myArray[i]);
			}
		}

		Collections.sort(myList);

		int[] sortedExpandedList = new int[myList.size()];

		Iterator<Integer> it = myList.iterator();

		for (int i=0; i< myList.size(); i++) {
			sortedExpandedList[i] = it.next().intValue();
		}

		if ((sortedExpandedList.length %2) == 0) {
			lowerHalf = new int[(sortedExpandedList.length / 2)];
			upperHalf = new int[(sortedExpandedList.length / 2)];
		} else {
			lowerHalf = new int[(sortedExpandedList.length / 2)];
			upperHalf = new int[(sortedExpandedList.length / 2)+2];
		}

		lowerHalf = Arrays.copyOfRange( sortedExpandedList, 0, lowerHalf.length);
		upperHalf = Arrays.copyOfRange( sortedExpandedList, upperHalf.length -1,  sortedExpandedList.length);

		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(1);
		formatter.setMinimumFractionDigits(1);
		formatter.setRoundingMode(RoundingMode.HALF_EVEN); 
		Float lowerHalfMedian = new Float(formatter.format(getMedian(lowerHalf)));
		Float upperHalfMedian = new Float(formatter.format(getMedian(upperHalf)));
		System.out.println(upperHalfMedian - lowerHalfMedian);

	}

	private static float getMedian(int[] a) {
		if ((a.length %2) == 0) {
			// even length so median is two central values
			return (( a[(a.length / 2)-1] + a[(a.length / 2)]) / 2);
		} else {
			return a[(a.length / 2)];
		}
	}
}
