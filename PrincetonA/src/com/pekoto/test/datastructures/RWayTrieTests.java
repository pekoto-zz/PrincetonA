package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.datastructures.RWayTrie;

/**
 * Unit tests for he RWayTrie class.
 */
public class RWayTrieTests {

    @Test
    public void testGet() {
        RWayTrie<Integer> trie = new RWayTrie<Integer>();
        
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
        RWayTrie<Integer> trie = new RWayTrie<Integer>();
        
        trie.add("hello", 0);
        trie.add("heck", 1);
        trie.add("hell", 2);
        trie.add("cat", 3);
        
        assertNull(trie.get("he"));
        assertNull(trie.get("doesn't exist"));
    }
    
    @Test
    public void testKeys() {
        RWayTrie<Integer> trie = new RWayTrie<Integer>();
        
        trie.add("hello", 0);
        trie.add("heck", 1);
        trie.add("hell", 2);
        trie.add("cat", 3);
        
        assertEquals(Integer.valueOf(0), trie.get("hello"));
        assertEquals(Integer.valueOf(1), trie.get("heck"));
        assertEquals(Integer.valueOf(2), trie.get("hell"));
        assertEquals(Integer.valueOf(3), trie.get("cat"));
        
        ArrayList<String> keys = new ArrayList<String>();
        
        for(String key : trie.keys()) {
            keys.add(key);
        }
        
        assertEquals(4, keys.size());
        assertTrue(keys.contains("hello"));
        assertTrue(keys.contains("heck"));
        assertTrue(keys.contains("hell"));
        assertTrue(keys.contains("cat"));
    }
    
    @Test
    public void testGetPrefix() {
        RWayTrie<Integer> trie = new RWayTrie<Integer>();
        
        trie.add("hello", 0);
        trie.add("heck", 1);
        trie.add("hell", 2);
        trie.add("cat", 3);
        
        assertEquals(Integer.valueOf(0), trie.get("hello"));
        assertEquals(Integer.valueOf(1), trie.get("heck"));
        assertEquals(Integer.valueOf(2), trie.get("hell"));
        assertEquals(Integer.valueOf(3), trie.get("cat"));
        
        ArrayList<String> keys = new ArrayList<String>();
        
        for(String key : trie.keysWithPrefix("he")) {
            keys.add(key);
        }
        
        assertEquals(3, keys.size());
        assertTrue(keys.contains("hello"));
        assertTrue(keys.contains("heck"));
        assertTrue(keys.contains("hell"));
    }
    
    @Test
    public void testLongestPrefix() {
        RWayTrie<Integer> trie = new RWayTrie<Integer>();
        
        trie.add("Sea", 0);
        trie.add("Shell", 1);
        trie.add("Shells", 2);
        trie.add("Sell", 3);
        trie.add("By", 3);
        trie.add("Seashore", 3);
        
        assertEquals("Shells", trie.longestPrefix("Shellsort"));
    }
}
