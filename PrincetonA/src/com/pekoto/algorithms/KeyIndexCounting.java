package com.pekoto.algorithms;

/**
 * DEF. Radix: The number of unique chars/integers in an alphabet
 *             E.g., the radix of a +ve integer is 10 (0-9)
 *             
 * If we have some array of items with a radix small enough that
 * we can use each item to index into an array, we can use key-value counting
 * to quickly sort.
 *      
 * Example uses:
 * - Sort enums
 * - Sort first letter of strings
 * 
 * 1. Declare an array for counting the number of each element (radix size + 1) -- this will be 1-indexed
 * 2. Iterate over the input, updating the number of each element you have
 * 3. Calculate cumulative counts -- e.g., the array index where this char should be in the sorted array
 * 4. Fill in an auxiliary array: 
 *  4.1 Iterate over the input array
 *  4.2 Get the start pos from the count array
 *  4.3 Insert the char from the input array into the aux array at start pos
 *  4.4 Increment the count
 * 5. Finally copy the aux array back to the main array
 * 
 * Performance: O(n + r) -- where r is size of radix
 * Space: O(n + r)
 * Stable, not in-place
 *      
 */
public class KeyIndexCounting {

    private static final int RADIX_SIZE = 10;
    
    public static void sort(int [] arr) {
        
        int [] counts = new int[RADIX_SIZE+1];
        
        // Count how many of each char we have,
        // using the value of each char as an index, starting from 1.
        for(int i = 0; i < arr.length; i++) {
            int index = arr[i]+1;
            counts[index]++;
        }
        
        // Get cumulative counts for each char,
        // telling us how many chars we have before this char
        // appears in the array
        for(int i = 0; i < RADIX_SIZE; i++) {
            counts[i+1] += counts[i];
        }
        
        int [] auxArray = new int[arr.length];
        
        // Fill in the auxiliary array using the counts
        // and input array
        for(int i = 0; i < arr.length; i++) {
            int digit = arr[i];
            int auxIndex = counts[digit];
            auxArray[auxIndex] = digit;
            counts[digit]++;
        }
        
        // Copy the auxiliary array back to the main array
        for(int i = 0; i < arr.length; i++) {
            arr[i] = auxArray[i];
        }
    }
}
