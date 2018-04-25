package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.challenges.LinkedListNode;
import com.pekoto.challenges.LinkedLists;

/**
 * Unit tests for the LinkedLists class
 */
public class LinkedListTests {

    @Test
    public void testRemoveDuplicates() {
        LinkedListNode node = new LinkedListNode(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(1);
        node.appendToTail(3);
        node.appendToTail(5);
        
        LinkedLists.removeDuplicates(node);
        ArrayList<Integer> arrayList = node.toArrayList();
        
        assertEquals(Integer.valueOf(1), arrayList.get(0));
        assertEquals(Integer.valueOf(2), arrayList.get(1));
        assertEquals(Integer.valueOf(3), arrayList.get(2));
        assertEquals(Integer.valueOf(5), arrayList.get(3));
        assertEquals(4, arrayList.size());
    }
    
    @Test
    public void testRemoveDuplicates_LastElementDupe() {
        LinkedListNode node = new LinkedListNode(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(1);
        node.appendToTail(3);
        node.appendToTail(5);
        node.appendToTail(5);
        
        LinkedLists.removeDuplicates(node);
        ArrayList<Integer> arrayList = node.toArrayList();
        
        assertEquals(Integer.valueOf(1), arrayList.get(0));
        assertEquals(Integer.valueOf(2), arrayList.get(1));
        assertEquals(Integer.valueOf(3), arrayList.get(2));
        assertEquals(Integer.valueOf(5), arrayList.get(3));
        assertEquals(4, arrayList.size());
    }
    
    @Test
    public void testGetKthElement() {
        LinkedListNode node = new LinkedListNode(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(4);
        node.appendToTail(5);
        
        assertEquals(Integer.valueOf(4), LinkedLists.getKthElement(node, 2));
    }
    
    @Test
    public void testGetKthElement_Last() {
        LinkedListNode node = new LinkedListNode(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(4);
        node.appendToTail(5);
        
        assertEquals(Integer.valueOf(5), LinkedLists.getKthElement(node, 1));
    }
    
    @Test
    public void testGetKthElement_First() {
        LinkedListNode node = new LinkedListNode(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(4);
        node.appendToTail(5);
        
        assertEquals(Integer.valueOf(1), LinkedLists.getKthElement(node, 5));
    }
    
    @Test
    public void testPartition() {
        LinkedListNode node = new LinkedListNode(3);
        node.appendToTail(5);
        node.appendToTail(8);
        node.appendToTail(5);
        node.appendToTail(10);
        node.appendToTail(2);
        node.appendToTail(1);
        
        LinkedLists.partition(node, 5);
        
        ArrayList<Integer> arrayList = node.toArrayList();

        assertEquals(Integer.valueOf(3), arrayList.get(0));
        assertEquals(Integer.valueOf(2), arrayList.get(1));
        assertEquals(Integer.valueOf(1), arrayList.get(2));
        assertEquals(Integer.valueOf(5), arrayList.get(3));
        assertEquals(Integer.valueOf(10), arrayList.get(4));
        assertEquals(Integer.valueOf(5), arrayList.get(5));
        assertEquals(Integer.valueOf(8), arrayList.get(6));
    }
    
    @Test
	public void testAddInReverse() {
		LinkedListNode one = new LinkedListNode(7);
		one.appendToTail(1);
		one.appendToTail(6);
		
		LinkedListNode two = new LinkedListNode(5);
		two.appendToTail(9);
		two.appendToTail(2);
		
		LinkedListNode result = LinkedLists.addInReverse(one, two);
		ArrayList<Integer> list = result.toArrayList();
		
		assertEquals(Integer.valueOf(2), list.get(0));
		assertEquals(Integer.valueOf(1), list.get(1));
		assertEquals(Integer.valueOf(9), list.get(2));
	}
	
	@Test
	public void testAddInReverse_GrowDigits() {
		LinkedListNode one = new LinkedListNode(7);
		one.appendToTail(1);
		one.appendToTail(6);
		
		LinkedListNode two = new LinkedListNode(5);
		two.appendToTail(9);
		two.appendToTail(4);
		
		LinkedListNode result = LinkedLists.addInReverse(one, two);
		ArrayList<Integer> list = result.toArrayList();
		
		assertEquals(Integer.valueOf(2), list.get(0));
		assertEquals(Integer.valueOf(1), list.get(1));
		assertEquals(Integer.valueOf(1), list.get(2));
		assertEquals(Integer.valueOf(1), list.get(3));
	}
	
	@Test
	public void testAddInReverse_LongerNumber() {
		LinkedListNode one = new LinkedListNode(7);
		one.appendToTail(1);
		one.appendToTail(6);
		
		LinkedListNode two = new LinkedListNode(0);
		two.appendToTail(1);
		
		LinkedListNode result = LinkedLists.addInReverse(one, two);
		ArrayList<Integer> list = result.toArrayList();
		
		assertEquals(Integer.valueOf(7), list.get(0));
		assertEquals(Integer.valueOf(2), list.get(1));
		assertEquals(Integer.valueOf(6), list.get(2));
	}
	
	@Test
	public void testIsPalindrome_True() {
	    LinkedListNode list = new LinkedListNode(1);
	    list.appendToTail(2);
	    list.appendToTail(2);
	    list.appendToTail(1);
	    
	    assertTrue(LinkedLists.isPalindrome(list));
	}
	
	@Test
	public void testIsPalindrome_False() {
	    LinkedListNode list = new LinkedListNode(1);
	    list.appendToTail(2);
	    list.appendToTail(3);
	    list.appendToTail(4);
	    
	    assertFalse(LinkedLists.isPalindrome(list));
	}
}
