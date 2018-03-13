package com.pekoto.algorithms;

public class HeapSort<T extends Comparable<T>> {

    /**
     * 1. Convert the array into a 0-indexed heap
     *    (We can ignore root nodes since they will be swapped)
     *    Bottom-up, take each node and sink it down to the correct position.
     *    Reminder: sink = swap with maximum child, while parent is less than a child
     *    
     * 2. The array is now in a max heap.
     *    1. Swap the top (max) element with the last element
     *    2. Decrement the last element pointer
     *    3. Sink the new top element into the correct position
     *  
     * Best: O(n log n)
     * Average: O(n log n)
     * Worst: O(n log n)
     *  
     * In-place (constant extra memory)
     * NOT stable (identical keys may not be in same relative place after sort)
     * 
     * In practice, tends to be slower than quicksort since the inner loop is longer/lack of caching
     * 
     * GOTCHA: The array is 0-based, making it slightly harder than a binary heap.
     * bottom up parent index = (arr.length-1)/2 + 1 (first node with children)
     * Child node one = (parent index*2) + 1
     * 
     * @param arr The array to be sorted
     */
    public void sort(T [] arr) {

        int endOfHeapIndex = arr.length-1;

        // Heapify
        // Bottom up, sink each node down to the correct position
        for(int parentIndex = (endOfHeapIndex/2); parentIndex >= 0; parentIndex--) {
            sink(arr, parentIndex, endOfHeapIndex);
        }

        // Sort
        // 1. Swap top (max) node with last node
        // 2. Decrement end of heap index (the max node is in the correct position)
        // 3. Sink the new top into the correct position
        while(endOfHeapIndex > 0) {
            swap(arr, 0, endOfHeapIndex);
            endOfHeapIndex--;
            sink(arr, 0, endOfHeapIndex);
        }
    }

    /**
     * 1. Get the child index
     * 2. Check to see if a second child exists, and if it does get the max child
     * 3. If parent < maximum child, swap them, update child index and repeat
     * 4. Otherwise break -- the node is in the correct position
     * 
     * @param arr The array to sort
     * @param parentIndex The parent node index
     * @param endOfHeapIndex The index of the last element in the heap
     */
    private void sink(T [] arr, int parentIndex, int endOfHeapIndex) {

        int childOneIndex;
        int childTwoIndex;
        int maxChildIndex;

        if(parentIndex == 0) {
            childOneIndex = 1;
        } else {
            childOneIndex = (parentIndex*2)+1;
        }

        while(childOneIndex <= endOfHeapIndex) {
            if(childOneIndex+1 <= endOfHeapIndex) {
                childTwoIndex = childOneIndex+1;
                maxChildIndex = max(arr, childOneIndex, childTwoIndex);
            } else {
                maxChildIndex = childOneIndex;
            }

            if(lessThan(arr, parentIndex, maxChildIndex)) {
                swap(arr, parentIndex, maxChildIndex);
                parentIndex = maxChildIndex;
                childOneIndex = (parentIndex*2)+1;
            } else {
                break;
            }
        }
    }

    private boolean lessThan(T [] arr, int indexOne, int indexTwo) {
        return arr[indexOne].compareTo(arr[indexTwo]) < 0;
    }

    private int max(T [] arr, int indexOne, int indexTwo) {
        if(lessThan(arr, indexOne, indexTwo)) {
            return indexTwo;
        } else {
            return indexOne;
        }
    }

    private void swap(T [] arr, int indexOne, int indexTwo) {
        T temp = arr[indexOne];
        arr[indexOne] = arr[indexTwo];
        arr[indexTwo] = temp;
    }
}
