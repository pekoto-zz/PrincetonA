package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pekoto.datastructures.HashMapWithChaining;

/** 
 * Unit tests for the HashMapWithChaining class
 */
public class HashMapWithChainingTests {

    @Test
    public void testPut() {
        HashMapWithChaining<Integer, String> hashMap = new HashMapWithChaining<Integer, String>();
        
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        
        assertEquals("One", hashMap.get(1));
        assertEquals("Two", hashMap.get(2));
        assertEquals("Three", hashMap.get(3));
    }
    
    @Test
    public void testPutUpdateKey() {
        HashMapWithChaining<Integer, String> hashMap = new HashMapWithChaining<Integer, String>();
        
        hashMap.put(2, "Two");
        hashMap.put(2,  "2");
        
        assertEquals("2", hashMap.get(2));
    }
    
    @Test
    public void testChaining() {
        HashMapWithChaining<String, String> hashMap = new HashMapWithChaining<String, String>(3);

        hashMap.put("Aa", "Aa");
        hashMap.put("BB", "BB");
        hashMap.put("Cc", "Cc");
        hashMap.put("DD", "DD");
        
        assertEquals("Aa", hashMap.get("Aa"));
        assertEquals("BB", hashMap.get("BB"));
        assertEquals("Cc", hashMap.get("Cc"));
        assertEquals("DD", hashMap.get("DD"));
    }
    
    @Test
    public void testGetKeyDoesNotExist() {
        HashMapWithChaining<Integer, String> hashMap = new HashMapWithChaining<Integer, String>();
        
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        
        assertNull(hashMap.get(5));
    }
    
    @Test
    public void testSize() {
        HashMapWithChaining<Integer, String> hashMap = new HashMapWithChaining<Integer, String>();
        
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        
        assertEquals(3, hashMap.size());
    }
    
    @Test
    public void testContains() {
        HashMapWithChaining<Integer, String> hashMap = new HashMapWithChaining<Integer, String>();
        
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        
        assertTrue(hashMap.contains(1));
        assertFalse(hashMap.contains(5));
    }
    
    @Test
    public void testDeleteOnlyNodeInChain() {
        HashMapWithChaining<Integer, String> hashMap = new HashMapWithChaining<Integer, String>();
        
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        
        hashMap.delete(2);
        
        assertNull(hashMap.get(2));
        assertEquals(2, hashMap.size());
    }
    
    @Test
    public void testDeleteLastNodeInChain() {
        
    }
    
    @Test
    public void testDeleteFirstNodeInChain() {
        
    }
    
    @Test
    public void testDeleteMiddleNodeInChain() {
        
    }
}
