package com.pekoto.test.challenges;

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
}
