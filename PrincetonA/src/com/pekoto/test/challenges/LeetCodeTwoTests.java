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
	
}
