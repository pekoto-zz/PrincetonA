package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pekoto.challenges.LinePoint;
import com.pekoto.challenges.ModerateChallenges;
import com.pekoto.challenges.SimpleTuple;

/*
 * Unit tests for the ModerateChallenges class.
 */
public class ModerateChallengesTests {

    @Test
    public void testSwapInPlace() {
        SimpleTuple tuple = ModerateChallenges.swapInPlace(4, 2);
        
        assertEquals(2, tuple.getA());
        assertEquals(4, tuple.getB());
    }
    
    @Test
    public void testGetIntersection() {
        LinePoint lineOneStart = new LinePoint(0.0, 0.0);
        LinePoint lineOneEnd = new LinePoint(2.0, 2.0);
        LinePoint lineTwoStart = new LinePoint(0.0, 2.0);
        LinePoint lineTwoEnd = new LinePoint(2.0, 0.0);
        
        LinePoint intersection = ModerateChallenges.getIntersection(lineOneStart, lineOneEnd, lineTwoStart, lineTwoEnd);
        
        assertEquals(1.0, intersection.x, 0.1);
        assertEquals(1.0, intersection.y, 0.1);
    }
    
    @Test
    public void testFindMinDifference() {
        int [] a = {1, 3, 15, 11, 2};
        int [] b = {23, 127, 235, 19, 8};
        
        int minDifference = ModerateChallenges.findSmallestDifference(a, b);
        
        assertEquals(3, minDifference);
    }
    
    @Test
    public void testGetMaxA() {
        assertEquals(4, ModerateChallenges.getMax(4, 2));
    }
    
    @Test
    public void testGetMaxB() {
        assertEquals(4, ModerateChallenges.getMax(4, 2));
    }
}
