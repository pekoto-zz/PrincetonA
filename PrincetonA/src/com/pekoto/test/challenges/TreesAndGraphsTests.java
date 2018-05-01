package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import com.pekoto.challenges.TreeNode;
import com.pekoto.challenges.TreesAndGraphs;

/*
 * Unit tests for the TreesAndGraphs class.
 */
public class TreesAndGraphsTests {

	@Test
	public void testTreesAndGraphs() {
		TreeNode root = new TreeNode(2);
		TreeNode node0 = new TreeNode(0);
		TreeNode node1 = new TreeNode(1);
		TreeNode node3 = new TreeNode(3);
		TreeNode node5 = new TreeNode(5);
		
		root.setLeftChild(node1);
		node1.setLeftChild(node0);
		root.setRightChild(node3);
		node3.setRightChild(node5);
		
		ArrayList<LinkedList<Integer>> result = TreesAndGraphs.allSequences(root);
		
		System.out.println();
	}
	
	@Test
	public void testCountPathsWithSum() {
	    
	    TreeNode root = new TreeNode(5);
	    TreeNode node10_1 = new TreeNode(10);
	    TreeNode node10_2 = new TreeNode(10);
	    TreeNode nodeNeg5 = new TreeNode(-5);
	    TreeNode node6 = new TreeNode(6);
	    TreeNode nodeNeg1 = new TreeNode(-1);
	    TreeNode node8 = new TreeNode(8);
	    TreeNode node2 = new TreeNode(2);
	    
	    root.setLeftChild(node10_1);
	    root.setRightChild(node8);
	    node10_1.setLeftChild(node10_2);
	    node10_1.setRightChild(node6);
	    node10_2.setLeftChild(nodeNeg5);
	    node6.setRightChild(nodeNeg1);
	    node8.setRightChild(node2);
	    
	    assertEquals(3, TreesAndGraphs.countPathsWithSum(root, 20));	    
	}
}
