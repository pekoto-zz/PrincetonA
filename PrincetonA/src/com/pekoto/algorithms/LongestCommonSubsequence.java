package com.pekoto.algorithms;

/**
 * The longest common subsequence is the longest series of characters
 * that appear in same relative order in two Strings.
 * The characters do not have to be contiguous.
 *
 * 1. Declare a 2D array of size string length + 1
 * 2. Iterate over rows and columns
 * 3. Initialize first row/col to 0
 * 4. If the chars match, use upper left val + 1
 * 5. Else, take max from top and left element
 * 6. Finally return lower right element
 * 
 * This is like Levenstein: we go down the columns,
 * checking the longest subsequence so far.
 * If we have matching chars, increment it, otherwise store our existing longest
 * subsequence.
 *
 * Example:
 * String 1 = BATD
 * String 2 = ABACD
 * 
 * Builds a matrix like this:
 * 
 *   0 B A T D
 * 0 0 0 0 0 0
 * A 0 0 1 1 1
 * B 0 1 1 1 1
 * A 0 1 2 2 2
 * C 0 1 2 2 2
 * D 0 1 2 2 3
 * 
 * Longest common subsequence = 3 (BAD)
 * 
 * Performance: O(nm)
 */
public class LongestCommonSubsequence {

    public static int getLongestCommonSubsequence(String strOne, String strTwo) {
        
        int length[][] = new int[strOne.length()+1][strTwo.length()+1];
        
        for(int row=0; row<=strOne.length(); row++) {
            for(int col=0; col<=strTwo.length(); col++) {
                if(row==0 || col==0) {
                    // Fill first row and column with zeroes
                    length[row][col] = 0;
                } else if (strOne.charAt(row-1) == strTwo.charAt(col-1)) {
                    // Chars match > take diagonal top left element + 1
                    length[row][col] = length[row-1][col-1] + 1;
                } else {
                    // Chars do not match > take max of top and left element
                    length[row][col] = Math.max(length[row-1][col], length[row][col-1]);
                }
            }
        }
        
        return length[strOne.length()][strTwo.length()];
    }
}
