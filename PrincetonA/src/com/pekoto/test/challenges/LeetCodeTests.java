package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
    
    @Test
    public void testMySqrt() {
        assertEquals(2, LeetCode.mySqrt(8));
        assertEquals(3, LeetCode.mySqrt(9));
    }
    
    @Test
    public void testIsMatch() {
        assertTrue(LeetCode.isMatch("aaa", "a*"));
    }
    
    @Test
    public void testMerge() {
        
        List<Interval> intervals = new ArrayList<Interval>();
        
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(0, 2));
        intervals.add(new Interval(3, 5));
        
        List<Interval> merged = LeetCode.merge(intervals);
        
        assertTrue(true);
    }
    
    @Test
    public void testFastPow() {
        assertEquals(16.0, LeetCode.myPow(2.0, 4), 0.1);
        assertEquals(0.25, LeetCode.myPow(2.0, -2), 0.1);
    }
    
    @Test
    public void testRemoveDuplicateLetters() {
        assertEquals("bac", LeetCode.removeDuplicateLetters("bbacac"));
    }
}
