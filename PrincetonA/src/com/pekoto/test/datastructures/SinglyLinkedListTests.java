package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import com.pekoto.datastructures.SinglyLinkedList;

/**
 * Unit tests for the SingleLinkedList class
 */
public class SinglyLinkedListTests {

    @Test
    public void testAddRecursive() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addRecursive("One");
        ll.addRecursive("Two");
        ll.addRecursive("Three");
        
        ArrayList<String> list = new ArrayList<String>();
        
        for(String s: ll) {
            list.add(s);
        }
        
        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("Three", list.get(2));
        assertEquals(3, ll.size());
    }
    
    @Test
    public void testAddIterative() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");
        
        ArrayList<String> list = new ArrayList<String>();
        
        for(String s: ll) {
            list.add(s);
        }
        
        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("Three", list.get(2));
        assertEquals(3, ll.size());
    }    
    
    @Test
    public void testRemove_RemoveFromStart() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");
        
        ll.removeRecursive("One");
        
        ArrayList<String> list = new ArrayList<String>();
        
        for(String s: ll) {
            list.add(s);
        }
        
        assertEquals("Two", list.get(0));
        assertEquals("Three", list.get(1));
        assertEquals(2, ll.size());
    }    
    
    @Test
    public void testRemove_RemoveFromMiddle() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");
        
        ll.removeRecursive("Two");
        
        ArrayList<String> list = new ArrayList<String>();
        
        for(String s: ll) {
            list.add(s);
        }
        
        assertEquals("One", list.get(0));
        assertEquals("Three", list.get(1));
        assertEquals(2, ll.size());
    }   
    
    @Test
    public void testRemove_RemoveFromEnd() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");
        
        ll.removeRecursive("Three");
        
        ArrayList<String> list = new ArrayList<String>();
        
        for(String s: ll) {
            list.add(s);
        }
        
        assertEquals("One", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals(2, ll.size());
    }   
    
    @Test
    public void testGetLast() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");

        assertEquals("Three", ll.getLast());
    }
    
    @Test
    public void testGet_Start() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");

        assertEquals("One", ll.get(0));
    }
    
    @Test
    public void testGet_Mid() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");

        assertEquals("Two", ll.get(1));
    }
    
    @Test
    public void testGet_End() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");

        assertEquals("Three", ll.get(2));
    }
    
    @Test
    public void testReverse() {
        SinglyLinkedList<String> ll = new SinglyLinkedList<String>();
        
        ll.addIterative("One");
        ll.addIterative("Two");
        ll.addIterative("Three");
        
        ll.reverse();

        ArrayList<String> list = new ArrayList<String>();
        
        for(String s: ll) {
            list.add(s);
        }
        
        assertEquals("Three", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("One", ll.get(2));
        assertEquals(3, ll.size());
    }
}
