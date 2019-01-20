package com.pekoto.datastructures;

/*
 * A Binary Indexed Tree (Fenwick Tree) is a way to quickly perform range-sum
 * and update operations.
 * 
 * Take an array and use it represent a tree.
 * 
 * Each element in the array either represents:
 * 1. a[i] -- if i is odd
 * 2. a[0]+...+a[i] -- if i is a power of 2
 * 
 * So we end up with a tree like this:
 * 
 * (Assume a 1-indexed array to simplify things)
 *      0  1  2  3  4  5
 * a = [0, 1, 2, 3, 4, 5]
 * 
 * [1] (bit 1)     [3] (bit 3)        [5] (bit 5)
 * 
 *        [3] (bit 2-a[1]+a[2]) [10] (bit 4-a[1]+a[2]+a[3]+a[4])
 * 
 * Then we can imagine going from the roots of the tree
 * up to the top adding or subtracting the parents during a range
 * query or update.
 * 
 * To get the next node in the array, we use x&(-x),
 * which gets the rightmost bit.
 * 
 * 1110 (-2)
 * 0010 (2)
 * 0010 (2&-2)
 * 
 * E.g., if we wanted to set the first bit to 1...
 * we set node 1 to 1, node 2 to 1, node 4 to 1... (use formula to get next node)
 * Then we set node 2 to 2, so we add 2 to node 2, giving 3, then 2 to node 4, giving 3, etc...
 * 
 * To get the sum, we start at the lower most node, then use the formula to move up the tree.
 * 
 * Space: O(n)
 * Create: O(n log n)
 * Query: O(log n)
 * Update: O(n)
 * 
 */
public class BinaryIndexedTree {
	
	int[] nums;
	int[] tree;

	public BinaryIndexedTree(int[] nums) {
		this.nums = nums;
		this.tree = new int[nums.length + 1];
		
		for (int i = 0; i < nums.length; i++) {
			setVals(i, nums[i]);			
		}
	}

	public void setVals(int index, int val) {
		index++;
		
		while (index < tree.length) {
			tree[index] += val;
			// Move down to tree -- add index
			index += (index & -index);
		}
	}

	public void update(int index, int val) {
		int diff = val - nums[index];
		nums[index] = val;
		setVals(index, diff);
	}

	public int getSum(int index) {
		int sum = 0;
		index++;
		while (index > 0) {
			sum += tree[index];
			// Move back up tree (subtract from index)
			index -= (index & -index);
		}
		return sum;
	}

	public int sumRange(int i, int j) {
		return getSum(j) - getSum(i - 1);
	}
}
