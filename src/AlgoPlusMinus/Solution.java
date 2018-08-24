package AlgoPlusMinus;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double plus = 0;
        double min = 0;
        double zero = 0;
        
        int arr[] = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
    
        for(int arr_j=0; arr_j < n; arr_j++){
        	if (arr[arr_j] > 0) {
        		plus++;
        	} else if (arr[arr_j] < 0) {
        		min++;
        	} else { zero++; }
        }
        
        DecimalFormat df = new DecimalFormat("0.000000"); 
        
        System.out.println(df.format(plus / n));
        System.out.println(df.format(min / n));
        System.out.println(df.format(zero / n));
    }
    
}
