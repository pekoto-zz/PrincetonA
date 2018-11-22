package com.pekoto.test.algorithms;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*
 * Unit tests for the UnionFind class
 */
public class UnionFindTests {

	@Test
	public void testSingleUnion_Connected() {
		UnionFind uf = new UnionFind(10);

		uf.union(1, 2);

		assertTrue(uf.areConnected(1, 2));
	}

	@Test
	public void testSingleUnion_NotConnected() {
		UnionFind uf = new UnionFind(10);

		uf.union(1, 2);

		assertFalse(uf.areConnected(1, 3));
	}

	@Test
	public void testMultipleUnion_Connected() {
		UnionFind uf = new UnionFind(10);

		// 3-4
		uf.union(3, 4);

		// 3-4 8-7
		uf.union(8, 7);

		// 3-4 8-7-9
		uf.union(7, 9);

		// 3-4-8-7-9
		uf.union(3, 9);

		assertTrue(uf.areConnected(3, 4));
		assertTrue(uf.areConnected(3, 7));
		assertTrue(uf.areConnected(3, 8));
		assertTrue(uf.areConnected(3, 9));

		assertTrue(uf.areConnected(4, 3));
		assertTrue(uf.areConnected(4, 7));
		assertTrue(uf.areConnected(4, 8));
		assertTrue(uf.areConnected(4, 9));

		assertTrue(uf.areConnected(7, 3));
		assertTrue(uf.areConnected(7, 4));
		assertTrue(uf.areConnected(7, 8));
		assertTrue(uf.areConnected(7, 9));

		assertTrue(uf.areConnected(8, 4));
		assertTrue(uf.areConnected(8, 7));
		assertTrue(uf.areConnected(8, 3));
		assertTrue(uf.areConnected(8, 9));

		assertTrue(uf.areConnected(9, 4));
		assertTrue(uf.areConnected(9, 7));
		assertTrue(uf.areConnected(9, 8));
		assertTrue(uf.areConnected(9, 3));
	}

	@Test
	public void testMultipleUnion_NotConnected() {
		UnionFind uf = new UnionFind(10);

		// 3-4
		uf.union(3, 4);

		// 3-4 8-7
		uf.union(8, 7);

		// 3-4 8-7-9
		uf.union(7, 9);

		// 3-4-8-7-9
		uf.union(3, 9);

		assertFalse(uf.areConnected(1, 3));
		assertFalse(uf.areConnected(3, 1));

		assertFalse(uf.areConnected(3, 5));
		assertFalse(uf.areConnected(4, 5));
		assertFalse(uf.areConnected(0, 9));
		assertFalse(uf.areConnected(8, 0));
	}
}
