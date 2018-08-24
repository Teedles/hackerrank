package AlgoDiagonalSums;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for(int a_i=0; a_i < n; a_i++){
            for(int a_j=0; a_j < n; a_j++){
                a[a_i][a_j] = in.nextInt();
            }
        }
        
        // compute primary & secondary diagonal
        int[] solution = computeDiagonals(a);
        System.out.println(Math.abs(solution[0] - solution[1]));
    }
    
    public static int[] computeDiagonals(int[][] n) {
        int[] result = new int[2];
        
        // compute primary
        for(int a_i=0; a_i < n.length; a_i++){
        	result[0] += n[a_i][a_i];
        }
        
        int foo = n.length -1;
        // compute secondary
        for(int a_j=0; a_j < n.length; a_j++){
        	result[1] += n[a_j][foo];
        	foo--;
        }
        
        // System.out.println(result[0] + " " + result[1]);
        return result;
    }
}
