package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HardChallenges {
    
    /*
     * Adds two numbers without the plus operator.
     * 
     * Point: Normally when we add two numbers, we add the digits and the carry.
     *        We can split these steps up, adding the digits ignoring the carry,
     *        and then adding the digits taking only the carry.
     *        Then we add these two together using the same process until
     *        there is nothing left to carry.
     *        
     *  759                759            323
     * +674               +674    -->    1110
     *  323 (no carry)    1110 (carry)   1433 (nothing left to carry, so we're done)
     *  
     * In binary we can replicate this process:
     * 1. Add step: We have a 0 when both bits are 0 or 1 (XOR) (Both being 1 would result in a carry)
     * 2. Carry step: We have a 1 if i-1 of both a and b are 1 (Take bits where both are 1, then carry to left)
     */
    public static int addWithoutPlus(int a, int b) {
        while(b != 0) {
            int sum = a ^ b;    // Add without carry
            int carry = (a & b) << 1;     // Carry without add
            a = sum;
            b = carry;
        }
        
        return a;
    }
    
    /*
     * Returns a random set of m integers from an array
     * Essentially the same as Knuth's shuffle algorithm.
     */
    public static int [] getRandomSet(int [] arr, int m) {
        int [] randomSet = new int[m];
        
        for(int i = 0; i < m; i++) {
            randomSet[i] = arr[i];
        }
        
        Random rand = new Random();
        
        for(int i = m; i < arr.length; i++) {
            int randomIndex = rand.nextInt(i+1);
            
            if(randomIndex < m) {
                randomSet[randomIndex] = arr[i];
            }
        }
        
        return randomSet;
    }
    
    /*
     * Gets all subarrays. This is O(n^2), so rarely optimal,
     * but it can be a useful brute force solution if you can't
     * think of any other solution.
     * 
     * It starts checking from the longest subarray, which is generally
     * what is asked for.
     * 
     * Note: This assumes you want at least 2 elements in the subarray
     */
    public static List<int[]> getAllSubarrays(int [] arr) {
        
        List<int[]> subarrays = new ArrayList<int[]>();
        
        for(int subarrayLength = arr.length-1; subarrayLength > 0; subarrayLength--) {
            
            // I.e., for a length 5 array, will be 0 on first iteration (1 length 5 array)
            // then 1 for second iteration (2 length 4 arrays), etc.
            int startOffsetCount = arr.length-1-subarrayLength;
            
            for(int start = 0; start <= startOffsetCount; start++) {
                // Do some checking in this subarray
                // E.g., equal number of letters and numbers
                subarrays.add(getSubArray(arr, start, start + subarrayLength));
            }
        }
        
        return subarrays;
    }
    
    private static int[] getSubArray(int [] arr, int start, int end) {
        int [] subarray = new int[end-start+1];
        
        for(int i = start; i <= end; i++) {
            int subarrayIndex = i-start;
            subarray[subarrayIndex] = arr[i];
        }
        
        return subarray;
    }
}
