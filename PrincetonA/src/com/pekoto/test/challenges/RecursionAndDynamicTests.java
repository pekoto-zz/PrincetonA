package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.challenges.Point;
import com.pekoto.challenges.RecursionAndDynamic;

/*
 * Unit tests for the RecursionAndDynamic class
 */
public class RecursionAndDynamicTests {

    @Test
    public void testCountWays() {
        assertEquals(121415, RecursionAndDynamic.countWays(20));
    }
    
    @Test
    public void testFindPath() {
    	boolean [][] maze = {
    			{true, false, true, true},
    			{true, true, true, true},
    			{false, true, false, false},
    			{true, true, true, true}
    	};
    	
    	
    	ArrayList<Point> path = RecursionAndDynamic.findPath(maze);
    	
    	assertEquals(7, path.size());
    	assertEquals(new Point(0, 0), path.get(0));
    	assertEquals(new Point(1, 0), path.get(1));
    	assertEquals(new Point(1, 1), path.get(2));
    	assertEquals(new Point(2, 1), path.get(3));
    	assertEquals(new Point(3, 1), path.get(4));
    	assertEquals(new Point(3, 2), path.get(5));
    	assertEquals(new Point(3, 3), path.get(6));
    }
    
    @Test
    public void testGetSubsets() {
        
        ArrayList<Integer> set = new ArrayList<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        
        ArrayList<ArrayList<Integer>> subsets = RecursionAndDynamic.getSubsets(set);
        
        assertEquals(8, subsets.size());
    }
}
