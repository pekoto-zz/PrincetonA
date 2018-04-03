package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.Knapsack;

/**
 * Unit tests for the Knapsack class.
 */
public class KnapsackTests {
    
    @Test
    public void testGetMaxValue() {
        int [] weights = {2, 4, 1, 4, 7};
        int [] values = {4, 2, 1, 5, 1};
        
        int maxValue = Knapsack.getMaxValue(weights, values, 8);
        
        assertEquals(10, maxValue);
    }
}
