package com.pekoto.algorithms;

/*
 * A naive union-find operation. Can be used to work out connected components.
 * 
 * Union: O(n)
 * Find: O(n)
 * (Both can be improved to log n)
 * 
 * If you have n union operations, this could end up as a O(n^2) algorithm, which is not good.
 * 
 * To optimize union-find to log n, we can make the tree smaller with two optimizations:
 * 1. Weighting: Hold a weight array and always merge the smaller tree into the larger one
 * 2. Path compression: When finding the root, set each node you traverse to point to the root
 *                      (need another loop)
 * 
 */
public class UnionFind {

	private int[] parent;
	
	public UnionFind(int size) {
		parent = new int[size];
		
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}
	
	/*
	 * Connects a and b.
	 * E.g.
	 *  0  1  2  3  4
	 * [0, 3, 2, 3, 4] (1's parent is 3)
	 * 
	 * > union 3 and 4
	 * 
	 *  0  1  2  3  4
	 * [0, 3, 2, 4, 4] (3's parent now 4, 1's root now 4 too)
	 * 
	 */
	public void union(int a, int b) {
		int rootOfA = getRoot(a);
		int rootOfB = getRoot(b);
		
		parent[rootOfA] = rootOfB;
	}
	
	/*
	 * Returns true if a and b are connected
	 * 
	 */
	public boolean areConnected(int a, int b) {
		return getRoot(a) == getRoot(b);
	}
	
	/*
	 * Gets the root parent for i
	 * 
	 * We initialize i to be its index, so keep
	 * looping round until we find an element where the
	 * value is the same as the index.
	 *  
	 */
	private int getRoot(int i) {
		
		while(i != parent[i]) {
			// Simple path compression: id[i] = id[id[i]]
			// (set every other node to point to its grandparent)
			i = parent[i];
		}
		
		return i;
	}
}
