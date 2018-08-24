package AlgoTripletCompare;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int a0 = in.nextInt();
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b0 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
        List<Integer> alice = new ArrayList<Integer>();
        List<Integer> bob = new ArrayList<Integer>();
        
        alice.add(a0);
        alice.add(a1);
        alice.add(a2);
        bob.add(b0);
        bob.add(b1);
        bob.add(b2);
        
        int[] ab = compareAB(alice, bob);
        
        System.out.println(ab[0] + " " + ab[1]);
    }
    
    public static int[] compareAB(List<Integer> a, List<Integer> b) {
        int[] tempAB = new int[2];
        for (int i =0; i < 3; i++ ) {
            if (a.get(i) == b.get(i)) {
                continue;
            } else if ( a.get(i) > b.get(i)) {
                tempAB[0] += 1;
            } else { tempAB[1] += 1; }
        }
        
        return tempAB;
    }
}
