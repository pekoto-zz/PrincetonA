package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.Itoa;

/**
 * Unit tests for the Itoa class.
 */
public class ItoaTests {

    @Test
    public void testItoa() {
       String str = Itoa.itoa(123);
       
       assertEquals("123", str);
    }
    
    @Test
    public void testNegativeNumber() {
        String str = Itoa.itoa(-5721);
        
        assertEquals("-5721", str);
    }
}
