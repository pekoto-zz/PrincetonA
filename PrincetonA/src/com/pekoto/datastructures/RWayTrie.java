package com.pekoto.datastructures;

/**
 * A basic trie data structure, where each node contains R (size of radix) children.
 * The trie is made up of nodes. Each node represents a character, and has R children,
 * where each child is another character. Strings are inserted one character at a time,
 * with each character being added as a new node as required.
 * 
 * The terminating character of a string is marked by an inserted value.
 * 
 * So, when searching, we just searching, we just move down the tree until...
 *  - We hit a null link (node has no non-null children)
 *  - The char we are looking for does not exist as a child from the current node
 * 
 * Performance:
 * - Put: O(n)
 * - Search: success=O(n), miss=O(n) worst case, but typically sublinear
 *                         (since we will likely mismatch before having checked every character)
 * - Search performance is thus likely to be better than a hashmap, since we don't have to
 *   compare every char.
 *   
 * Memory:
 * - The major downside of the trie is that it takes up a lot of memory,
 *   since every node has R children.
 * 
 * Uses:
 *  - Spell checker
 *  - Quick searching for items using a small radix
 * 
 * Note: From "retrieve", but pronounced "try" to differentiate it from "tree".
 * 
 * @param <T> The type to associate with each String
 */
public class RWayTrie<T> {

    private Node root;
    
    // Num of chars in extended ASCII
    private static final int DEFAULT_RADIX_SIZE = 256;
    private int radixSize;
    
    private static class Node {
        // Use object since no generic array creation allowed
        private Object value;
        private Node[] children;
        
        private Node(int radixSize) {
            children = new Node[radixSize];
        }
    }
    
    public RWayTrie() {
        this.radixSize = DEFAULT_RADIX_SIZE;
    }
    
    public RWayTrie(int radixSize) {
        this.radixSize = radixSize;
    }
    
    /**
     * Adds a string key to the trie and associates it with a value.
     * 
     * 1. Set the root via a recursive call...
     * 2. If the node is null, create a new node
     * 3. If the char index is off the end of the string,
     *    set the node value to be the given value and return the node
     * 4. Otherwise, get the next char to add to the trie...
     * 5. Add the next char to the child of this node at index [nextChar]
     *    using a recursive call
     * 
     * Note: We don't actually store the chars of the string.
     *       Those are "stored" as the child index.
     * 
     * @param key The key to add into the trie
     * @param value The value associated with this key
     */
    public void add(String key, T value) {
        root = add(root, key, value, 0);
    }
    
    private Node add(Node node, String key, T value, int charIndex) {
        if(node == null) {
            node = new Node(this.radixSize);
        }
        
        // We reached the end of our key
        if(charIndex == key.length()) {
            node.value = value;
            return node;
        }
        
        // Think of it as:
        //  node.children = go down to the next node in the trie
        //  charIndex+1 = look at the next node in the key
        // So we step down the trie and iterate along our key at the same time
        char nextChar = key.charAt(charIndex);
        node.children[nextChar] = add(node.children[nextChar], key, value, charIndex+1);
        
        return node;
    }
    
    /**
     * Returns the value associated with a key in the trie,
     * or null if the key could not be found.
     * 
     * There are three situations where our key was not found:
     * 1. We reach a null child when iterating over our key chars
     * 2. We reach the end of the trie (again, a null node)
     * 3. We find out string in the trie, but it's not marked
     *    as a key (has no associated value). In this case null be
     *    returned
     * 
     * 1. Get the value by doing a recursive call from the root...
     * 2. If the node is null, return null
     * 3. If the char index is off the end of the key, return the node
     * 4. Otherwise, get the next char
     * 5. Recurse for the node's child at index [nextxChar], looking
     *    at the next char.
     * 
     * @param key The key to search for in the trie
     * @return The value associated with this key, or null if the key could not be found
     */
    public T get(String key) {
        Node node = get(root, key, 0);
        
        if(node == null) {
            return null;
        } else {
            // Cast due to lack of generic array creation (See Node class)
            // (If the key was found, but it has no associated value -- was never inserted
            //  as a key -- then null will be returned, so this works)
            return (T)node.value;
        }
    }
    
    private Node get(Node node, String key, int charIndex) {
        if(node == null) {
            return null;
        }
        
        // We finished searching the length of our key
        if(charIndex == key.length()) {
            return node;
        }
        
        char nextChar = key.charAt(charIndex);
        
        // Think of it as:
        //  node.children = go down to the next node in the trie
        //  charIndex+1 = look at the next node in the key
        // So we step down the trie and iterate along our key at the same time
        return get(node.children[nextChar], key, charIndex+1);
    }
}
