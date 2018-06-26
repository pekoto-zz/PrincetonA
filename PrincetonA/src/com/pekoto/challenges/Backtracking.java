package com.pekoto.challenges;

import java.util.ArrayList;

public class Backtracking {

    /*
     * Gets a list of all of the binary numbers that can be made
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
    
    /* 
     * Gets a list of all the decimal numbers that can be made
     * using n digits.
     */
    public static ArrayList<String> getDecimal(int n) {
        ArrayList<String> variations = new ArrayList<String>();
        char [] variation = new char[n];
        
        getDecimalVariations(variations, variation, 0);
        
        return variations;
    }
    
    private static void getDecimalVariations(ArrayList<String> variations, char [] variation, int index) {
        if(index >= variation.length) {
            variations.add(new String(variation));
            return;
        }
        
        for(int i = 0; i<=9; i++) {
            variation[index] = Character.forDigit(i, 10);
            getDecimalVariations(variations, variation, index+1);
        }
    }
    
    /*
     * Returns all of the dice rolls that can be used to sum
     * to a given n.
     * Backtracking example.
     */
    public static ArrayList<ArrayList<Integer>> diceSum(int numOfDice, int targetSum) {
        ArrayList<ArrayList<Integer>> variations = new ArrayList<ArrayList<Integer>>();
        
        diceSum(numOfDice, targetSum, 0, variations, new ArrayList<Integer>());
        
        return variations;
    }
    
    private static void diceSum(int numOfDice, int targetSum, int sumSoFar, ArrayList<ArrayList<Integer>> variations, ArrayList<Integer> variation) {
        
        if(numOfDice == 0) {
            if(targetSum == sumSoFar) {
                variations.add(clone(variation));
            }
            
            return;
        }
        
        for(int i = 1; i <= 6; i++) {
            
            // If every subsequent dice is a 1
            int min = sumSoFar + i + (1 * (numOfDice-1));

            // If every subsequent dice is a 6
            int max = sumSoFar + i + (6 * (numOfDice-1));
            
            // Check if valid
            if(min <= targetSum && targetSum <= max) {
                
                // Choose this element
                variation.add(i);

                // Explore (recursive call)
                diceSum(numOfDice-1, targetSum, i+sumSoFar, variations, variation);
                
                // Unchoose this element
                variation.remove(variation.size()-1);
            }
        }
    }
    
    private static ArrayList<Integer> clone(ArrayList<Integer> list) {
        ArrayList<Integer> clone = new ArrayList<Integer>();
        
        for(Integer i: list) {
            clone.add(i);
        }
        
        return clone;
    }
}
