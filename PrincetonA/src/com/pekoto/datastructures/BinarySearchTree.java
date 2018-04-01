package com.pekoto.datastructures;

import java.util.ArrayList;

/**
 * A tree in symmetric order:
 * - Every element in the left subtree is < parent
 * - Every element in the right subtree is > parent
 * 
 * This means we can essentially do binary searches, like a quicksort partition -
 * every element on the left <, every element on the right >. So IF the tree is well balanced,
 * it will be efficient.
 * 
 * Operations typically take O(log n) time, but worst case is O(n) since, if keys are inserted
 * with a natural ordering, the tree will just have a linked list structure.
 * 
 * Uses:
 * * Finding k elements < value
 * * Quick lookup/insert (if elements random)
 * * Printing out min > max values (via in order traversal)
 * 
 * * get(key)
 * * put(key, value)
 * * delete(key)
 * * size()
 * * inorderTraversal()
 * * max()
 * * min()
 * * lessThan()
 * 
 * @param <K> The type of key
 * @param <V> The type of value
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    
    private Node root;
    
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int count;
        
        public Node(K key, V value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }
    
    /**
     * Add a new node into the tree, or update an existing node
     * 
     * Recursively call a helper method from the root:
     * 1. If we've reached the end of the tree (hit a null node),
     *    return a new node with this key/value
     * 2. Otherwise, compare the current node to the new key
     * 3. If the key is <, set the left subtree to be the left subtree + this node
     * 4. If the key is >, set the right subtree to be the right subtree + this node
     * 5. If key is ==, update the value of this node
     * 6. Update the node count
     * 7. Return the node
     * 
     * Average: O (log n)
     * Worst: O (n) -- tree keys are inserted according to natural ordering
     *                 and so tree has linear structure
     * 
     * @param key The node key
     * @param value The node value
     */
    public void put(K key, V value) {
        root = put(root, key, value);
    }
    
    private Node put(Node node, K key, V value) {
        if(node == null) {
            return new Node(key, value, 1);
        }
            
        int result = key.compareTo(node.key);
        
        if(result < 0) {
            // Put node in left subtree
            node.left = put(node.left, key, value);
        } else if (result > 0) {
            // Put node in right subtree
            node.right = put(node.right, key, value);
        } else {
            // Update value of existing node
            node.value = value;
        }
        
        // Set the number of nodes in subtrees + 1 (for the node itself)
        node.count = size(node.left) + 1 + size(node.right);
        
        return node;
    }
    
    /**
     * Starting at the root, while the node is not null...
     * 1. Compare the key to the current node
     * 2. If the key is <, move to left subtree
     * 3. If the key is >, move to right subtree
     * 4. If the key is ==, return value
     * 
     * Average: O (log n)
     * Worst: O (n) -- tree keys are inserted according to natural ordering
     *                 and so tree has linear structure
     * 
     * @param key The key to find
     * @return The value associated with the given key, or null if key not found
     */
    public V get(K key) {
        Node node = root;
        
        while(node != null) {
            int result = key.compareTo(node.key);
            
            if(result < 0) {
                node = node.left;
            } else if (result > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        
        return null;
    }
    
    /**
     * Remove the node with the specified key from the tree
     * 0. First, find the key to delete
     * 1. If the node has no children, just set the parent link to be null
     * 2. If the node has a left child only, set the link to the right subtree
     * 3. If the node has a right child only, set the link to be the left subtree
     * 4. If the node has two children, replace it with the min node in the right subtree...
     * 4.1 Save the node to delete
     * 4.2 Update node to be the minimum node in the right subtree
     * 4.3 Set the node's right subtree to remove the minimum element (now effectively swapped)
     * 4.4 Set the node's left subtree to be the node to delete's left subtree
     * 5. Update count
     * 
     * Average: O (log n)
     * Worst: O (sqrt(n)) -- research shows lots of random inserts/deletes lead to an unbalanced tree
     * 
     * @param key The key to remove from the tree
     */
    public void remove(K key) {
        root = remove(root, key);
    }
    
    private Node remove(Node node, K key) {
        if(node == null) {
            return null;
        }
        
        int result = key.compareTo(node.key);
        
        if(result < 0) {
            node.left = remove(node.left, key);
        } else if (result > 0) {
            node.right = remove(node.right, key);
        } else {
            if(node.right == null) {
                // Node has no right node -- set child to be left node
                return node.left;
            } else if (node.left == null) {
                // Node has no left child -- set child to be right node
                return node.right;
            } else {
                // Node has left and right children
                // 
                Node nodeToDelete = node;
                
                // Replace the node with the smallest node in the right subtree
                node = min(nodeToDelete.right);
                
                // Remove the smallest node from the right subtree (we just moved it up)
                node.right = deleteMin(nodeToDelete.right);
                
                // Set the left subtree to be the deleted nodes old subtree
                node.left = nodeToDelete.left;
            }
        }
        
        node.count = size(node.left) + 1 + size(node.right);
        
        return node;
    }
    
    /**
     * Deletes the minimum node in a subtree
     * 
     * 1. Recursively find the minimum node in this subtree (the node where the left node is null)
     * 2. Return that nodes right node, which will be the new subtree for the parent
     * 3. Update the count
     * 
     * @param node The subtree to delete the min from
     * @return The subtree with the min node deleted
     */
    private Node deleteMin(Node node) {
        if(node.left == null) {
            return node.right;
        }
        
        node.left = deleteMin(node.left);
        node.count = size(node.left) + 1 + size(node.right);
        return node;
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(Node node) {
        if(node == null) {
            return 0;
        } else {
            return node.count;
        }
    }
    
    /**
     * Finds the number of elements in the tree less than and including a given key
     * 
     * Start at the root, recursively:
     * 1. If the node == null, return
     * 2. Compare the key to the node key
     * 3. If the key <, recurse to left subtree
     * 4. If the key >, add size of left subtree + 1 (this node) + recurse to right subtree
     * 5. If the key ==, return 1 (this node) + size of this node's left subtree
     * 
     * @param key The key to find elements less than
     * @return The number of elements < this key (inclusive)
     */
    public int lessThan(K key) {
        return lessThan(root, key);
    }
    
    private int lessThan(Node node, K key) {
        if(node == null) {
            return 0;
        }
        
        int result = key.compareTo(node.key);
        
        if(result < 0) {
            return lessThan(node.left, key);
        } else if (result > 0) {
            return 1 + size(node.left) + lessThan(node.right, key);
        } else {
            return 1 + size(node.left);
        }
    }
    
    public V min() {
        if(root == null) {
            return null;
        } else {
            return min(root).value;
        }
    }
    
    private Node min(Node node) {
        if(node == null) {
            return null;
        }
        
        while(node.left != null) {
            node = node.left;
        }
        
        return node;
    }
    
    public V max() {
        
        if(root == null) {
            return null;
        } else {
            return max(root).value;
        }
    }
    
    private Node max(Node node) {
        
        if(node == null) {
            return null;
        }
        
        while(node.right != null) {
            node = node.right;
        }
        
        return node;
    }
    
    public Iterable<K> keys() {
        ArrayList<K> list = new ArrayList<K>();
        inorderTraversal(root, list);
        return list;
    }
    
    /**
     * Traverse the tree from bottom left -> root -> bottom right
     * Returns the values in-order (min -> max)
     * 
     * @param node The node to traverse from
     * @param list An Iterable to store traversed nodes
     */
    private void inorderTraversal(Node node, ArrayList<K> list) {
        if(node == null) {
            return;
        }
        
        inorderTraversal(node.left, list);
        list.add(node.key);
        inorderTraversal(node.right, list);
    }
    
    /**
     * Returns the list of nodes in preorder.
     * (Top > bottom-left > bottom-right)
     * 
     * 1. Visit the root
     * 2. Recursively visit the left subtree
     * 3. Recursively visit the right subtree
     * 
     * Could be used to copy a tree
     * 
     * @return A list of pre-ordered keys
     */
    public ArrayList<K> preorderTraversal() {
        ArrayList<K> keys = new ArrayList<K>();
        
        preorderTraversal(root, keys);
        
        return keys;
    }
    
    private void preorderTraversal(Node node, ArrayList<K> keys) {
        if(node == null) {
            return;
        }
        
        keys.add(node.key);
        preorderTraversal(node.left, keys);
        preorderTraversal(node.right, keys);
    }
    
    /**
     * Returns the list of nodes in postorder.
     * (bottom-left > bottom-right > top)
     * 
     * 1. Recursively visit the left subtree
     * 2. Recursively visit the right subtree
     * 3. Visit the root
     * 
     * Could be used to delete a tree
     * 
     * @return A list of pre-ordered keys
     */
    public ArrayList<K> postorderTraversal() {
        ArrayList<K> keys = new ArrayList<K>();
        
        postorderTraversal(root, keys);
        
        return keys;
    }
    
    private void postorderTraversal(Node node, ArrayList<K> keys) {
        if(node == null) {
            return;
        }
        
        postorderTraversal(node.left, keys);
        postorderTraversal(node.right, keys);
        keys.add(node.key);
    }
}
