package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.FibonacciMemoization;

/**
 * Unit tests for the FibonacciMemoization class.
 */
public class FibonacciMemoizationTests {

    @Test
    public void testGetFib() {
        FibonacciMemoization fib = new FibonacciMemoization();
        
        assertEquals(5, fib.getFib(5));
    }
}
