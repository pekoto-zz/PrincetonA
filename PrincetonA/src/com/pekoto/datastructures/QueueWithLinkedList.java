package com.pekoto.datastructures;

import java.util.Iterator;

/**
 * A queue implementation using a linked list
 *
 * Advantages:
 * * No need for array resizing
 *
 * Disadvantages:
 * * Cannot do O(1) access to random element
 * * Extra memory required for linked list pointers
 *
 * Performance:
 * * enqueue O(1)
 * * dequeue O(1)
 *
 * @param <T> The type of object that can be stored in this queue
 */
public class QueueWithLinkedList<T> implements Iterable<T> {

    private Node start;
    private Node end;
    private int size = 0;

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    /**
     * 1. Save the old end
     * 2. Set end to a new node with the client value
     * 3. If queue is empty, set start = end
     * 4. Otherwise point the old end to end
     *
     * @param value The object to add onto the back of the queue
     */
    public void enqueue(T value) {
        Node oldEnd = end;
        end = new Node(value);

        // 1 item queue
        if(start == null) {
            start = end;
        } else {
            oldEnd.next = end;
        }

        size++;
    }

    /**
     *
     * 1. Check if queue is empty
     * 2. Save the value at the start of the queue
     * 3. Set start to be the next value in the queue
     * 4. If start is now null, set end to be null too
     * 5. return the value
     *
     * @return The object at the front of the queue
     */
    public T dequeue() {
        if(isEmpty()) {
            throw new NullPointerException("Cannot dequeue empty queue");
        }

        T value = start.value;

        start = start.next;

        // If we only had one element in our queue
        // clear the end pointer as well
        if(start == null) {
            end = null;
        }

        size--;

        return value;
    }

    public T peekStart() {
        if(start == null) {
            return null;
        } else {
            return start.value;
        }
    }

    public T peekEnd() {
        if(end == null) {
            return null;
        } else {
            return end.value;
        }
    }

    public boolean isEmpty() {
        return start == null && end == null && size == 0;
    }

    public int size() {
        return size;
    }

    // Iterable implementation

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = start;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T value = current.value;
            current = current.next;
            return value;
        }

        public void remove() {
            // Remove during iteration not supported
        }
    }
}
