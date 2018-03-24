package com.pekoto.datastructures;

/**
 * A trie that is more memory efficient than an R-way Trie.
 * Instead of each node having an array of R children (R = radix size),
 * each node has 3 children:
 *  - Less than (char at this position is less than)
 *  - More than (char at this position is more than)
 *  - Equal (char at this position is the same)
 *  
 *  Kind of like a binary search tree, but for Strings
 *  
 *  Performance:
 *  Search hit: O(L+ln N) 
 *  Search miss: O(ln N)
 *  Insert: O(L+ln N)
 *  
 *  Space: O(N)
 *  N = number of strings, L = length of string
 *  
 *  In practical terms, works better than hashing, and is more flexible.
 *  But only works for Strings.
 *  
 *  Hashing:
 *   - Need to examine entire key
 *   - Search hits and misses cost the same
 *   - Performance relies on hash function
 *   - Does not support ordered operations
 *   
 *  TSTs:
 *   - Only works for strings
 *   - Only has to examine just enough key characters
 *   - Supports ordered operations + more!
 *  
 *  Uses:
 *   - Like standard tries, fast string searching
 *   - Spell checkings, etc.
 *   
 * @param <T> The type to associate with each String
 * 
 */
public class TernarySearchTrie<T> {

    private Node root;
    
    private class Node {
        private T value;
        private char chr;
        
        private Node left;
        private Node mid;
        private Node right;
    }
    
    /**
     * Adds a key and associated value to the trie recursively.
     * 
     *  1. Set the root by calling the recursive method
     *  2. Get the next character in the key
     *  3. If the node is null, create a new node and set its character
     *     to be the next character
     *  4. If the char is < current node's char, move left
     *  5. If the char is > current node's char, move right
     *  6. If we're not at the end of the key yet, move down the middle, looking at the next char in the key
     *  7. Finally, if we've reached the end of the key, update the associated value
     * 
     * @param key The key to add to the trie
     * @param value The value to associate with this trie
     */
    public void add (String key, T value) {
        root = add(root, key, value, 0);
    }
    
    private Node add(Node node, String key, T value, int charIndex) {

        char nextChar = key.charAt(charIndex);
        
        // New node -- add this char
        if(node == null) {
            node = new Node();
            node.chr = nextChar;
        }
        
        if(nextChar < node.chr) {
            // Node exists, but this char is less than node's char
            node.left = add(node.left, key, value, charIndex);
        } else if (nextChar > node.chr) {
            // Node exists, but this char is more than node's char
            node.right = add(node.right, key, value, charIndex);
        } else if (charIndex < key.length() - 1) {
            // Still processing key chars, char values are the same.
            // Move to next char.
            node.mid = add(node.mid, key, value, charIndex+1);
        } else {
            // Reached the end of the key, set/update the value
            node.value = value;
        }
        
        return node;
    }
    
    /**
     * Returns the value associated with a given key,
     * or null if the value is not found.
     * 
     * The key is not found if:
     * 1. We reach a null node
     * 2. The value associated with the terminating node is null
     * 
     * 1. Get the node with this key by searching from the root and first
     *    char of the key recursively
     * 2. If the node is null, return null
     * 3. If we've reached the end of our key, return the node
     * 4. Get the next char
     * 5. If the char is < current node's char, move left
     * 6. If the char is > current node's char, move right
     * 7. Otherwise move down the middle and look at the next char in the key
     * 
     * @param key The key to search for in the trie
     * @return The value associated with the key, or null if the value is not found
     */
    public T get(String key) {
        Node node = get(root, key, 0);
        
        if(node == null) {
            return null;
        } else {
            return node.value;
        }
    }
    
    private Node get(Node node, String key, int charIndex) {
        if(node == null) {
            return null;
        }
        
        if(charIndex == key.length()-1) {
            return node;
        }
        
        char nextChar = key.charAt(charIndex);
        
        if(nextChar < node.chr) {
            return get(node.left, key, charIndex);
        } else if (nextChar > node.chr) {
            return get(node.right, key, charIndex);
        } else {
            return get(node.mid, key, charIndex+1);
        }
    }
}
