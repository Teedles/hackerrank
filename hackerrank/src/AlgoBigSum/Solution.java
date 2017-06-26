package AlgoBigSum;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        long result = 0;
    	Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        
        System.out.println(computeResult(arr));
        
    }
    
    public static long computeResult(int[] a) {
    	long temp = 0;
    	
    	for(int arr_i=0; arr_i < a.length; arr_i++) {
    		temp += (long) a[arr_i];
    	}
    	
    	return temp;
    }
}