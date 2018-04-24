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
    
    @Test
    public void testOneAway() {
        assertTrue(ArraysAndStrings.oneAway("pale", "ple"));
        assertTrue(ArraysAndStrings.oneAway("pales", "pale"));
        assertTrue(ArraysAndStrings.oneAway("pale", "bale"));
        assertFalse(ArraysAndStrings.oneAway("pale", "bake"));
    }
    
    @Test
	public void testZeroOut() {
		int [][] matrix = {
				{1,  2,  3,  4},
				{5,  6,  7,  8},
				{9,  10, 0,  12},
				{13, 0,  15, 16},
				{17, 18, 19, 20}
		};
		
		StringsAndArrays.zeroOut(matrix);
		
		// Expected:
		// 1  0 0 4
		// 5  0 0 8
		// 0  0 0 0
		// 0  0 0 0
		// 17 0 0 20
		
		assertEquals(1, matrix[0][0]);
		assertEquals(0, matrix[0][1]);
		assertEquals(0, matrix[0][2]);
		assertEquals(4, matrix[0][3]);
		
		assertEquals(5, matrix[1][0]);
		assertEquals(0, matrix[1][1]);
		assertEquals(0, matrix[1][2]);
		assertEquals(8, matrix[1][3]);
		
		assertEquals(0, matrix[2][0]);
		assertEquals(0, matrix[2][1]);
		assertEquals(0, matrix[2][2]);
		assertEquals(0, matrix[2][3]);
		
		assertEquals(0, matrix[3][0]);
		assertEquals(0, matrix[3][1]);
		assertEquals(0, matrix[3][2]);
		assertEquals(0, matrix[3][3]);
		
		assertEquals(17, matrix[4][0]);
		assertEquals(0, matrix[4][1]);
		assertEquals(0, matrix[4][2]);
		assertEquals(20, matrix[4][3]);
	}
	
	@Test
	public void testZeroOut_ZeroInFirstRow() {
		int [][] matrix = {
				{1,  2,  3,  0},
				{5,  6,  7,  8},
				{9,  10, 0,  12},
				{13, 0,  15, 16},
				{17, 18, 19, 20}
		};
		
		StringsAndArrays.zeroOut(matrix);
		
		// Expected:
		// 0  0 0 0
		// 5  0 0 0
		// 0  0 0 0
		// 0  0 0 0
		// 17 0 0 0
		
		assertEquals(0, matrix[0][0]);
		assertEquals(0, matrix[0][1]);
		assertEquals(0, matrix[0][2]);
		assertEquals(0, matrix[0][3]);
		
		assertEquals(5, matrix[1][0]);
		assertEquals(0, matrix[1][1]);
		assertEquals(0, matrix[1][2]);
		assertEquals(0, matrix[1][3]);
		
		assertEquals(0, matrix[2][0]);
		assertEquals(0, matrix[2][1]);
		assertEquals(0, matrix[2][2]);
		assertEquals(0, matrix[2][3]);
		
		assertEquals(0, matrix[3][0]);
		assertEquals(0, matrix[3][1]);
		assertEquals(0, matrix[3][2]);
		assertEquals(0, matrix[3][3]);
		
		assertEquals(17, matrix[4][0]);
		assertEquals(0, matrix[4][1]);
		assertEquals(0, matrix[4][2]);
		assertEquals(0, matrix[4][3]);
	}
	
	@Test
	public void testZeroOut_ZeroInFirstCol() {
		int [][] matrix = {
				{1,  2,  3,  4},
				{5,  6,  7,  8},
				{9,  10, 0,  12},
				{13, 0,  15, 16},
				{0, 18, 19, 20}
		};
		
		StringsAndArrays.zeroOut(matrix);
		
		// Expected:
		// 0 0 0 4
		// 0 0 0 8
		// 0 0 0 0
		// 0 0 0 0
		// 0 0 0 0
		
		assertEquals(0, matrix[0][0]);
		assertEquals(0, matrix[0][1]);
		assertEquals(0, matrix[0][2]);
		assertEquals(4, matrix[0][3]);
		
		assertEquals(0, matrix[1][0]);
		assertEquals(0, matrix[1][1]);
		assertEquals(0, matrix[1][2]);
		assertEquals(8, matrix[1][3]);
		
		assertEquals(0, matrix[2][0]);
		assertEquals(0, matrix[2][1]);
		assertEquals(0, matrix[2][2]);
		assertEquals(0, matrix[2][3]);
		
		assertEquals(0, matrix[3][0]);
		assertEquals(0, matrix[3][1]);
		assertEquals(0, matrix[3][2]);
		assertEquals(0, matrix[3][3]);
		
		assertEquals(0, matrix[4][0]);
		assertEquals(0, matrix[4][1]);
		assertEquals(0, matrix[4][2]);
		assertEquals(0, matrix[4][3]);
	}
}
