package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

import com.pekoto.challenges.LeetCodeTwo;

public class LeetCodeTwoTests {

	@Test
	public void testMinSwapsToSort() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		int[] nums = {0, 2, 1, 3};
		
		assertEquals(1, lct.minSwapsToSort(nums));
	}
	
	@Test
	public void testMinSwapsCouples() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		int[] nums = {0, 2, 1, 3};
		
		assertEquals(1, lct.minSwapsCouples(nums));
	}
	
	@Test
	public void testBashPermutations() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		String s = "abc{d,e}f{gh,ij}";
		
		assertEquals(4, lct.bashPermutations(s).size());
	}
	
	@Test
	public void testMergeSortedLists_ArrayList() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		ArrayList<Integer> one = new ArrayList<Integer>(Arrays.asList(new Integer[] {1, 4, 5, 7}));
		ArrayList<Integer> two = new ArrayList<Integer>(Arrays.asList(new Integer[] {0}));
		
		ArrayList<Integer> mergedList = lct.mergeSortedLists(one, two);
		
		assertEquals(Integer.valueOf(0), mergedList.get(0));
		assertEquals(Integer.valueOf(1), mergedList.get(1));
		assertEquals(Integer.valueOf(4), mergedList.get(2));
		assertEquals(Integer.valueOf(5), mergedList.get(3));
		assertEquals(Integer.valueOf(7), mergedList.get(4));
	}
	
	@Test
	public void testMergeSortedLists_LinkedList() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		LinkedList<Integer> one = new LinkedList<Integer>(Arrays.asList(new Integer[] {1, 10}));
		LinkedList<Integer> two = new LinkedList<Integer>(Arrays.asList(new Integer[] {0, 6, 11, 13}));
		
		ArrayList<Integer> mergedList = lct.mergeSortedLists(one, two);
		
		assertEquals(Integer.valueOf(0), mergedList.get(0));
		assertEquals(Integer.valueOf(1), mergedList.get(1));
		assertEquals(Integer.valueOf(6), mergedList.get(2));
		assertEquals(Integer.valueOf(10), mergedList.get(3));
		assertEquals(Integer.valueOf(11), mergedList.get(4));
		assertEquals(Integer.valueOf(13), mergedList.get(5));
	}

}
