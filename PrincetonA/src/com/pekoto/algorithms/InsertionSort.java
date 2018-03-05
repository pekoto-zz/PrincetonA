package com.pekoto.algorithms;

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
public class InsertionSort {

    public static void sort(int [] arr) {

        for(int i = 0; i < arr.length; i++) {

            int j = i;

            while(j > 0 && (arr[j] < arr[j-1])) {
                    // Swap elements while right element is bigger than left
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    
                    j--;
            }
        }
    }
}
