package com.pekoto.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic data type (here for use in graph adjacency lists)
 * 
 * Essentially a stack with linked list.
 * 
 */
public class Bag<T> implements Iterable<T> {

    private Node<T> first;
    private int size;
    
    private static class Node<T> {
        private T value;
        private Node<T> next;
        
        public Node(T value) {
            this.value = value;
        }
    }
    
    /**
     * Add a new value into the bag
     * (will be added to the "top" of the bag, like a stack)
     * 
     * @param value The value to add to the bag
     */
    public void add(T value) {
        Node<T> oldFirst = first;
        first = new Node<T>(value);
        first.next = oldFirst;
        
        size++;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    // Iterable implementation
    public Iterator<T> iterator()  {
        return new ListIterator<T>(first);  
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext() { 
            return current != null;
        }

        public void remove() { 
            throw new UnsupportedOperationException();  
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            T item = current.value;
            current = current.next; 
            return item;
        }
    }
}
