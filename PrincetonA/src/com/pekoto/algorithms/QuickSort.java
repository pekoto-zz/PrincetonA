package com.pekoto.algorithms;

public class QuickSort {

    /**
     * A divide and conquer algorithm
     * Pick a pivot and partition:
     *  - Put it in the correct place
     *  - All lesser elements to the left
     *  - All greater elements to the right
     *  
     *  Recursively do the same with everything to the left of the partition
     *  Recursively do the same with everything to the right of the partition
     *  
     *  Average performance: O(n log n)
     *  Worse case: O(n^2) -- if we always pick the greatest/smallest element
     *  -- If picking last element, would happen if array was already sorted/reverse sorted
     *  
     *  Space: O(log n) -- unlike mergesort, which requires O(n)
     *  Also tends to be faster than mergesort in practice since it does not allocating addition
     *  space for the auxiliary arrays  
     * 
     * @param arr The array to sort
     */
    public static void sort(int [] arr) {
        sort(arr, 0, arr.length-1);
    }
    
    /**
     * Pick a pivot and partition:
     *  - Put it in the correct place
     *  - All lesser elements to the left
     *  - All greater elements to the right
     *  
     *  Recursively do the same with everything to the left of the partition
     *  Recursively do the same with everything to the right of the partition     * 
     * 
     * @param arr The array to sort
     * @param left The left array pointer
     * @param right The right array pointer
     */
    private static void sort(int [] arr, int left, int right) {
        if(left < right) {
            // After this, partition element will be in the right place
            int partitionIndex = partition(arr, left, right);
            
            sort(arr, left, partitionIndex-1);
            sort(arr, partitionIndex+1, right);
        }
    }
    
    /**
     * Places pivot element at correct index
     * All smaller elements to the left, and all greater elements to the right
     * i = index of smaller/equal elements (behind j)
     * j = current element we're checking
     * 
     * 1. Save pivot value
     * 2. for j = left to < right
     * 3. if arr[j] < pivot value
     * 4.     increment i
     * 5.     swap i and j
     * 6. Finally, swap i+1 with right
     * 7. Return i+1 -- the correct pivot index
     * 
     * @param arr The array to sort
     * @param left The left pointer
     * @param right The right pointer
     * @return The correct location of the pivot value
     */
    private static int partition(int [] arr, int left, int right) {
        int pivotValue = arr[right];
        int i = left-1;
        
        // Go through array
        for(int j = left; j < right; j++) {
            // If j < pivot, increment i and swap
            if(arr[j] < pivotValue) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Finally, swap i+1 with right (pivot)
        i++;
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
        
        // Return final partition index
        return i;
    }
}
