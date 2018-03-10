package com.pekoto.algorithms;

/**
 * 1. Perform a bottom of heapify operation 
 *  * (turn the array into a binary heap structure)
 *  * In reverse order, take a node and sink it down (the bottom nodes will be swapped out)
 *  
 * 2. Sort the array
 *  * Now we have the max element at the root
 *  * Swap this max element to end of array
 *  * Sink down the new max
 *  
 *  In-place (constant extra memory)
 *  Not stable (elements with same keys will not be in same relative positions)
 *  
 *  Best: O(n log n)
 *  Average: O(n log n)
 *  Worst: O(n log n)
 *  
 *  Heap sort is optimal in space and time, but the inner look is longer than quicksort's
 *  and poor use of caching means quicksort usually ends up being faster.
 *  
 *  GOTCHA: Unlike binary heap, heapsort uses 0-indexing, requiring a check in the sink operation
 *  
 * @param <T> The type of elements to sort
 */
public class HeapSort<T extends Comparable<T>> {

    public void sort(T [] arr) {
        
        int endOfHeapIndex = arr.length;
        
        // Heapify
        // Ignore the child nodes (endOHeapIndex/2)-1
        // Bottom up choose a node and sink it down to the correct place
        for(int parentIndex = (endOfHeapIndex/2)-1; parentIndex >= 0; parentIndex--) {
            sink(arr, parentIndex, endOfHeapIndex);
        }
        
        endOfHeapIndex--;
        
        // Sort
        // Take the largest node (node at the top) and swap it with the end of the heap
        // Sink the new top of the heap to the correct place
        while(endOfHeapIndex > 0) {
            swap(arr, 0, endOfHeapIndex);
            endOfHeapIndex--;
            sink(arr, 0, endOfHeapIndex);
        }
    }
    
    /**
     * 1. Take a parent node and while we are not at the end of the heap,
     * and the node is less than one of its children...
     * 2. Swap the node with the larger child
     * 
     * GOTCHA: The array is 0-indexed, so need a special condition for that child
     * 
     * @param arr The array to sort
     * @param parentIndex The node to sink down
     * @param endOfHeapIndex The bottom of the heap
     */
    private void sink(T [] arr, int parentIndex, int endOfHeapIndex) {
        
        int childOneIndex = 0;
        
        if(parentIndex != 0) {
            childOneIndex = parentIndex*2;
        } else {
            childOneIndex = 1;
        }
        
        int childTwoIndex = childOneIndex+1;
        
        while(childOneIndex < endOfHeapIndex && (lessThan(arr, parentIndex, childOneIndex) || lessThan(arr, parentIndex, childTwoIndex))) {
            int maxChildIndex = max(arr, childOneIndex, childTwoIndex);
            swap(arr, parentIndex, maxChildIndex);
            parentIndex = maxChildIndex;
            childOneIndex = parentIndex*2;
            childTwoIndex = childOneIndex+1;
        }
    }
    
    private void swap(T [] arr, int indexOne, int indexTwo) {
        T temp = arr[indexOne];
        arr[indexOne] = arr[indexTwo];
        arr[indexTwo] = temp;
    }
    
    private boolean lessThan(T [] arr, int indexOne, int indexTwo) {
        if(indexTwo >= arr.length) {
            return false;
        } else if(arr[indexTwo] == null) {
            return false;
        } else if (arr[indexOne] == null) {
            return false;
        } else {
            return arr[indexOne].compareTo(arr[indexTwo]) < 0;
        }
    }
    
    private int max(T [] arr, int indexOne, int indexTwo) {
        if(lessThan(arr, indexOne, indexTwo)) {
            return indexTwo;
        } else {
            return indexOne;
        }
    }
}
