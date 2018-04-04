package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.algorithms.EditDistance;

/**
 * Unit tests for the EditDistance class.
 */
public class EditDistanceTests {

    @Test
    public void testEditDistance() {
        int distance = EditDistance.getEditDistance("Hats", "Catz");
        
        assertEquals(2, distance);
    }
    
    @Test
    public void testEditDistance_StringOneLonger() {
        int distance = EditDistance.getEditDistance("Fireman", "Fire");
        
        assertEquals(3, distance);
    }
    
    @Test
    public void testEditDistance_StringTwoLonger() {
        int distance = EditDistance.getEditDistance("Tach", "Teacher");
        
        assertEquals(3, distance);
    }
    
    @Test
    public void testEditDistance_StringOneEmpty() {
        int distance = EditDistance.getEditDistance("", "Test");
        
        assertEquals(4, distance);
    }
    
    @Test
    public void testEditDistance_StringTwoEmpty() {
        int distance = EditDistance.getEditDistance("Test", "");
        
        assertEquals(4, distance);
    }
}
