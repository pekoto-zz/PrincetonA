package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RecursionAndDynamic {

    /*
     * Counts the ways a stair can be traversed via 1, 2, or
     * 3 steps at a time.
     * (Note: Will overflow fairly quickly).
     */
    public static int countWays(int n) {
        int [] memo = new int[n+1];
        Arrays.fill(memo, -1);
        
        return countWays(n, memo);
    }
    
    private static int countWays(int n, int[] memo) {
        if(n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[n] > -1) {
            return memo[n];
        } else {
            memo[n] = countWays(n-1, memo) + countWays(n-2, memo) + countWays(n-3, memo);
            return memo[n];
        }
    }
    
    /*
     * Finds a path between the top left
     * and bottom right of a maze, assuming you
     * can only move right and down.
     * 
     * Performance: O(NM)
     */
    public static ArrayList<Point> findPath(boolean [][] maze) {
    	if(maze == null || maze.length == 0) {
    		return null;
    	}
    	
    	ArrayList<Point> path = new ArrayList<Point>();
    	HashSet<Point> failedPoints = new HashSet<Point>();
    	
    	if(findPath(maze, maze.length-1, maze[0].length-1, path, failedPoints)) {
    		return path;
    	}
    	
    	return null;
    }
    
    private static boolean findPath(boolean [][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints) {
    	if(row < 0 || col < 0 || !maze[row][col]) {
    		return false;
    	}
    	
    	Point p = new Point(row, col);
    	
    	if(failedPoints.contains(p)) {
    		return false;
    	}
    	
    	boolean atOrigin = (row == 0) && (col == 0);
    	
    	if(atOrigin 
    			// Check col to the left
    			|| findPath(maze, row, col-1, path, failedPoints) 
    			// Check row above
    			|| findPath(maze, row-1, col, path, failedPoints)) {
    		
    		path.add(p);
    		return true;
    	}
    	
    	failedPoints.add(p);
    	return false;
    }
}
