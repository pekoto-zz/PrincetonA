package com.pekoto.datastructures;

/**
 * A queue implementation using a resizing array
 *
 * * Advantages:
 * * No extra memory needed for linked list pointers
 * * Can do O(1) access to random elements if required
 *
 * Disadvantages:
 * * Need to dynamically resize array
 * (But amortized analysis shows on average this will not affect order)
 * * Can end up with wasted space since pointers are only reset
 *   when everything has been dequeued
 *
 * Performance:
 * * enqueue O(1) (amortized -- O(n) worst case)
 * * dequeue O(1) (amortized -- O(n) worst case)
 *
 * @param <T> The type of objects to store in the queue
 */
public class QueueWithArray<T> {

    private static final int DEFAULT_SIZE = 10;

    private int start = 0;
    private int end = 0;

    private int size = 0;

    private T [] arr;

    public QueueWithArray() {
        arr = (T[]) new Object[DEFAULT_SIZE];
    }

    public QueueWithArray(int initialSize) {
        arr = (T[]) new Object[initialSize];
    }

    /**
     * 1. Check for resize
     * 2. Put at object onto the end of the queue
     * 3. Increment the end pointer
     *
     * @param value The object to put into the queue
     */
    public void enqueue(T value) {
        if(end == arr.length) {
            resize(arr.length*2);
        }

        arr[end] = value;
        end++;
        size++;
    }

    /**
     *
     * 1. Check if queue is empty
     * 2. Save the value at the start of the queue
     * 3. Set the start of the queue to null
     * 4. Increment the start pointer
     * 5. If start == end, set start and end pointers to 0 (empty queue)
     * 6. Check for resizing
     *
     * @return The object at the front of the queue
     */
    public T dequeue() {
        if(isEmpty()) {
            throw new NullPointerException("Can't dequeue empty queue");
        }

        T value = arr[start];
        arr[start] = null;
        start++;

        // We dequeued everything
        if(start == end) {
            start = 0;
            end = 0;
        }

        if(end == arr.length/4) {
            resize(arr.length/2);
        }

        size--;

        return value;
    }

    public T peekStart() {
        return arr[start];
    }

    public T peekEnd() {
        if(end == 0) {
            return null;
        } else {
            return arr[end-1];
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Copy the array to a newly sized array
     *
     * 1. Create a new array of the requested size
     * 2. For 0 < pointer, copy in array elements
     * 3. Set old array to be new array
     *
     * @param newSize New size of the array
     */
    private void resize(int newSize) {
        T [] copy = (T[]) new Object[newSize];

        for(int i = 0; i < end; i++) {
            copy[i] = arr[i];
        }

        arr = copy;
    }
}
