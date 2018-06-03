package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pekoto.challenges.LinePoint;
import com.pekoto.challenges.ModerateChallenges;
import com.pekoto.challenges.Person;
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
    
    @Test
    public void testGetMaxAliveUsingSorting() {
        
        Person p1 = new Person(1901, 1990);
        Person p2 = new Person(1901, 1983);
        Person p3 = new Person(1902, 1902);
        Person p4 = new Person(1905, 1972);
        Person p5 = new Person(1903, 1904);
        Person p6 = new Person(1905, 1999);
        
        Person [] people = { p1, p2, p3, p4, p5, p6 };
        
        int maxYear = ModerateChallenges.getMaxAliveUsingSorting(people, 1901);
        
        assertEquals(1905, maxYear);
    }
    
    @Test
    public void testGetMaxAliveUsingDeltas() {
        
        Person p1 = new Person(1901, 1990);
        Person p2 = new Person(1901, 1983);
        Person p3 = new Person(1902, 1902);
        Person p4 = new Person(1905, 1972);
        Person p5 = new Person(1903, 1904);
        Person p6 = new Person(1905, 1999);
        
        Person [] people = { p1, p2, p3, p4, p5, p6 };
        
        int maxYear = ModerateChallenges.getMaxAliveUsingDeltas(people, 1901, 1999);
        
        assertEquals(1905, maxYear);
    }
    
    @Test
    public void testFindUnsortedSequence() {
        int [] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        
        SimpleTuple result = ModerateChallenges.findUnsortedSequence(arr);
        
        assertEquals(3, result.getA());
        assertEquals(9, result.getB());
    }
    
    @Test
    public void testGetMaxSum() {
        int [] arr = {2, -8, 3, -2, 4, -10};
        
        assertEquals(5, ModerateChallenges.getMaxSum(arr));
    }
    
    @Test
    public void testDoesMatch() {
        assertTrue(ModerateChallenges.doesMatch("ababb", "backbatbackbatbat"));
    }
    
    @Test 
    public void testGetPondSizes() {
        
        int [][] matrix = {
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        };
        
        List<Integer> pondSizes = ModerateChallenges.getPondSizes(matrix);
        
        assertEquals(3, pondSizes.size());
        assertEquals(Integer.valueOf(2), pondSizes.get(0));
        assertEquals(Integer.valueOf(4), pondSizes.get(1));
        assertEquals(Integer.valueOf(1), pondSizes.get(2));
    }
}
