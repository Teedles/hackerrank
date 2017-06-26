package AlgoLadder;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int mySize = s.nextInt();
		
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < mySize; i++) {
		sb.append(" ");
		}
		
		int l =mySize;
		while ( l > 0 ) {
			l--;
			sb.deleteCharAt(l);
			sb.insert(l, '#');
			System.out.println(sb.toString());
		}

	}

}
