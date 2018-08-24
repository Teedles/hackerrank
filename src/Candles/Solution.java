package Candles;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
    	int tallestCandle = 0;
    	int numberOfTallestCandles = 0;
    	
    	Scanner in = new Scanner(System.in);
        int[] arr = new int[5];
        for(int arr_i=0; arr_i < 5; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        
        in.close();
        
        // find tallest candle
        
        for (int i = 0; i < arr.length; i++) {
        	if (arr[i] > tallestCandle) {
        		tallestCandle = arr[i];
        	}
        }
        
        // count tallest candles
        
        for (int k = 0; k < arr.length; k++) {
        	if (arr[k] == tallestCandle) {
        		numberOfTallestCandles++;
        	}
        }
        
                
        System.out.println(tallestCandle + " " + numberOfTallestCandles);

    }
}
