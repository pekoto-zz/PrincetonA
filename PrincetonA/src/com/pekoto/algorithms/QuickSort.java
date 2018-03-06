package com.pekoto.algorithms;

public class QuickSort {

    /**
     * Divide and conquer algorithm
     * 
     * 1. Pick a partition, put it in the right place
     * 2. Everything to the left is < partition
     * 3. Everything to the right is > partition
     * 4. Sort the left and right subarrays recursively
     * 
     * In-place (but O(log n) space due to recursion calls)
     * NOT stable
     * 
     * O(n log n) average time
     * O(n^2) worst time -- but very unlikely if we shuffle the array
     * 
     * In practice, faster than merge sort since we don't need to allocate
     * the auxiliary arrays
     * 
     * @param arr The array to sort
     */
    public static void sort(int [] arr) {
        // Important to avoid worst-case scenario, which can occur when array sorted
        Shuffle.shuffle(arr);
        sort(arr, 0, arr.length-1);
    }
    
    /**
     * Pick a partition and put everything < to the left, everything > to the right
     * Recursively sort left and right sub arrays
     * 
     * @param arr The array to sort
     * @param left The left pointer
     * @param right The right pointer
     */
    private static void sort(int [] arr, int left, int right) {
        if(left < right) {
            int partitionIndex = partition(arr, left, right);
            
            sort(arr, left, partitionIndex-1);
            sort(arr, partitionIndex+1, right);
        }
    }
    
    /**
     * 1. Pick a pivot point (here we just pick the first element)
     * 2. Take two values:
     *  i -- the left of the array (after the pivot)
     *  j -- the right of the array
     *  
     * 3. Find a value of i that is > pivot value (increment i)
     * 4. Find a value of j that is < pivot value (decrement j)
     * 5. Swap i and j, while pointers haven't crossed over
     * 6. Finally, swap j and pivot (left) to put pivot into the correct position
     * 
     * @param arr The array to sort
     * @param left The left pointer
     * @param right The right pointer
     * @return The correct index of the pivot value
     */
    private static int partition(int [] arr, int left, int right) {
        
        int pivotVal = arr[left];
        int i = left+1;
        int j = right;
        
        while(true) {
            while(arr[i] < pivotVal) {
                i++;
                
                if(i == right) {
                    break;
                }
            }
            
            while(pivotVal < arr[j]) {
                j--;
                if(j == left) {
                    break;
                }
            }
            
            // Check if pointers crossed over
            if(j > i) {
                // Swap i and j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                break;
            }
        }
        
        // Swap pivot into place
        int temp = arr[left];
        arr[left] = arr[j];
        arr[j] = temp;
        
        return j;
    }
}
