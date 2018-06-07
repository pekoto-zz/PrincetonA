package com.pekoto.challenges;

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
            int randomIndex = rand.nextInt(m);
            
            if(randomIndex < i+1) {
                randomSet[randomIndex] = arr[i];
            }
        }
        
        return randomSet;
    }
}
