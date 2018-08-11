package com.pekoto.challenges;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * Serialize and deserialize a binary tree to/from a String
 */
public class TreeCodec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        
        serialize(root, sb);
        
        return sb.toString();
    }
    
    /*
     * Basic in-order traversal
     */
    private void serialize(TreeNode root, StringBuffer sb) {
        if(root == null) {
            // \0 = null character marker
            sb.append("X").append(",");
            return;
        }
        
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(",")));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        
        if(val.equals("X")) {
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        
        return node;
    }
}
