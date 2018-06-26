package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.challenges.Backtracking;

/*
 * Unit tests for the Backtracking class
 */
public class BacktrackingTests {

    @Test
    public void testGetBinary() {
        ArrayList<String> variations = Backtracking.getBinary(1);
        
        assertEquals("00", variations.get(0));
        assertEquals("01", variations.get(1));
        assertEquals("10", variations.get(2));
        assertEquals("11", variations.get(3));
    }
    
    @Test
    public void testGetDecimal() {
        ArrayList<String> variations = Backtracking.getDecimal(2);
        
        assertEquals(100, variations.size());
        assertEquals("00", variations.get(0));
        assertEquals("99", variations.get(99));
    }
    
    @Test
    public void testDiceSum() {
        ArrayList<ArrayList<Integer>> variations = Backtracking.diceSum(2, 4);
        
        assertEquals(3, variations.size());
        assertEquals(Integer.valueOf(1), variations.get(0).get(0));
        assertEquals(Integer.valueOf(3), variations.get(0).get(1));
        
        assertEquals(Integer.valueOf(2), variations.get(1).get(0));
        assertEquals(Integer.valueOf(2), variations.get(1).get(1));
        
        assertEquals(Integer.valueOf(3), variations.get(2).get(0));
        assertEquals(Integer.valueOf(1), variations.get(2).get(1));
    }
}
