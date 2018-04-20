package com.pekoto.datastructures;

import java.util.HashMap;

/**
 * A least recently used (LRU) cache implementation.
 * 
 * The LRU cache allows objects to be inserted and
 * retrieved in constant time.
 * 
 * If the cache becomes full, the least recently used item is removed.
 * When an item is retrieved or added, it becomes the most recently used item.
 * 
 * To get and put in constant time, we use a HashMap.
 * To keep track of the most/least recently used, we use a doubly-linked list.
 * The HashMap points to nodes in our double-linked list.
 *
 * Performance:
 * Get: O(1)
 * Put: O(1)
 *
 * @param <K> The key type
 * @param <V> The value type
 */
public class LruCache<K, V> {

    private HashMap<K, Node> cache;
    private int size;
    private int capacity;
    private Node mostRecentlyUsed;
    private Node leastRecentlyUsed;

    private class Node {
        K key;
        V value;
        Node previous;
        Node next;

        private Node() { }

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LruCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.cache = new HashMap<K, Node>();

        mostRecentlyUsed = new Node();
        leastRecentlyUsed = new Node();

        mostRecentlyUsed.previous = leastRecentlyUsed;
        leastRecentlyUsed.next = mostRecentlyUsed;
    }
    
	/**
	 * Adds a key and associated value to the cache.
	 * If the cache is full, remove the oldest entry.
	 * If the key already exists, update the value.
	 * 
	 * 1. Check if node exists
	 * 2. If cache is full...
	 * 	2.1 Remove the oldest node from the linked list
	 * 	2.2 Remove the value from the cache
	 * 	2.3 Decrement the size
	 * 3. Add a new node to the cache
	 * 4. Put the new node at the start of the linked list
	 * 5. Increment the size
	 * 6. If node already exists, update the value and put it at the front of the linked list
	 * 
	 * @param key The key to add
	 * @param value The value associated with this key
	 */
    public void add(K key, V value) {
        Node cachedNode = cache.get(key);
        
        if(cachedNode == null) {
            if(size >= capacity) {
                Node oldest = popLeastRecentlyUsed();
                cache.remove(oldest.key);
                size--;
            }
            
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            setMostRecentlyUsed(newNode);
            size++;
        } else {
            // Node already exists
            cachedNode.value = value;
	    removeNode(cachedNode);
            setMostRecentlyUsed(cachedNode);
        }
    }
    
	/**
	 * Removes the least recently used node from the linked list
	 * and returns it.
	 * 
	 * @return The least recently used Node
	 */
    private Node popLeastRecentlyUsed() {
        Node oldest = leastRecentlyUsed.next;
        removeNode(oldest);
        return oldest;
    }
    
    /**
	 * Removes a node from the linked list.
	 * 
	 * 1. Get the previous and next nodes
	 * 2. Reassign previous and next to point to each other
	 *    (thus removing all pointers to this node)
	 * 
	 * @param node The node to remove from the linked list
	 */
    private void removeNode(Node node) {
        Node previous = node.previous;
        Node next = node.next;

        previous.next = next;
        next.previous = previous;
    }   
    
    /**
	 * Sets the given node as the most recently used node.
	 * 
	 * 1. Get the old most recently used node
	 * 2. Set the given node's next and previous
	 * 3. Set the old most recently used node's next node
	 * 4. Set the most recently used node to point to the given node
	 * 
	 * @param node The node to set as most recently used
	 */
    private void setMostRecentlyUsed(Node node) {
        Node oldMostRecentlyUsed = mostRecentlyUsed.previous;
        
        node.previous = oldMostRecentlyUsed;
        node.next = mostRecentlyUsed;

        oldMostRecentlyUsed.next = node;
        
        mostRecentlyUsed.previous = node;
    }
    
    /**
	 * Returns the value associated with a given key,
	 * or null if the key does not exist.
	 * 
	 * 1. Check if key is the cache
	 * 2. Remove the node from its current position in the linked list
	 * 3. Add the node to the front (most recently used position) of the linked list
	 * 
	 * @param key The key to search for
	 * @return The value associated with the given key, or null if key does not exist
	 */
    public V get(K key) {
        Node cachedNode = cache.get(key);

        if(cachedNode == null) {
            return null;
        }
     
        removeNode(cachedNode);
        setMostRecentlyUsed(cachedNode);

        return cachedNode.value;
    }
}
