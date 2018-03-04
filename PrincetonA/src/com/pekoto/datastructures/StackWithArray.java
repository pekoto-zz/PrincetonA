package com.pekoto.datastructures;

import java.util.Iterator;

/**
 * A stack implemented using a resizing array.
 *
 * Advantages:
 * * No extra memory needed for linked list pointers
 * * Can do O(1) access to random elements if required
 *
 * Disadvantages:
 * * Need to dynamically resize array
 * (But amortized analysis shows on average this will not affect order)
 *
 * Performance:
 * * push O(1) (amortized -- O(n) worst case)
 * * pop O(1) (amortized -- O(n) worst case)
 *
 * Uses:
 * * Browser back button
 * * Word processor undo function
 * * Interpreter (parsing commands -- use two stack algorithm)
 * * - pop values on value stack
 * * - pop operators on operator stack
 * * - at right parenthesis, pop operator and two values
 * * - put values back on value stack
 *
 * @param <T> The type to store on the stack
 */
public class StackWithArray<T> implements Iterable<T> {

    private static final int DEFAULT_SIZE = 10;

    private T [] arr;
    private int nextElementIndex = 0;

    public StackWithArray() {
        arr = (T[])new Object[DEFAULT_SIZE];
    }

    public StackWithArray(int initialSize) {
        arr = (T[])new Object[initialSize];
    }

    /**
     * 1. Check for resize (pointer == array length)
     * 2. Set arr[pointer] to new value
     * 3. Increment pointer
     *
     * @param value The value to push onto the stack
     */
    public void push(T value) {
        if(nextElementIndex == arr.length) {
            resize(arr.length*2);
        }

        arr[nextElementIndex] = value;
        nextElementIndex++;
    }

    /**
     *
     * 1. Check if stack empty
     * 2. Save arr[pointer-1] value
     * 3. Set arr[pointer-1] to null
     * 4. Decrement pointer
     * 5. Check for resize (pointer > 0 && pointer == arr length /4
     *
     * @return The value on top of the stack
     */
    public T pop(){
        if(nextElementIndex == 0) {
            throw new NullPointerException("Cannot pop empty stack");
        }

        T value = arr[nextElementIndex-1];
        arr[nextElementIndex-1] = null;
        nextElementIndex--;

        // Resize to 1/2 size when 1/4 size (to avoid thrashing)
        if(nextElementIndex > 0 && nextElementIndex == arr.length/4) {
            resize(arr.length/2);
        }

        return value;
    }

    public T peek() {
        return arr[nextElementIndex-1];
    }

    public int size() {
        return nextElementIndex;
    }

    public boolean isEmpty() {
        return nextElementIndex == 0;
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

        for(int i = 0; i < nextElementIndex; i++) {
            copy[i] = arr[i];
        }

        arr = copy;
    }

    // Iterable implementation
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {

        private int nextElement = nextElementIndex;

        public boolean hasNext() {
            return nextElement > 0;
        }

        public T next() {
            nextElement--;

            return arr[nextElement];
        }

        public void remove() {
            // Remove during iteration not supported
        }
    }
}
