package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.LongestCommonSubsequence;

/**
 * Unit tests for the LongestCommonSubsequence class.
 */
public class LongestCommonSubsequenceTests {

    @Test
    public void testGetLongestCommonSubsequence() {
        int longestSubsequence = LongestCommonSubsequence.getLongestCommonSubsequence("BATD", "ABACD");
    
        assertEquals(3, longestSubsequence);
    }
    
    @Test
    public void testNoLongestCommonSubsequence() {
        int longestSubsequence = LongestCommonSubsequence.getLongestCommonSubsequence("AAA", "BB");
        
        assertEquals(0, longestSubsequence);        
    }
}
