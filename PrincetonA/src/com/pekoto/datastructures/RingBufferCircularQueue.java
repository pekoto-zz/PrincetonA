package com.pekoto.datastructures;

/**
 * An implementation of a circular queue/ring buffer.
 * 
 * A ring buffer is a FIFO queue of fixed size.
 * The structure contains a fixed-size array, write position,
 * and used space variables.
 * 
 * Enqueue:
 *  1. If there is capacity...
 *  2. If the write position > capacity, clamp it to 0 (wrap around)
 *  3. Insert the element, and increment the write position and used space variables
 * 
 * Dequeue:
 *  1. Check we have something in our queue
 *  2. The read position = (write position - used space)
 *  3. If the read position is -ve, add on the size of our array
 *  4. Read the element
 *  5. Decrement the write position
 *  6. Return the element
 *  
 *  Performance:
 *   * Enqueue: O(1)
 *   * Dequeue: O(1)
 *   * Don't need pointers like linked-list, and can still do element-wise access
 *   * No need to resize
 *   * Elements contiguous in memory
 *   * - Must be fixed size
 *   * - Need to decide how to handle overwrites
 *   
 *  Uses:
 *   * Data streaming or anything with a fixed buffer (stop client sending more data until buffer cleared) 
 * 
 */
public class RingBufferCircularQueue<T> {

    private T[] arr;
    private int usedSpace;
    private int writePos;
    
    public RingBufferCircularQueue(int size) {
        this.arr = (T[]) new Object[size];
    }
    
    /**
     * Adds an item to the queue.
     * 
     * 1. If the queue is full, return false.
     * 2. If writePos >= array size, clamp writePos = 0
     * 3. Insert the element
     * 4. Increment writePos and usedSpace
     * 
     * @param item The item to add to the queue
     * @return false if the queue is full, so item can't be enqueued,
     *         true otherwise.
     */
    public boolean enqueue(T item) {
        
        // We can't enqueue this data -- queue full
        if(usedSpace == arr.length) {
            return false;
        }
        
        if(writePos >= arr.length) {
            // Wrap around
            writePos = 0;
        }
        
        arr[writePos] = item;
        writePos++;
        usedSpace++;
        
        return true;
    }
    
    /**
     * Dequeues and returns the element at the started of the queue
     * 
     * 1. Check if queue is empty
     * 2. index = (writePos - usedSpace)
     * 3. If index is -ve, add the array length
     * 4. Decrement used space counter and return item
     * 
     * @return The item at the start of the queue
     */
    public T dequeue() {
        if(usedSpace == 0) {
            throw new NullPointerException("Can't dequeue empty queue");
        }
        
        int readPos = writePos-usedSpace;
        
        if(readPos < 0) {
            // Buffer has wrapped around
            readPos += arr.length;
        }
        
        T value = arr[readPos];
        arr[readPos] = null;
        usedSpace--;
        
        return value;
    }
}
