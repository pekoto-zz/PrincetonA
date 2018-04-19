package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.pekoto.datastructures.LruCache;

/**
 * Unit tests for the LruCache class.
 */
public class LruCacheTests {

    @Test
    public void testAddGet() {
        LruCache<Integer, Integer> lruCache = new LruCache<Integer, Integer>(4);
    
        lruCache.add(1, 1);
        lruCache.add(2, 2);
        lruCache.add(3, 3);
        lruCache.add(4, 4);
    
        assertEquals(Integer.valueOf(1), lruCache.get(1));
        assertEquals(Integer.valueOf(2), lruCache.get(2));
        assertEquals(Integer.valueOf(3), lruCache.get(3));
        assertEquals(Integer.valueOf(4), lruCache.get(4));
    }
    
    @Test
    public void testGet_DoesNotExist() {
        LruCache<Integer, Integer> lruCache = new LruCache<Integer, Integer>(4);
        
        lruCache.add(1, 1);
        lruCache.add(2, 2);
        lruCache.add(3, 3);
        lruCache.add(4, 4);
    
        assertNull(lruCache.get(5));
    }
    
    @Test
    public void testLeastRecentlyUsedRemoved() {
        LruCache<Integer, Integer> lruCache = new LruCache<Integer, Integer>(4);
        
        lruCache.add(1, 1);
        lruCache.add(2, 2);
        lruCache.add(3, 3);
        lruCache.add(4, 4);
        lruCache.add(5, 5); // Replaces 1,1 as least recently used
        
        assertNull(lruCache.get(1));
        assertEquals(Integer.valueOf(2), lruCache.get(2));
        assertEquals(Integer.valueOf(3), lruCache.get(3));
        assertEquals(Integer.valueOf(4), lruCache.get(4));
        assertEquals(Integer.valueOf(5), lruCache.get(5));
    }
    
    @Test
    public void testUpdateValue() {
        LruCache<Integer, Integer> lruCache = new LruCache<Integer, Integer>(4);
        
        lruCache.add(1, 1);
        lruCache.add(2, 2);
        lruCache.add(3, 3);
        lruCache.add(4, 4);
        lruCache.add(2, 22);
        
        assertEquals(Integer.valueOf(1), lruCache.get(1));
        assertEquals(Integer.valueOf(22), lruCache.get(2));
        assertEquals(Integer.valueOf(3), lruCache.get(3));
        assertEquals(Integer.valueOf(4), lruCache.get(4));
    }
    
    @Test
    public void testLeastRecentlyUsedUpdated() {
        LruCache<Integer, Integer> lruCache = new LruCache<Integer, Integer>(4);
        
        lruCache.add(1, 1);
        lruCache.add(2, 2);
        lruCache.add(3, 3);
        lruCache.add(4, 4);
        
        // Update most recently used
        lruCache.get(1);
        
        // Should now remove 2
        lruCache.add(5, 5);
        
        assertEquals(Integer.valueOf(1), lruCache.get(1));
        assertNull(lruCache.get(2));
        assertEquals(Integer.valueOf(3), lruCache.get(3));
        assertEquals(Integer.valueOf(4), lruCache.get(4));
        assertEquals(Integer.valueOf(5), lruCache.get(5));
    }
}
