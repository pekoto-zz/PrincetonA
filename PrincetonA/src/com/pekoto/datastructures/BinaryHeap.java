package com.pekoto.datastructures;

/**
 * A kind of priority queue.
 * A complete (perfectly balanced except from trailing nodes) binary tree (each parent has at most two children).
 * 
 * Allows you to quickly find the max/min element.
 * 
 * Parent node = nodeIndex/2
 * Child node one = nodeIndex*2
 * Child node two = nodeIndex*2+1
 * 
 * Performance:
 * * put: O(log n)
 * * removeMax/Min: O(log n)
 * 
 * Implementation:
 * * put: adds a node at the end of the tree then SWIMs it up
 *        (swim: keep swapping child node with parent while parent is bigger and not past top of heap)
 * 
 * * removeMax: swaps the top (max/min) node with the last node, removes it for return, and SINKS top node down
 *              (sink: keep swapping parent node with LARGER child while parent node is smaller and not past bottom of heap)
 * 
 * Gotcha: Start indexing at 1 to make the maths easier
 * 
 * @param <T> The type to be stored on the heap (comparable)
 */
public class BinaryHeap<T extends Comparable<T>> {

    private static final int DEFAULT_SIZE = 11;
    private T[] arr;
    
    // Set the root at one to make the math easier
    private int nextElementIndex = 1;
    
    public BinaryHeap() {
        arr = (T[]) new Comparable[DEFAULT_SIZE];
    }
    
    public BinaryHeap(int initialSize) {
        arr = (T[]) new Comparable[initialSize+1];
    }
    
    /**
     * 1. Check for resize
     * 2. Add new value to bottom of heap
     * 3. Swim the new element to its correct position
     * 4. Increment next element pointer
     * 
     * @param value The element to put on to the end of the heap
     */
    public void put(T value) {
        if(nextElementIndex == arr.length) {
            resize(arr.length*2);
        }
        
        arr[nextElementIndex] = value;
        swim(nextElementIndex);
        nextElementIndex++;
    }
    
    /**
     * After a new node has been added to the bottom of the heap:
     * 1. While not past the top of the heap
     * 2. And while the child is bigger than its parent
     * 3. Swap the child with its parent
     * 
     * @param childIndex The index of the node that was just added
     */
    private void swim(int childIndex) {
        int parentIndex = childIndex/2;
        
        while(childIndex > 0 && lessThan(parentIndex, childIndex)) {
            swap(parentIndex, childIndex);
            childIndex = parentIndex;
            parentIndex /= 2;
        }
    }
    
    /**
     * Returns the node with the largest (smallest) value
     * 1. Decrement next element pointer
     * 2. Swap the largest node with the last node
     * 3. Save largest value for return
     * 4. Get rid of the reference
     * 5. Sink the new top node to its correct position
     * 6. Check for resize
     * 7. Return the max value
     * 
     * @return The largest/smallest node in the heap
     */
    public T removeMax() {
        nextElementIndex--;
        swap(1, nextElementIndex);
        T value = arr[nextElementIndex];
        arr[nextElementIndex] = null;
        
        sink(1);
        
        if(nextElementIndex == arr.length/4) {
            resize(arr.length/2);
        }
        
        return value;
    }
    
    private void sink(int parentIndex) {
        int childOneIndex = parentIndex*2;
        int childTwoIndex = childOneIndex+1;
        
        while(parentIndex < nextElementIndex-1 && (lessThan(parentIndex, childOneIndex) || lessThan(parentIndex, childTwoIndex))) {
            int maxChildIndex = max(childOneIndex, childTwoIndex);
            swap(parentIndex, maxChildIndex);
            parentIndex = maxChildIndex;
            childOneIndex = parentIndex*2;
            childTwoIndex = childOneIndex+1;
        }
    }
    
    private void swap(int indexOne, int indexTwo) {
        T temp = arr[indexOne];
        arr[indexOne] = arr[indexTwo];
        arr[indexTwo] = temp;
    }
    
    private boolean lessThan(int indexOne, int indexTwo) {
        if(arr[indexTwo] == null) {
            return false;
        } else if (arr[indexOne] == null) {
            return false;
        } else {
            return arr[indexOne].compareTo(arr[indexTwo]) < 0;
        }
    }
    
    private int max(int indexOne, int indexTwo) {
        if(lessThan(indexOne, indexTwo)) {
            return indexTwo;
        } else {
            return indexOne;
        }
    }
    
    public int size() {
        return nextElementIndex -1;
    }
    
    public boolean isEmpty() {
        return nextElementIndex == 1;
    }
    
    private void resize(int newSize) {
        T [] copy = (T[]) new Comparable[newSize];
        
        for(int i = 0; i < nextElementIndex; i++) {
            copy[i] = arr[i];
        }
        
        arr = copy;
    }
}
