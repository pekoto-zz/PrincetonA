package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.RabinKarpSubstringMatching;

public class RabinKarpSubstringMatchingTests {

	@Test
	public void testSearch_Found() {
		
		RabinKarpSubstringMatching rk = new RabinKarpSubstringMatching("ABCABY");
		int index = rk.search("ABCABDABIDABCABYABCDEH");
		
		assertEquals(10, index);
	}
	
	@Test
	public void testSearch_NotFound() {
		RabinKarpSubstringMatching rk = new RabinKarpSubstringMatching("ABCABY");
		int index = rk.search("ABCABDABIDABCABUABCDEH");
		
		assertEquals(-1, index);
	}
}
