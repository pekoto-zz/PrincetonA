package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

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
}
