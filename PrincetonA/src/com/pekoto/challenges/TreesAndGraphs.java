package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.LinkedList;

public class TreesAndGraphs {

    /**
     * Returns true if the given root node of a binary tree is balanced
     * (Balanced = difference between height of subtrees <= 1)
     * 
     * Performance: O(n)
     * Space: O(h) -- height of tree due to recursive calls
     * 
     * Point: The height of null subtrees are given as -1,
     *        so that the bottom elements get a height of 0.
     * 
     * @param root
     * @return True if the binary tree is balanced
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) > Integer.MIN_VALUE;
    }
    
    private int checkHeight(TreeNode node) {
        if(node == null) {
            return -1;
        }
        
        int leftHeight = checkHeight(node.left);
        
        // Left subtree unbalanced, pass it up the recursion stack
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        
        int rightHeight = checkHeight(node.right);
        
        // Right subtree unbalanced, pass it up the recursion stack
        if(rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        
        int heightDifference = Math.abs(leftHeight - rightHeight);
        
        if(heightDifference > 1) {
            // This is a fairly arbitrary error flag
            // We could have throw an exception or passed back a tuple or something
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * Returns a list of linked lists that could have been used to generate the
     * BST given as input.
     * 
     * This one is quite complex to wrap your head around due to multiple
     * recursion.
     * 
     * Essentially, the idea is to create sublists (left and right) from the root
     * of the tree. Then, we iterate through all of those sublists in every possible
     * way as long as the elements in the list are kept in the same relative order
     * in the generated permutation.
     * 
     * The weave lists function does the permutation generation.
     * 
     * The way this works:
     * 1. Take an element from the left list and add it to the prefix
     * 2. Recurse
     * 3. If the left or right list is empty, generate a permutation by joining
     *    the prefix, left, and right lists. Return.
     * 4. If the left list is not empty, the next recursion call will remove another
     *    element from the left list. Thus we are basically looping through the left
     *    list using recursion.
     * 5. THen we do the same with the right list.
     * 6. Note that on the left list recursion, we end up triggering the right
     *    list recursion and so on. This is what makes it so complex.
     * 
     * @param node
     * @return A list of linked lists, where each linked list is a possible
     * 		   array sequence that could have been used to generate the
     * 		   BST given as input.
     */
	public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		
		if (node == null) {
			result.add(new LinkedList<Integer>());
			return result;
		} 
		
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);
		
		/* Recurse on left and right subtrees. */
		ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
		
		/* Weave together each list from the left and right sides. */
		for (LinkedList<Integer> left : leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
				weaveLists(left, right, weaved, prefix);
				result.addAll(weaved);
			}
		}
		return result;
	}
    
    public static void weaveLists(LinkedList<Integer> left, LinkedList<Integer> right, ArrayList<LinkedList<Integer>> weaved, LinkedList<Integer> prefix) {
		/* One list is empty. Add the remainder to [a cloned] prefix and
		 * store result. */
		if (left.size() == 0 || right.size() == 0) {
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(left);
			result.addAll(right);
			weaved.add(result);
			return;
		}
		
		/* Recurse with head of first added to the prefix. Removing the
		 * head will damage first, so we’ll need to put it back where we
		 * found it afterwards. 
		 * 
		 * I.e., We add the first element from the left list onto our prefix list,
		 * 		 merge the prefix, left, and right lists together,
		 * 		 and then add the element back onto out left list.
		 * */
		int headFirst = left.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(left, right, weaved, prefix);
		prefix.removeLast();
		left.addFirst(headFirst);
		
		/* Do the same thing with second, damaging and then restoring
		 * the list.*/
		int headSecond = right.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(left, right, weaved, prefix);
		prefix.removeLast();	
		right.addFirst(headSecond);
	}
	

}
