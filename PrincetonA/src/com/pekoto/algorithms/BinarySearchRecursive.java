package com.pekoto.algorithms;

/**
 *  A simple binary search class
 *
 *  @author Graham McRobbie
 *  @
 */
public class BinarySearchRecursive {

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
        int high = arr.length-1;
        int low = 0;

        while(low <= high) {
            // Prevent arithmetic overflow when high and low
            // are large
            int mid = low + (high-low)/2;

            if(arr[mid] < key) {
                // Check the upper half
                low = mid+1;
            } else if (arr[mid] > key) {
                // Check the lower half
                high = mid-1;
            } else {
                // Key found
                return mid;
            }
        }

        // Key not found
        return -1;
    }
}

