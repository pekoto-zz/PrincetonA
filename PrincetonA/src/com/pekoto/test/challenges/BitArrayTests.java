package com.pekoto.test.challenges;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pekoto.challenges.BitArray;

public class BitArrayTests {

    @Test
    public void testBitArray() {
        BitArray arr = new BitArray(1000);
        
        arr.put(10);
        arr.put(5);
        arr.put(64);
        arr.put(78);
        arr.put(99);
        
        assertTrue(arr.contains(10));
        assertTrue(arr.contains(5));
        assertTrue(arr.contains(64));
        assertTrue(arr.contains(78));
        assertTrue(arr.contains(99));
        assertFalse(arr.contains(256));
    }
}
