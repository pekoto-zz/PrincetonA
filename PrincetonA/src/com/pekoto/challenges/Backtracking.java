package com.pekoto.challenges;

import java.util.ArrayList;

public class Backtracking {

    /*
     * Prints all of the binary numbers that can be made
     * using n bits.
     */
    public static ArrayList<String> getBinary(int n) {
        ArrayList<String> variations = new ArrayList<String>();
        char [] variation = new char[(int) Math.pow(2, n)];
        
        getBinaryVariations(variations, variation, 0);
        
        return variations;
    }
    
    private static void getBinaryVariations(ArrayList<String> variations, char[] variation, int index) {
        if(index >= variation.length) {
            variations.add(new String(variation));
            return;
        }
        
        variation[index] = '0';
        getBinaryVariations(variations, variation, index+1);
        
        variation[index] = '1';
        getBinaryVariations(variations, variation, index+1);
    }
}
