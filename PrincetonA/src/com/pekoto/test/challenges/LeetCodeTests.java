package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.LeetCode;

/**
 *  Unit tests for the LeetCode class 
 */
public class LeetCodeTests {
    
    @Test
    public void testMedianSortedArrays() {
        int [] arr1 = {1, 3};
        int [] arr2 = {2};
        
        assertEquals(2.0, LeetCode.findMedianSortedArrays(arr1, arr2), 0.1);
    }
}
