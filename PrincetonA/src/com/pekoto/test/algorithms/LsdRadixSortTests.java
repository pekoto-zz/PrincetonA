package com.pekoto.test.algorithms;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pekoto.algorithms.LsdRadixSort;

/**
 * Unit tests for the LsdRadixSort class.
 */
public class LsdRadixSortTests {
	
    @Test
	public void testSorts() {
		
		String [] arr = {
			"abe",
			"ace",
			"aba",
			"bbd",
			"gzi",
			"bad",
			"zap",
			"oia"
		};
		
		LsdRadixSort.sort(arr, 3);
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		for(int i = 1; i < arr.length; i++) {
			assertTrue(arr[i-1].compareTo(arr[i]) <= 0);
		}
	}
}
