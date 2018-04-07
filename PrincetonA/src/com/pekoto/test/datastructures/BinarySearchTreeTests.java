package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;
import com.pekoto.datastructures.BinarySearchTree;

/**
 * Unit tests for the BinarySearchTree class
 */
public class BinarySearchTreeTests {

    @Test
    public void testPut() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        
        ArrayList<Integer> keys = (ArrayList<Integer>) bst.keys();
        
        assertEquals(Integer.valueOf(5), keys.get(0));
        assertEquals(Integer.valueOf(8), keys.get(1));
        assertEquals(Integer.valueOf(10), keys.get(2));
        assertEquals(Integer.valueOf(12), keys.get(3));
        assertEquals(4, keys.size());
    }
    
    @Test
    public void testPutDuplicate() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(5,  13);
        
        ArrayList<Integer> keys = (ArrayList<Integer>) bst.keys();
        
        assertEquals(Integer.valueOf(5), keys.get(0));
        assertEquals(Integer.valueOf(8), keys.get(1));
        assertEquals(Integer.valueOf(10), keys.get(2));
        assertEquals(Integer.valueOf(12), keys.get(3));
        assertEquals(4, keys.size());
    }
    
    @Test
    public void testCount() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        
        assertEquals(4, bst.size());
    }
    
    @Test
    public void testGet() {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();
        
        bst.put(10, "Ten");
        bst.put(5, "Five");
        bst.put(12, "Twelve");
        bst.put(8, "Eight");
        
        assertEquals("Ten", bst.get(10));
        assertEquals("Five", bst.get(5));
        assertEquals("Twelve", bst.get(12));
        assertEquals("Eight", bst.get(8));
        assertNull(bst.get(0));
    }
    
    @Test
    public void testLessThan() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(13,  13);
        bst.put(1,  1);
        bst.put(-100, -100);
        
        assertEquals(3, bst.lessThan(5));
        assertEquals(0, bst.lessThan(-200));
        assertEquals(7, bst.lessThan(500));
    }
    
    @Test
    public void testMin() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(13,  13);
        bst.put(1,  1);
        bst.put(-100, -100);
    
        assertEquals(Integer.valueOf(-100), bst.min());
    }
    
    @Test
    public void testMax() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(13,  13);
        bst.put(1,  1);
        bst.put(-100, -100);
    
        assertEquals(Integer.valueOf(13), bst.max());
    }
    
    @Test
    public void testDeleteMin() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(13,  13);
        bst.put(1,  1);
        bst.put(-100, -100);
        
        bst.remove(-100);
        
        assertNull(bst.get(-100));
        assertEquals(6, bst.size());
    }
    
    @Test
    public void testDeleteMax() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(13,  13);
        bst.put(1,  1);
        bst.put(-100, -100);
        
        bst.remove(13);
        
        assertNull(bst.get(13));
        assertEquals(6, bst.size());
    }
    
    @Test
    public void testDeleteTwoChildren() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(13,  13);
        bst.put(1,  1);
        bst.put(-100, -100);
        
        bst.remove(5);
        
        assertNull(bst.get(5));
        assertEquals(6, bst.size());
        
        ArrayList<Integer> keys = (ArrayList<Integer>) bst.keys();
        
        assertEquals(Integer.valueOf(-100), keys.get(0));
        assertEquals(Integer.valueOf(1), keys.get(1));
        assertEquals(Integer.valueOf(8), keys.get(2));
        assertEquals(Integer.valueOf(10), keys.get(3));
        assertEquals(Integer.valueOf(12), keys.get(4));
        assertEquals(Integer.valueOf(13), keys.get(5));
    }
    
    @Test
    public void testDeleteRoot() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(10, 10);
        bst.put(5, 5);
        bst.put(12, 12);
        bst.put(8, 8);
        bst.put(13,  13);
        bst.put(1,  1);
        bst.put(-100, -100);
        
        bst.remove(10);
        
        assertNull(bst.get(10));
        assertEquals(6, bst.size());
        
        ArrayList<Integer> keys = (ArrayList<Integer>) bst.keys();
        
        assertEquals(Integer.valueOf(-100), keys.get(0));
        assertEquals(Integer.valueOf(1), keys.get(1));
        assertEquals(Integer.valueOf(5), keys.get(2));
        assertEquals(Integer.valueOf(8), keys.get(3));
        assertEquals(Integer.valueOf(12), keys.get(4));
        assertEquals(Integer.valueOf(13), keys.get(5));
    }
    
    @Test
    public void testPreorder() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(5, 5);
        bst.put(3, 3);
        bst.put(8, 8);
        bst.put(2, 2);
        bst.put(4, 4);
        bst.put(7, 7);
        bst.put(9, 9);
        
        ArrayList<Integer> nodes = bst.preorderTraversal();
        
        assertEquals(Integer.valueOf(5), nodes.get(0));
        assertEquals(Integer.valueOf(3), nodes.get(1));
        assertEquals(Integer.valueOf(2), nodes.get(2));
        assertEquals(Integer.valueOf(4), nodes.get(3));
        assertEquals(Integer.valueOf(8), nodes.get(4));
        assertEquals(Integer.valueOf(7), nodes.get(5));
        assertEquals(Integer.valueOf(9), nodes.get(6));
    }
    
    @Test
    public void testPostorder() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(5, 5);
        bst.put(3, 3);
        bst.put(8, 8);
        bst.put(2, 2);
        bst.put(4, 4);
        bst.put(7, 7);
        bst.put(9, 9);
        
        ArrayList<Integer> nodes = bst.postorderTraversal();
        
        assertEquals(Integer.valueOf(2), nodes.get(0));
        assertEquals(Integer.valueOf(4), nodes.get(1));
        assertEquals(Integer.valueOf(3), nodes.get(2));
        assertEquals(Integer.valueOf(7), nodes.get(3));
        assertEquals(Integer.valueOf(9), nodes.get(4));
        assertEquals(Integer.valueOf(8), nodes.get(5));
        assertEquals(Integer.valueOf(5), nodes.get(6));
    }
    
    @Test
    public void testValidate() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
        
        bst.put(3, 3);
        bst.put(2, 2);
        bst.put(5, 5);
        bst.put(4, 4);
        bst.put(1, 1);
        
        assertTrue(bst.isValid());
    }
}
