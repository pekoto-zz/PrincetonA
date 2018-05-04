package com.pekoto.challenges;

import java.util.Arrays;

public class RecursionAndDynamic {

    /*
     * Counts the ways a stair can be traversed via 1, 2, or
     * 3 steps at a time.
     * (Note: Will overflow fairly quickly).
     */
    public static int countWays(int n) {
        int [] memo = new int[n+1];
        Arrays.fill(memo, -1);
        
        return countWays(n, memo);
    }
    
    private static int countWays(int n, int[] memo) {
        if(n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] > -1) {
            return memo[n];
        } else {
            memo[n] = countWays(n-1, memo) + countWays(n-2, memo) + countWays(n-3, memo);
            return memo[n];
        }
    }
}
