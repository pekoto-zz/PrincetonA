package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.pekoto.datastructures.TernarySearchTrie;

/**
 * Tests for the TernarySearchTree class.
 */
public class TernarySearchTrieTests {

    @Test
    public void testGet() {
        TernarySearchTrie<Integer> trie = new TernarySearchTrie<Integer>();
        
        trie.add("hello", 0);
        trie.add("heck", 1);
        trie.add("hell", 2);
        trie.add("cat", 3);
        
        assertEquals(Integer.valueOf(0), trie.get("hello"));
        assertEquals(Integer.valueOf(1), trie.get("heck"));
        assertEquals(Integer.valueOf(2), trie.get("hell"));
        assertEquals(Integer.valueOf(3), trie.get("cat"));
    }
    
    @Test
    public void testGet_NotFound() {
        TernarySearchTrie<Integer> trie = new TernarySearchTrie<Integer>();
        
        trie.add("hello", 0);
        trie.add("heck", 1);
        trie.add("hell", 2);
        trie.add("cat", 3);
        
        assertNull(trie.get("he"));
        assertNull(trie.get("doesn't exist"));
    }
}
