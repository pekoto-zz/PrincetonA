package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.pekoto.datastructures.BinaryHeap;

/**
 * Unit tests for the BinaryHeap class
 */
public class BinaryHeapTests {

    @Test
    public void testPut() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        
        heap.put(7);
        heap.put(8);
        heap.put(-1);
        heap.put(5);
        heap.put(1);
        heap.put(9);
        heap.put(5);
        
        assertEquals(7, heap.size());
        assertEquals(Integer.valueOf(9), heap.removeMax());
    }
    
    @Test
    public void testRemoveAllAndShrink() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        
        heap.put(3);
        heap.put(-1);
        heap.put(10);
        
        assertEquals(Integer.valueOf(10), heap.removeMax());
        assertEquals(Integer.valueOf(3), heap.removeMax());
        assertEquals(Integer.valueOf(-1), heap.removeMax());
        assertEquals(0, heap.size());
    }
    
    @Test
    public void testGrowArray() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(5);
        
        heap.put(8);
        heap.put(-3);
        heap.put(7);
        heap.put(54);
        heap.put(22);
        heap.put(3);
        heap.put(-2);
        
        assertEquals(7, heap.size());
        assertEquals(Integer.valueOf(54), heap.removeMax());
    }
}
