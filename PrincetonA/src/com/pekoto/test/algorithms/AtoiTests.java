package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.Atoi;

/**
 * Unit tests for the Atoi class.
 */
public class AtoiTests {

    @Test
    public void testAtoi() {
        int i = Atoi.atoi("123");
        
        assertEquals(123, i);
    }
    
    @Test
    public void testAtoi_LongNumber() {
        int i = Atoi.atoi("512391223");
        
        assertEquals(512391223, i);
    }
}
