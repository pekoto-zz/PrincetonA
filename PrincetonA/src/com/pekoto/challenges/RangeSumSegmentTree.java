package com.pekoto.challenges;

/*
 * Segment trees can be use to find the max, min, sum, etc.
 * in log(n) time.
 * 
 * They can be built using arrays or nodes.
 * (arrays just represent nodes in the tree -- like a binary heap)
 * 
 * The leaf nodes contain an element in the array, and the nodes above
 * represent the value at range of those nodes. For example:
 * 
 *         0  1  2
 * Input: [1, 3, 5]
 * 
 * Segment Tree:
 * 
 *             9 (0-2)
 *           /  \
 *   (0-1)   4   5 (2-2)
 *          / \ 
 *   (0-0) 1   3 (1-1)
 * 
 * [Sum is value of node, and range in parenthesis)
 * 
 * Thus to build:
 * 1. Pass in array, start and end
 * 2. If start > end, return
 * 3. If start == end, set value to value in array (hit leaf node)
 * 4. Else set left to build(start...mid)
 * 		   set right to build(mid+1...end)
 *         set sum = left.sum + right.sum
 * 
 * To query the tree, it's like querying a binary search tree, but we
 * query using the range covered by this node.
 * 
 * To construct: O(n)
 * To query: O(log n)
 * 
 */
public class RangeSumSegmentTree {

	private class SegmentTreeNode {
		int start;
		int end;
		SegmentTreeNode left;
		SegmentTreeNode right;
		int sum;
	
		SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.left = null;
			this.right = null;
			this.sum = 0;
		}
		
		@Override
		public String toString() {
			return start + "-" + end + "("+sum+")";
		}
	}
	
	SegmentTreeNode root = null;
	
	public RangeSumSegmentTree(int[] nums) {
		root = buildTree(nums, 0, nums.length-1);
	}
	
	/*
	 * To build the tree:
	 * 1. If start > end, return
	 * 2. If start == end, set value to nums[start]
	 * 3. Otherwise...
	 * 	3.1 Get the mid
	 * 	3.2 Set left to build from start...mid
	 * 	3.3 Set right to build from mid+1...end
	 * 	3.4 Set sum to be sum of left and right
	 * 4.Return the node
	 */
	private SegmentTreeNode buildTree(int[] nums, int start, int end) {
		if(start > end) {
			return null;
		}
		
		SegmentTreeNode node = new SegmentTreeNode(start, end);
		
		if(start == end) {
			node.sum = nums[start];
		} else {

			int mid = start + (end-start)/2;
			
			node.left = buildTree(nums, start, mid);
			node.right = buildTree(nums, mid+1, end);
				
			// If this was a min/max tree we could set this
			// to the min/max value
			node.sum = node.left.sum + node.right.sum;
		}
		
		return node;
	}
	
	public void update(int i, int val) {
		update(root, i, val);
	}
	
	/*
	 * Like searching a BST, except we search
	 * on the range of the current node, and
	 * update the values once we find our leaf
	 */
	private void update(SegmentTreeNode node, int i, int val) {
		if(node.start == node.end) {
			node.sum = val;
		} else {
			int mid = node.start + (node.end-node.start)/2;
			
			if(i <= mid) {
				update(node.left, i, val);
			} else {
				update(node.right, i, val);
			}
			
			node.sum = node.left.sum + node.right.sum;
		}
	}
	
	public int sumRange(int i, int j) {
		return sumRange(root, i, j);
	}
	
	/*
	 * Again, similar to searching a BST, except we are searching on the values
	 * that this node range covers.
	 */
	private int sumRange(SegmentTreeNode node, int start, int end) {
		if(node.start == start && node.end == end) {
			return node.sum;
		}
		
		int mid = node.start + (node.end-node.start)/2;
		
		// If end if <= this node range, then answer must be in let tree
		if(end <= mid) {
			// Search left
			return sumRange(node.left, start, end);
		} else if (start >= mid+1) {
			return sumRange(node.right, start, end);
		} else {
			return sumRange(node.left, start, mid) + sumRange(node.right, mid+1, end);
		}
	}
}
