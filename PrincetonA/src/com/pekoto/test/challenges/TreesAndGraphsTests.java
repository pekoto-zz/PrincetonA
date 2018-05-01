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
		TreeNode nodeNeg3 = new TreeNode(-3);
		TreeNode node11 = new TreeNode(11);
		TreeNode node7 = new TreeNode(7);
		TreeNode node5 = new TreeNode(5);
		TreeNode node8 = new TreeNode(8);
		TreeNode node10_1 = new TreeNode(10);
		TreeNode node6 = new TreeNode(6);
		TreeNode node12 = new TreeNode(12);
		TreeNode nodeNeg5 = new TreeNode(-5);
		
		root.left = nodeNeg3;
		nodeNeg3.left = node11;
		node11.left = node7;
		nodeNeg3.right = node5;
		node5.right = node8;
		
		root.right = node10_1;
		node10_1.left = node6;
		node10_1.right = node12;
		node12.right = nodeNeg5;
		
		assertEquals(1, TreesAndGraphs.countPathsWithSum(root, 20));
	}
}
