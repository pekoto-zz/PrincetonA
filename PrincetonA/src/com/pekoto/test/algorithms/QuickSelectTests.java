package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.pekoto.algorithms.QuickSelect;

/**
 * Unit tests for the quick select class
 */
public class QuickSelectTests {

    @Test
    public void testSelectMin() {
        int [] arr = {-100, -1, 5, 2, 7, -500, 12};
        
        int k = QuickSelect.select(arr, 0);
        
        assertEquals(-500, k);
    }
    
    @Test
    public void testSelectThree() {
        int [] arr = {-100, -1, 5, 2, 7, -500, 12};
        
        int k = QuickSelect.select(arr, 2);
        
        assertEquals(-1, k);
    }
    
    @Test
    public void testSelectSix() {
        int [] arr = {-100, -1, 5, 2, 7, -500, 12};
        
        int k = QuickSelect.select(arr, 6);
        
        assertEquals(12, k);
    }
}
