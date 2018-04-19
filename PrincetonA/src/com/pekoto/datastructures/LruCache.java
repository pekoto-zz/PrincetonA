package com.pekoto.datastructures;

import java.util.HashMap;

/**
 * A least recently used (LRU) cache implementation.
 * 
 * The LRU cache allows objects to be inserted and
 * retrieved in constant time.
 * 
 * If the cache becomes full, the least recently used item is overwritten.
 * When an item is retrieved or put, it becomes the most recently used item.
 * 
 * To get and put in constant time, we use a HashMap.
 * To check if an item is the most/least recently used, we use a doubly-linked list.
 *
 * @param <K> The key type
 * @param <V> The value type
 */
public class LruCache<K, V> {

    private HashMap<K, Node> cache;
    private int size;
    private int capacity;
    private Node head;  // mostRecentlyUsed?
    private Node tail;  // leaseRecentlyUsed?

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

        head = new Node();
        head.previous = null;

        tail = new Node();
        tail.next = null;

        head.next = tail;
        tail.previous = head;
    }

    public V get(K key) {
        Node cachedNode = cache.get(key);

        if(cachedNode == null) {
            return null;
        }

        moveToHead(cachedNode); // setMostRecentlyUsed

        return cachedNode.value;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private void removeNode(Node node) {
        Node previous = node.previous;
        Node next = node.next;

        previous.next = next;
        next.previous = previous;
    }

    // Add to head (most recently used?)
    private void addNode(Node node) {
        node.previous = head;
        node.next = head.next;

        head.next.previous = node;
        head.next = node;
    }

    public void add(K key, V value) {
        Node cachedNode = cache.get(key);
        
        if(cachedNode == null) {

            if(size >= capacity) {
                // Pop the tail
                Node tail = popTail();
                cache.remove(tail.key);
                size--;
            }
            
            Node newNode = new Node(key, value);
            
            cache.put(key, newNode);
            
            addNode(newNode);
            
            size++;
            
        } else {
            // Node already exists
            cachedNode.value = value;
            moveToHead(cachedNode);
        }
    }

    private Node popTail() {
        // What does res stand for??
        Node res = tail.previous;
        removeNode(res);
        return res;
    }
}
