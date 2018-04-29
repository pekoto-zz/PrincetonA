package com.pekoto.challenges;

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
    
}
