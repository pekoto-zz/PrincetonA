package com.pekoto.algorithms;

/**
 * Converts an integer into a string
 * 
 * 1. Iterate through the string from right > left (starting at least significant digit)
 * 2. Get the remainder by mod 10
 * 3. Decrease the number by dividing by 10
 * 4. Add the remainder to the end of the char array
 * 5. If the number < 0, break -- we've finished
 * 
 */
public class Itoa {
    
    public static String itoa(int num) {
        char [] chrs = new char[10];
        int asciiOffset = 48;
        int digits = 0;
        boolean isNegative=false;
        
        if(num < 0) {
            isNegative=true;
            num *=-1;
        };
        
        for(int i = chrs.length-1; i>=0; i--) {
            
            if(num <= 0) {
                break;
            }
            
            int remainder = num % 10;
            num = num/10;
            chrs[i] = (char)(remainder+asciiOffset);
            digits++;
        }
        
        String str = new String(chrs, chrs.length-digits, digits);
        
        if(isNegative) {
            str = "-" + str;
        }
        
        return str; 
    }
}
