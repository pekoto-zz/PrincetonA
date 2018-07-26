package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.LeetCode;

/**
 *  Unit tests for the LeetCode class 
 */
public class LeetCodeTests {
    
    @Test
    public void testLengthOfLongestSubstring() {
        String s = "abcbadcab";
        
        assertEquals(4, LeetCode.lengthOfLongestSubstring(s));
    }
    
    @Test
    public void testMedianSortedArrays() {
        int [] arr1 = {1, 2};
        int [] arr2 = {3, 4};
        
        assertEquals(2.5, LeetCode.findMedianSortedArrays(arr1, arr2), 0.1);
    }
}
