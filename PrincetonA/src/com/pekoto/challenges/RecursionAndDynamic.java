package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

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
    
    /*
     * Recursively generates all possible subsets from a set
     * represented by an ArrayList.
     * 
     * Point: Subsets can be generated for the set ABC by appending C
     *        to every subset for AB, which can be generated by appending
     *        B to every subset for A, which is just {0} and {A}, etc.
     *        
     * Performance: O(n2^n) -- an iterative implementation would bring a slight optimisation
     * 
     * Note on performance calculation:
     * There will be 2^n subsets for every set of size n.
     * You can derive this by noticing the set double in size with each new element,
     * because each new element has a choice of being in all of the previous subsets, or not.
     * So you get all of the previous subsets + all of the previous subsets with this element added.
     * 
     * Each element n will be contained in half of the n previous subsets, which we iterate around
     * to generate our new subsets, hence we get n2^2 runtime.
     * 
     */
    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {
        return getSubsets(set, 0);
    }
    
    private static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allSubsets;
        
        if(set.size() == index) {
            // Add empty set
            allSubsets = new ArrayList<ArrayList<Integer>>();
            allSubsets.add(new ArrayList<Integer>());
        } else {
            allSubsets = getSubsets(set, index+1);
            
            int item = set.get(index);
            
            ArrayList<ArrayList<Integer>> allSubsetsForThisItem = new ArrayList<ArrayList<Integer>>();
            
            for(ArrayList<Integer> subset: allSubsets) {
                ArrayList<Integer> subsetForThisItem = new ArrayList<Integer>();
                subsetForThisItem.addAll(subset);
                subsetForThisItem.add(item);
                allSubsetsForThisItem.add(subsetForThisItem);
            }
            
            allSubsets.addAll(allSubsetsForThisItem);
        }
        
        return allSubsets;
    }
    
    /*
     * Returns the product of two numbers
     * without using multiplication.
     * 
     * Point: We can think of the product as the number of
     *        elements in an AxB matrix of two numbers A and B.
     *        Instead of counting every element, though, we can
     *        count the elements in a row and continuously double
     *        until we reach the total number of rows.
     *        Of course, we need a special condition if there are an odd
     *        number of rows.
     * 
     * Performance: O(log s) -- where s is the smaller
     *              of the two numbers.
     */
    public static int minProduct(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        
        return minProductHelper(smaller, bigger);
    }
    
    /*
     * Treat the two numbers as rows and columns in a matrix.
     * Divide down the rows until we have one, at which point the
     * product is the column size. Then multiply these by 2 (add
     * together, and add another column is odd), until we reach the
     * number of rows.
     * 
     * rowCount and columnSize are not literally rows and columns in
     * a matrix, but it helps to think of them as such conceptually.
     */
    private static int minProductHelper(int rowCount, int columnSize) {
        if(rowCount == 0) {
            return 0;
        } else if (rowCount == 1) {
            // 1 x bigger = bigger
            return columnSize;
        }
        
        int rowCountDividedByTwo = rowCount >> 1;
        
        // Can be thought of as "half the product" of the current recursion --
        // the sum of elements in our matrix so far
        int numOfElementsSoFar = minProductHelper(rowCountDividedByTwo, columnSize);
        
        if(rowCount % 2 == 0) {
            return numOfElementsSoFar + numOfElementsSoFar;
        } else {
            // Odd numbers can be expressed as num*2 (i.e., num+num) + larger
            // E.g., 31 = 15 + 15 + 1 (= 15*2 + 1)
            // So 31*35 = 15 + 15 + 35 * 35 (= 15*2 + 35*1 * 35)
            // Another way to think of it:
            // We are doubling our numberOfElements so far each time until
            // we reach the number of rows, but if the number of rows we
            // are looking a is odd, add on another column after doubling.
            return numOfElementsSoFar + numOfElementsSoFar + columnSize;
        }
    }
    
    /*
     * Shifts disks from source to destination using the rules
     * of the Tower of Hanoi puzzle.
     * 
     * Point: Using the base case and build approach, we can
     * 		  observe that any disk can be moved to any
     *        other tower by using a third tower as a buffer.
     *        E.g., move 1 and 2 to tower 3:
     *         Move 1 to tower 2 (source > buffer)
     *         Move 2 to tower 3 (source > destination)
     *         Move 1 to tower 3 (buffer > destination)
     *        
     *        We can change which towers are source/buffer/destination
     *        with alternate recursive calls.
     */
    public static void towersOfHanoi(int numOfDisks, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> buffer) {
    	if(numOfDisks > 0) {
    		// Move disk from source to buffer, using destination as buffer
    		towersOfHanoi(numOfDisks-1, source, buffer, destination);
    		// Move disk from source to destination
    		destination.push(source.pop());
    		// Move disk from buffer to destination, using source as a buffer
    		towersOfHanoi(numOfDisks-1, buffer, destination, source);
    	}
    }
    
    public static ArrayList<String> permutationsWithoutDupes(String str) {
        ArrayList<String> permutations = new ArrayList<String>();
        permutationsWithoutDupes(str, permutations, str.length()-1);
        
        return permutations;
    }
    
    private static void permutationsWithoutDupes(String str, ArrayList<String> permutations, int charIndex) {
        if(charIndex == 0) {
            permutations.add(str.charAt(0)+"");
            return;
        }
        
        charIndex--;
        permutationsWithoutDupes(str, permutations, charIndex);
        charIndex++;
        
        char newChar = str.charAt(charIndex);
        
        ArrayList<String> newPermutations = new ArrayList<String>();
        for(String permutation: permutations) {
            for(int i = 0; i < permutation.length(); i++) {
                if(i == 0) {
                    newPermutations.add(newChar + permutation);
                } else {
                    String front = permutation.substring(0, i);
                    String back = permutation.substring(i, permutation.length());
                    newPermutations.add(front + newChar + back);
                }
            }
            
            newPermutations.add(permutation + newChar);
        }
       
        permutations.clear();
        permutations.addAll(newPermutations);
    }
}