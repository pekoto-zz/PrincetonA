package com.pekoto.algorithms;

/**
 * Converts a string into an integer
 * 
 * 1. Declare a running total
 * 2. Start at the end of the string and work backwards (from least significant digit)
 * 3. Get the char ASCII value - 48 (48 = ASCII offset to get the int value)
 * 4. Work out the multiplier: 10^0 for the rightmost digit, etc.
 * 5. Increment total by char value * multiplier
 * 
 * (This assumes number is positive)
 * 
 */
public class Atoi {

    public static int atoi(String str) {
        int total = 0;
        int asciiOffset = 48;   // ASCII 0 = 48
        
        for(int i = str.length()-1; i>=0; i--) {
            int chrVal = str.charAt(i) - asciiOffset;
            double multiplier = Math.pow(10, str.length()-(i+1));
            
            total += chrVal * multiplier;
        }
        
        return total;
    }
}
