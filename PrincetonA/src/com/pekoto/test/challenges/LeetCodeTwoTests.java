package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
	
	@Test
	public void testMinManhattanDistance() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		int [][] grid = new int[2][2];
		List<int[]> points = new ArrayList<>();
		
		points.add(new int[] {0,0});
		points.add(new int[] {0,2});
		points.add(new int[] {2,2});
		
		int[] result = lct.minManhattanDistance(grid, points);
		
		assertEquals(0, result[0]);
		assertEquals(2, result[1]);
	}

	@Test
	public void testGetDuplicatePermutations() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		int [] arr = {1, 1, 2, 3};
		
		List<int[]> result = lct.getDuplicatePermutations(arr);
		
		assertEquals(12, result.size());
	}
	
	@Test
	public void testCandyCrush()  {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		int[][] board = {
				{110,5,112,113,114},
				{210,211,5,213,214},
				{310,311,3,313,314},
				{410,411,412,5,414},
				{5,1,512,3,3},
				{610,4,1,613,614},
				{710,1,2,713,714},
				{810,1,2,1,1},
				{1,1,2,2,2},
				{4,1,4,4,1014}
		};
		
		lct.candyCrush(board);
		
		System.out.println();
	}
	
	public void testGetAsManyMeetings() {
		LeetCodeTwo lct = new LeetCodeTwo();
		
		int[] meetings = {5, 2, 3};
		
		List<Integer> result = lct.getAsManyMeetings(meetings, 8);
		
		assertEquals(2, result.size());
		assertEquals(Integer.valueOf(3), result.get(0));
		assertEquals(Integer.valueOf(5), result.get(1));
	}
}
