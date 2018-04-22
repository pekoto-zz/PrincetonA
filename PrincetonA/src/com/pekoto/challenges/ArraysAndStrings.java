package com.pekoto.challenges;

public class ArraysAndStrings {
    
    /**
     * Returns true if all characters in a string are unique.
     * Assumes only extended ASCII chars.
     * 
     * Can run without using the array by sorting the string
     * and then comparing if two contiguous elements are the same.
     * 
     * Performance: O(n)
     * Space: O(1)
     * 
     * @param str Checks a string for all unique characters
     * @return True if all chars are unique
     */
    public static boolean areAllCharsUnique(String str) {
        
        final int EXTENDED_ASCII_LENGTH = 256;
        
        if(str.length() > EXTENDED_ASCII_LENGTH) {
            return false;
        }
        
        boolean [] charFound = new boolean[EXTENDED_ASCII_LENGTH];
        
        for(int i = 0; i < str.length(); i++) {
            if(charFound[str.charAt(i)]) {
                return false;
            } else {
                charFound[str.charAt(i)] = true;
            }
        }
        
        return true;   
    }
}