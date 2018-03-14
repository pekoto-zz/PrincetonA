package com.pekoto.datastructures;

public class SinglyLinkedList<T> {

    private Node root;
    private int size;

    private class Node {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }
    }

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

    public void removeRecursive(T value) {
        root = removeIterative(root, value);
    }

    private Node removeRecursive(Node node, T value) {
        if (node == null) {
            return null;
        }

        if (node.value.equals(value)) {
            size--;
            return node.next;
        }

        node.next = removeIterative(node.next, value);
        return node;
    }

    public void removeIterative(T value) {

        Node first = root;

        while (first != null) {

            Node next = root.next;

            if (first.equals(value)) {
                first = next;
                return;
            } else {
                first = first.next;
            }
        }
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
    
    public void reverse() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
