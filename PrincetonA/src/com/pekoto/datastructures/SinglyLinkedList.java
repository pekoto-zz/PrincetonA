package com.pekoto.datastructures;

import java.util.Iterator;

/**
 * A simple linked list class.
 *
 * @param <T> The type of data to store in the linked list
 */
public class SinglyLinkedList<T> implements Iterable<T>{

    private Node root;
    private int size;

    private class Node {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }
        
        public String toString() {
            return value.toString();
        }
    }

    // 
    public Node getRoot() {
        return root;
    }
    
    /**
     * Adds a new value to the end of the linked list
     * 
     * 1. Set the root via a recursive call...
     * 2. If the node is null, return a new node
     * 3. Otherwise, set node.next = node.next + value...
     * 
     * @param value The value to add to the linked list
     */
    public void addRecursive(T value) {
        root = addRecursive(root, value);
        size++;
    }

    private Node addRecursive(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }

        node.next = addRecursive(node.next, value);

        return node;
    }

    /**
     * Adds a new node to the linked list iteratively
     * 
     * 1. If the root is null, set it to be a new node
     * 2. Otherwise, keep iterating until node.next is null
     * 3. Set node.next = new node
     * 
     * @param value The value to add to the linked list
     */
    public void addIterative(T value) {
        if (root == null) {
            root = new Node(value);
        } else {
            Node node = root;

            while (node.next != null) {
                node = node.next;
            }

            node.next = new Node(value);
        }

        size++;
    }

    /**
     * Removes a value from the linked list
     * 
     * 1. Set the root by making a recursive call...
     * 2. If node is null, return null
     * 3. If we have found our node, return node.next
     * 4. Otherwise, call again for node.next
     * 
     * @param value The value to remove from the linked list
     */
    public void removeRecursive(T value) {
        root = remove(root, value);
    }

    private Node remove(Node node, T value) {
        if (node == null) {
            return null;
        }

        if (node.value.equals(value)) {
            size--;
            return node.next;
        }

        node.next = remove(node.next, value);
        
        return node;
    }

    public T getFirst() {
        return root.value;
    }

    public T getLast() {
        if (root == null) {
            return null;
        }
        
        Node node = root;
        
        while(node.next != null) {
            node = node.next;
        }
        
        return node.value;
    }

    public T get(int index) {
        if(root == null) {
            return null;
        }
        
        if(index > size-1) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        
        Node node = root;
        
        for(int i = 0; i != index; i++) {
            node = node.next;
        }
        
        return node.value;
    }
    
    /**
     * Reverses the linked list
     * 
     * 1. Set up previous, current, and next nodes
     * 2. Set next to the next node
     * 3. Switch the current node to point at the previous node
     * 4. Set the previous node to be current node
     * 5. Set the current node to be the next node
     * 6. Finally, set the root to the previous node
     */
    public void reverse() {
        if(root == null || root.next == null) {
            return;
        }
        
        Node previous = null;
        Node current = root;
        Node next = null;
        
        while(current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        
        root = previous;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    // Iterable implementation
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        Node node = root;

        public boolean hasNext() {
            return node != null;
        }

        public T next() {
            T value = node.value;
            node = node.next;
            return value;
        }

        public void remove() {
            // Remove during iteration not supported
        }
    } 
}
