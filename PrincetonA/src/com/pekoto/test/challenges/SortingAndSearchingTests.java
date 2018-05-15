package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.SortingAndSearching;

public class SortingAndSearchingTests {

    @Test
    public void testMergeSorted() {
        
        int [] a = {2, 5, 8, 12, 0, 0, 0};
        int [] b = {3, 4, 10};
        
        SortingAndSearching.mergeSorted(a, b, 4);
        
        assertEquals(2, a[0]);
        assertEquals(3, a[1]);
        assertEquals(4, a[2]);
        assertEquals(5, a[3]);
        assertEquals(8, a[4]);
        assertEquals(10, a[5]);
        assertEquals(12, a[6]);
    }
    
    @Test
    public void testSortAnagrams() {
        String [] arr = {
                "Test",
                "Abcde",
                "531",
                "136",
                "cdebA",
                "seTt"
        };
        
        SortingAndSearching.sortAnagrams(arr);
        
        assertEquals("Abcde", arr[0]);
        assertEquals("cdebA", arr[1]);
        assertEquals("531", arr[2]);
        assertEquals("136", arr[3]);
        assertEquals("Test", arr[4]);
        assertEquals("seTt", arr[5]);
    }
}
