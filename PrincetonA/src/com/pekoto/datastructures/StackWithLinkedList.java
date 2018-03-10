package com.pekoto.datastructures;

import java.util.Iterator;

/**
 * A stack implemented using a linked list.
 *
 * Advantages:
 * * No need for array resizing
 *
 * Disadvantages:
 * * Cannot do O(1) access to random element
 * * Extra memory required for linked list pointers
 *
 * Performance:
 * * push O(1)
 * * pop O(1)
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
 * @param <T> The type of elements to hold on the stack
 */
public class StackWithLinkedList<T> implements Iterable<T> {

    private Node top;
    private int size;

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    /**
     * Pushes a value onto the stack
     *
     * 1. Save the old node
     * 2. Create a new node with the client value
     * 3. Point the new top to the old top
     *
     * @param value The value to put on the stack
     */
    public void push(T value) {
        Node oldTop = top;

        top = new Node(value);
        top.next = oldTop;

        size++;
    }

    /**
     * Pops a value from the top of the stack
     *
     * 1. Check if the stack is empty
     * 2. Save the value from the stop of the stack
     * 3. Set top.next to the new top
     * 4. Return the saved value
     *
     * @return The value on top of the stack
     */
    public T pop() {
        if(top == null) {
            throw new NullPointerException("Cannot pop an empty stack");
        }

        T value = top.value;
        top = top.next;
        size--;

        return value;
    }

    public T peek() {
        if(top == null) {
            return null;
        } else {
            return top.value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    // Iterable implementation

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = top;

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
