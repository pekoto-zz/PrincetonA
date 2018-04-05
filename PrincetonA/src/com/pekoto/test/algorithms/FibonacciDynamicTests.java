package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.FibonacciDynamic;

public class FibonacciDynamicTests {

    @Test
    public void testGetFib() {
        int n = FibonacciDynamic.getFib(8);
        
        assertEquals(21, n);
    }
}
