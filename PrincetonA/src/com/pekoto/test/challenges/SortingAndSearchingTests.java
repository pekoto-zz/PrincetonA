package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pekoto.challenges.Listy;
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
    
    @Test
    public void testFindInListy() {
        
        int [] arr = {5, 7, 9, 12, 44, 76};
        
        Listy list = new Listy(arr);
    
        assertEquals(-1, SortingAndSearching.findInListy(list, 69));
        assertEquals(0, SortingAndSearching.findInListy(list, 5));
        assertEquals(1, SortingAndSearching.findInListy(list, 7));
        assertEquals(2, SortingAndSearching.findInListy(list, 9));
        assertEquals(3, SortingAndSearching.findInListy(list, 12));
        assertEquals(4, SortingAndSearching.findInListy(list, 44));
        assertEquals(5, SortingAndSearching.findInListy(list, 76));
    }
    
    @Test
    public void testSortedMatrixSearch() {
        
        int [] [] matrix = {
                {1,  2,  3,  4},
                {10, 11, 12, 13},
                {20, 21, 22, 23},
                {30, 31, 32, 33},
                {40, 41, 42, 43}
        };
        
        assertTrue(SortingAndSearching.sortedMatrixSearch(matrix, 22));
        assertTrue(SortingAndSearching.sortedMatrixSearch(matrix, 1));
        assertTrue(SortingAndSearching.sortedMatrixSearch(matrix, 43));
        assertFalse(SortingAndSearching.sortedMatrixSearch(matrix, -1));
    }
}
