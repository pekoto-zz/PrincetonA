package com.pekoto.algorithms;

public class QuickSelect {

    /**
     * Returns the Kth smallest element.
     * E.g., 0 returns the smallest element
     * 1 returns the second smallest element, etc.
     * 
     * Modified quicksort -- 
     * 1. Partition the array
     * 2. If the partition index > k, look in left subarray (right = partitionIndex-1)
     * 3. If the partition index < k, look in right subarray (left = partitionIndex+1)
     * 4. Recurse in just one of these subarrays -- we don't need both halves to be sorted
     * 5. We're finished with the partition index = k
     * 
     * Linear time O(n) on average
     * 
     * @param arr The arr to select the Kth min element from
     * @param k the element to return
     * @return The Kth min element
     */
    public static int select(int [] arr, int k) {
        Shuffle.shuffle(arr);
        
        int left = 0;
        int right = arr.length-1;
        
        while(left < right) {
            int partitionIndex = partition(arr, left, right);
            
            if(partitionIndex < k) {
                left = partitionIndex+1;
            } else if (partitionIndex > k) {
                right = partitionIndex-1;
            } else {
                return arr[k];
            }
        }
        
        return arr[k];
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
