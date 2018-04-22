package com.pekoto.test.challenges;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pekoto.challenges.ArraysAndStrings;

/**
 * Unit tests for the ArraysAndStrings class.
 */
public class ArraysAndStringsTests {

    @Test
    public void testAreAllCharsUnique_True() {
        assertTrue(ArraysAndStrings.areAllCharsUnique("ABCD"));
    }
    
    @Test
    public void testAreAllCharsUnique_False() {
        assertFalse(ArraysAndStrings.areAllCharsUnique("ABCA"));        
    }
    
    @Test
    public void testArePermutations_True() {
        assertTrue(ArraysAndStrings.arePermutations("ABCD", "CBAD"));
    }
    
    @Test
    public void testArePermutations_False() {
        assertFalse(ArraysAndStrings.arePermutations("ABCD", "ABCZ"));
    }
}
