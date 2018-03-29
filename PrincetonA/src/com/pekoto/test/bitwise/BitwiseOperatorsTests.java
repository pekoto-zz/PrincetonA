package com.pekoto.test.bitwise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.bitwise.BitwiseOperators;

/**
 * Unit tests for the BitwiseOperators class.
 */
public class BitwiseOperatorsTests {

    BitwiseOperators bitwise = new BitwiseOperators();
    
    @Test
    public void testAnd() {
        assertEquals(4, bitwise.and(5, 6));
    }
    
    @Test
    public void testOr() {
        assertEquals(7, bitwise.or(5,  6));
    }
    
    @Test
    public void testXor() {
        assertEquals(3, bitwise.xor(5,  6));
    }
    
    @Test
    public void testNot() {
        assertEquals(-1, bitwise.not(0));
        assertEquals(-2, bitwise.not(1));
        assertEquals(-6, bitwise.not(5));
    }
}
