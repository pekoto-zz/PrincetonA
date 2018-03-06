package com.pekoto.algorithms;

public class MergeSort {

    /**
     * MergeSort
     * 
     * Divide and conquer
     * 
     * 1. Split the array into two halves (n sublists)
     * 2. Repeatedly merge the sublists to produce new sorted sublists
     * 
     * Performance: O(n log n)
     * Space: O(n) -- for auxiliary array during merge
     * 
     * Stable sort: objects with the same keys will stay in the same relative positions 
     * 
     * Used for sorting linked lists.
     * Used if all the data can't fit into memory:
     * 1. Sort sublists into temporary files
     * 2. Combine sorted files into a single large file (for x elements, until memory runs out)
     * 
     * @param arr The array to be sorted
     */
    public static void sort(int [] arr) {
        sort(arr, 0, arr.length-1);
    }
    
    /**
     * Recursively breaks the array into n sublists
     * Sorts sublists by merging them together.
     * 
     * 1. While left pointer < right pointer
     * 2. Calculate the mid point
     * 3. Call sort for 0-mid (break into left sublists)
     * 4. Call sort for mid+1-arr length (break into right sublists)
     * 5. Merge sublists together, thus sorting them
     * 
     * @param arr The array to be sorted
     * @param left The left pointer of the array
     * @param right The right pointer 
     */
    private static void sort(int [] arr, int left, int right) {
        if(left < right) {
            int mid = left + (right-left) / 2;
            
            sort(arr, left, mid);
            sort(arr, mid+1, right);
            
            merge(arr, left, mid, right);
        }
    }
    
    /**
     * Merge two subarrays back into the main array, taking the highest element each time, thus sorting them
     * 1. Declare left size (mid-left+1)
     * 2. Declare right size (right-mid)
     * 3. Copy data into left/right arrays
     * 4. Set up array pointer=left
     * 5. Copy in left/right arrays into main array, based on which is lower
     * 6. Copy the remaining elements     * 
     * 
     * @param arr The array to be sorted
     * @param left The left pointer
     * @param mid The midpoint
     * @param right The right pointer
     */
    private static void merge(int [] arr, int left, int mid, int right) {
        int leftSize = mid-left + 1;
        int rightSize = right-mid;
        
        int [] leftArr = new int[leftSize];
        int [] rightArr = new int[rightSize];
        
        System.arraycopy(arr, left, leftArr, 0, leftSize);
        System.arraycopy(arr, mid+1, rightArr, 0, rightSize);
        
        int leftPointer = 0;
        int rightPointer = 0;
        int arrPointer = left;
        
        while(leftPointer < leftArr.length && rightPointer < rightArr.length) {
            if(leftArr[leftPointer] <= rightArr[rightPointer]) {
                arr[arrPointer++] = leftArr[leftPointer++];
            } else {
                arr[arrPointer++] = rightArr[rightPointer++];
            }
        }
        
        while(leftPointer < leftArr.length) {
            arr[arrPointer++] = leftArr[leftPointer++];
        }
        
        while(rightPointer < rightArr.length) {
            arr[arrPointer++] = rightArr[rightPointer++];
        }
    }   
}
