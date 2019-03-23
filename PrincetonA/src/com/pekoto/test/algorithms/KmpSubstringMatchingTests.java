package com.pekoto.test.algorithms;

import org.junit.Assert;
import org.junit.Test;

import com.pekoto.algorithms.KmpSubstringMatching;

public class KmpSubstringMatchingTests {

	@Test
	public void TestKmpSubstringMatching_True() {
		
		boolean result = KmpSubstringMatching.contains("ABCABCABY", "ABCABY");
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void TestKmpSubstringMatching_False() {
		boolean result = KmpSubstringMatching.contains("ABCABCAD", "ABCABY");
		
		Assert.assertFalse(result);		
	}
}
