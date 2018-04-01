package com.pekoto.algorithms;

/**
 * An iterative version of binary search.
 * More efficient in Java since Java does not support tail-call optimisation.
 * (Tail-call optimisation = stop a new stack frame being allocated when a call
 *  to a method is simply returned from the calling function)
 */
public class BinarySearchIterative {
    
    /**
     * The binary search algorithm
     * O(log n)
     * 
     * @param arr The array to search, must be sorted
     * @param key The key to find in the array
     *
     * @return The element of the array that key exists in, -1 otherwise
     */
    public static int search(int [] arr, int key) {
        int low = 0;
        int high = arr.length-1;
        
        while(low <= high) {
            int mid = low + (high-low)/2;
            
            if(key < arr[mid]) {
                high = mid-1;
            } else if (key > arr[mid]) {
                low = mid+1;
            } else {
                return mid;
            }
        }
        
        // Key not found
        return -1;
    }
}
