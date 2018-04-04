package com.pekoto.algorithms;

/**
 * Levenshtein distance calculated using Wagner-Fiscer algorithm.
 * Uses a bottom-up dynamic programming approach:
 * Sets up an array to keep track of how many edit operations it would
 * take to turn the first string to the second using deletions,
 * insertions, or substitutions.
 * 
 * 1. Set up a 2D array of strOne length+1/strTwo length+1
 * 2. Initialize the first column and row to the index values
 *    (the cost to transform an empty string to that index is the length of the string until that points)
 * 3. Iterate over the rows and columns, starting from 1 and set the distance
 *    at this row/col to be the minimum of:
 *      1. Delete: [row-1][col] + 1 (we can delete to get to the row above -- +1 delete operation)
 *      2. Insert: [row][col-1] + 1 (we can get to the previous col, then add another char -- +1 insert operation)
 *      3. Substitution: [row-1][col-1] + 0 or 1 depending if chars are the same (we can transform to the upper
 *                       left, and then transform this char if required too)
 *                       
 *  Possible improvement: Can improve space/practical running time slightly by using two arrays and swapping them.
 * 
 * Running time: O(nm)
 * Space: O(nm)
 */
public class EditDistance {

    public static int getEditDistance(String strOne, String strTwo) {
        
        if(strOne.length() == 0) {
            return strTwo.length();
        }
        
        if(strTwo.length() == 0) {
            return strOne.length();
        }
        
        int [][] editDistance = new int[strOne.length()+1][strTwo.length()+1];
        
        for(int i = 0; i <= strOne.length(); i++) {
            editDistance[i][0] = i;
        }
        
        for(int j = 0; j <= strTwo.length(); j++) {
            editDistance[0][j] = j;
        }
        
        for(int row = 1; row <= strOne.length(); row++) {
            for(int col = 1; col <= strTwo.length(); col++) {
                int swapCharsCost;
                
                if(strOne.charAt(row-1) == strTwo.charAt(col-1)) {
                    swapCharsCost = 0;
                } else {
                    swapCharsCost = 1;
                }
                
                int deletionCost = editDistance[row-1][col] + 1;
                int insertionCost = editDistance[row][col-1] + 1;
                int substitutionCost = editDistance[row-1][col-1] + swapCharsCost;
                
                editDistance[row][col] = min(deletionCost, insertionCost, substitutionCost);
            }
        }
        
        return editDistance[strOne.length()][strTwo.length()];
    }
    
    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
