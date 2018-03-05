package com.pekoto.algorithms;

import java.util.Random;

public class Shuffle {

    public static Random random = new Random();
    
    /**
     * Take each element and while it's greater than the element to the left, keep shifting it left
     * 
     * Best case: O(n), already sorted
     * Worst case: O(n^2), reverse sorted 
     * 
     * 1. Iterate 0-arr.length
     * 2. At each iteration, set j to i
     * 3. While j > 0 && greater than previous element, swap j with previous element
     * 4. Decrement j
     * 
     * @param arr The array to sort
     */
    public static void shuffle(int [] arr) {
        
        for(int i = 1; i < arr.length; i++) {
            // Recall nextInt is exclusive
            int rand = random.nextInt(i+1);
            int temp = arr[rand];
            arr[rand] = arr[i];
            arr[i] = temp;
        }
    }
}
