package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

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
}
