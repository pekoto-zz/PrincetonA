package com.pekoto.datastructures;

/**
 * A hash map that uses an array of linked lists to deal with collisions
 * 
 * If there is a collision:
 *   Store the next element in the next element in the linked list
 *   
 * Operations typically take O(1) time, though could theoretically
 * take O(n) time if we had some very bad, non-uniform, hashing algorithm
 * 
 * C.f., linear probing.
 * 
 * This uses more memory (more empty array indices), but no need to resize
 * the array, so degrades more gracefully.
 *
 * @param <T> The key type
 * @param <V> The value type
 */
public class HashMapWithChaining<T, V> {

    // Typically set to some power of two or prime number
    private final int ARRAY_SIZE = 97;
    
    private Node [] arr;
    private int size = 0;
    
    private static class Node {
        private Object key;
        private Object value;
        private Node next;
        
        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
    public HashMapWithChaining() {
        arr = new Node[ARRAY_SIZE];
    }
    
    /**
     * We can use this for testing.
     * Set some very small size for testing hash collisions.
     * 
     * @param initialSize The initial size of the array
     */
    public HashMapWithChaining(int initialSize) {
        arr = new Node[initialSize];
    }

    /**
     * 1. Calculate the hash of the key
     * 2. Start at index [hash] in the array
     * 3. While it's not null, check to see if we're updating an existing key
     * 4. If it's a new key, add it, setting the next node for the new key
     *    to be the linked list current at this index
     * 
     * @param key The key for this object
     * @param value The value of the object
     */
    public void put(T key, V value) {
        int hash = hash(key);
        
        for(Node node = arr[hash]; node != null; node = node.next) {
            if(key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        
        size++;
        arr[hash] = new Node(key, value, arr[hash]);
    }
    
    /**
     * Return the value of the object with this key
     * 
     * 1. Calculate the hash for this object
     * 2. Get the node at index [hash] in the array
     * 3. Check through the linked list at this position until
     *    we find a key that matches ours
     * 
     * @param key The key to get
     * @return The value of the object with this key,
     *         or null if the key doesn't exist
     */
    public V get(T key) {
        int hash = hash(key);
        
        for(Node node = arr[hash]; node != null; node = node.next) {
            if(key.equals(node.key)) {
                return (V)node.value;
            }
        }
        
        return null;
    }
    
    public void delete(T key) {
        int hash = hash(key);
        
        arr[hash] = delete(arr[hash], key);
    }
    
    public Node delete(Node node, T key) {
        if (node == null) {
            return null;
        }
        
        if (key.equals(node.key)) {
            size--;
            return node.next;
        }
        
        node.next = delete(node.next, key);
        return node;
    }
    
    public boolean contains(T key) {
        return get(key) != null;
    }
    
    public int size() {
        return size;
    }
    
    /** 
     * Squeezes the hash code down into the size of
     * an array index.
     * 
     * @param key The key of the object to be store
     * @return The index of the array to store this object in
     */
    private int hash(T key) {
        return Math.abs(key.hashCode()) % arr.length;
    }
}
