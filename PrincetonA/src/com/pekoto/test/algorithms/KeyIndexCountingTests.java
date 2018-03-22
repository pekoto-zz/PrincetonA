package com.pekoto.test.algorithms;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pekoto.algorithms.KeyIndexCounting;

/*
 * Unit tests for the KeyIndexCounting class
 */
public class KeyIndexCountingTests {

    @Test
    public void testSort() {
        int [] arr = {9, 8, 3, 3, 8, 9, 1, 1, 0, 7, 9, 2};
        
        KeyIndexCounting.sort(arr);
        
        for(int i : arr) {
            System.out.println(" " +i);
        }
        
        for(int i = 1; i < arr.length; i++) {
            assertTrue(arr[i-1] <= arr[i]);
        }
    }
}
