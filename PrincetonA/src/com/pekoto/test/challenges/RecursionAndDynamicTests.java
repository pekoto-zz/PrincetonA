package com.pekoto.test.challenges;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Stack;

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
    
    @Test
    public void testMinProduct() {
        assertEquals(56, RecursionAndDynamic.minProduct(7, 8));
    }
    
    @Test 
    public void testTowersOfHanoi() {
    	Stack<Integer> source = new Stack<Integer>();
    	source.push(4);
    	source.push(3);
    	source.push(2);
    	source.push(1);
    	
    	Stack<Integer> buffer = new Stack<Integer>();
    	Stack<Integer> destination = new Stack<Integer>();
    	
    	RecursionAndDynamic.towersOfHanoi(4, source, destination, buffer);
    	
    	assertEquals(0, source.size());
    	assertEquals(4, destination.size());
    	assertEquals(Integer.valueOf(1), destination.pop());
    	assertEquals(Integer.valueOf(2), destination.pop());
    	assertEquals(Integer.valueOf(3), destination.pop());
    	assertEquals(Integer.valueOf(4), destination.pop());
    }
    
    @Test
    public void testPermutationsWithoutDupes() {
        ArrayList<String> permutations = RecursionAndDynamic.getPermutationsWithoutDupes("ABC");
        
        assertEquals(6, permutations.size());
        assertEquals("ABC", permutations.get(0));
        assertEquals("BAC", permutations.get(1));
        assertEquals("BCA", permutations.get(2));
        assertEquals("ACB", permutations.get(3));
        assertEquals("CAB", permutations.get(4));
        assertEquals("CBA", permutations.get(5));
    }
    
    @Test
    public void testGetParenthesesPermutations() {
        ArrayList<String> variations = RecursionAndDynamic.getParenthesesPermutations(3);
        
        assertEquals(5, variations.size());
        assertEquals("((()))", variations.get(0));
        assertEquals("(()())", variations.get(1));
        assertEquals("(())()", variations.get(2));
        assertEquals("()(())", variations.get(3));
        assertEquals("()()()", variations.get(4));
    }
    
    @Test
    public void testMakeChange() {
        assertEquals(6, RecursionAndDynamic.makeChange(15));
    }
    
    @Test
    public void testEightQueens() {
        ArrayList<Integer[]> results = RecursionAndDynamic.eightQueens();
        assertEquals(91, results.size());
    }
}
