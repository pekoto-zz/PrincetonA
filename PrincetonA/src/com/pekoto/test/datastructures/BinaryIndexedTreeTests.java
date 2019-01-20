package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.datastructures.BinaryIndexedTree;

public class BinaryIndexedTreeTests {

	@Test
	public void testBinaryIndexedTree() {
		BinaryIndexedTree bit = new BinaryIndexedTree(new int[] {1, 2, 3, 4, 5});
		
		bit.update(0, 2);
		
		assertEquals(4, bit.getSum(1));
		assertEquals(9, bit.sumRange(1, 3));
	}
}
