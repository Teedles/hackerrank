package AlgoMinMaxSum;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
    	BigInteger sums[] = new BigInteger[3];
    	sums[0] = BigInteger.ZERO;
    	BigInteger temp = BigInteger.ZERO;
    	
    	Scanner in = new Scanner(System.in);
        int[] arr = new int[5];
        for(int arr_i=0; arr_i < 5; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        
        in.close();
        
        // sums
        for(int arr_k=0; arr_k < 5; arr_k++){
        	sums[0] = sums[0].add(BigInteger.valueOf(arr[arr_k]));
        }
        
        sums[1] = sums[0];
        sums[2] = BigInteger.valueOf(0);
        
        for(int arr_j=0; arr_j < 5; arr_j++){      	
        	
        	temp = sums[0].subtract(BigInteger.valueOf(arr[arr_j]));
        	
            if (temp.compareTo(sums[1]) == -1) {
            	sums[1] = temp;
            }

        
            if (temp.compareTo(sums[2]) == 1) {
            	sums[2] = temp;
            }
                      
        }
        
        System.out.println(sums[1] + " " + sums[2]);

    }
}
