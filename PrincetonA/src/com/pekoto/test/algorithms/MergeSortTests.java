package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.pekoto.algorithms.MergeSort;

/**
 * Unit tests for the MergeSort class
 */
public class MergeSortTests {

    @Test
    public void testSort() {
        int [] arr = {5, 3, 7, -1, -100};
        
        MergeSort.sort(arr);
        
        assertEquals(-100, arr[0]);
        assertEquals(-1, arr[1]);
        assertEquals(3, arr[2]);
        assertEquals(5, arr[3]);
        assertEquals(7, arr[4]);
    }
}
