package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.pekoto.algorithms.HeapSort;

/**
 * Unit tests for the HeapSort class
 */
public class HeapSortTests {
    
    @Test
    public void testSortEven() {
        HeapSort<Integer> sorter = new HeapSort<Integer>();
        
        Integer[] arr = {5, 3, -1, 9, 10, 11};
        
        sorter.sort(arr);
        
        assertEquals(Integer.valueOf(-1), arr[0]);
        assertEquals(Integer.valueOf(3), arr[1]);
        assertEquals(Integer.valueOf(5), arr[2]);
        assertEquals(Integer.valueOf(9), arr[3]);
        assertEquals(Integer.valueOf(10), arr[4]);
        assertEquals(Integer.valueOf(11), arr[5]);
    }
    
    @Test
    public void testSortOdd() {
        HeapSort<Integer> sorter = new HeapSort<Integer>();
        
        Integer[] arr = {25, 300, -12, 3, 4, 592, 1};
        
        sorter.sort(arr);
        
        assertEquals(Integer.valueOf(-12), arr[0]);
        assertEquals(Integer.valueOf(1), arr[1]);
        assertEquals(Integer.valueOf(3), arr[2]);
        assertEquals(Integer.valueOf(4), arr[3]);
        assertEquals(Integer.valueOf(25), arr[4]);
        assertEquals(Integer.valueOf(300), arr[5]);
        assertEquals(Integer.valueOf(592), arr[6]);
    }
    
    @Test
    public void testSortSmall() {
        HeapSort<Integer> sorter = new HeapSort<Integer>();
        
        Integer[] arr = {4, -1, 12};
        
        sorter.sort(arr);
        
        assertEquals(Integer.valueOf(-1), arr[0]);
        assertEquals(Integer.valueOf(4), arr[1]);
        assertEquals(Integer.valueOf(12), arr[2]);
    }
}
