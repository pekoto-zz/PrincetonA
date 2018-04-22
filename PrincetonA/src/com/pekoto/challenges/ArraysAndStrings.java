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
    
    /**
     * Returns true if two strings are permutations of each other.
     * Assumes strings are not null and use extended ASCII characters.
     * 
     * Could also sort the strings and compare each element, if you
     * wanted to avoid using array completely.
     * 
     * Performance: O(n)
     * Space: O(1)
     * 
     * @param strOne
     * @param strTwo
     * @return true if strings are permutations of each other, false otherwise
     */
    public static boolean arePermutations(String strOne, String strTwo) {
        
        if(strOne.length() != strTwo.length()) {
            return false;
        }
        
        final int EXTENDED_ASCII_LENGTH = 256;
        
        int [] charCounts = new int[EXTENDED_ASCII_LENGTH];
        
        for(int i = 0; i < strOne.length(); i++) {
            charCounts[strOne.charAt(i)]++;
        }
        
        for(int i = 0; i < strTwo.length(); i++) {
            charCounts[strTwo.charAt(i)]--;
            
            if(charCounts[strTwo.charAt(i)] < 0) {
                return false;
            }
        }
        
        return true;
    }
}
