package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.pekoto.test.challenges.Interval;

public class LeetCode {

    /**
     * Find the length of the longest unique substring in a string
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int start = 0;
        int maxLength = 0;

        HashMap<Character, Integer> charPos = new HashMap<Character, Integer>();

        for (int end = 0; end < s.length(); end++) {
            if (charPos.containsKey(s.charAt(end))) {
                start = Math.max(charPos.get(s.charAt(end)), start);
            }

            maxLength = Math.max(end - start + 1, maxLength);
            charPos.put(s.charAt(end), end + 1);
        }

        return maxLength;
    }

    /**
     * Find the median of 2 sorted arrays
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums2.length < nums1.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = nums1.length;

        while (left <= right) {

            int xPartition = left + (right - left) / 2;
            int yPartition = ((nums1.length + nums2.length + 1) / 2) - xPartition;

            int leftXMax = (xPartition == 0) ? Integer.MIN_VALUE : nums1[xPartition - 1];
            int rightXMin = (xPartition == nums1.length) ? Integer.MAX_VALUE : nums1[xPartition];

            int leftYMax = (yPartition == 0) ? Integer.MIN_VALUE : nums2[yPartition - 1];
            int rightYMin = (yPartition == nums2.length) ? Integer.MAX_VALUE : nums2[yPartition];

            if (leftXMax <= rightYMin && leftYMax <= rightXMin) {
                if ((nums1.length + nums2.length) % 2 == 0) {
                    return (Math.max(leftXMax, leftYMax) + Math.min(rightXMin, rightYMin)) / 2.0;
                } else {
                    return Math.max(leftXMax, leftYMax);
                }
            } else if (leftXMax > rightYMin) {
                right = xPartition - 1;
            } else {
                left = xPartition + 1;
            }
        }

        // Error, arrays were unsorted
        return -1.0;
    }

    /*
     * Returns nearest integer square root
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int left = 1;
        int right = x;

        while (true) {
            int mid = left + (right - left) / 2;

            if (mid > x / mid) {
                // Sqrt must be <= x/sqrt
                right = mid - 1;
            } else if (mid + 1 > x / (mid + 1)) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
    }

    /*
     * RegEx matcher using bottom-up dynamic programming. * = 0 or move chars . =
     * any single char
     * 
     * Uses a boolean table to check if the string matches the pattern at each
     * point. The pattern is the rows, and the string is the column.
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] matches = new boolean[s.length() + 1][p.length() + 1];

        // An empty string matches and empty pattern
        matches[0][0] = true;

        // Set up the first row. This deals with patterns where
        // we could potentially match the empty string at various points
        // e.g., a* or a*b* or a*b*c*
        for (int i = 1; i < matches[0].length; i++) {
            // Remember we have a 1 offset before the pattern starts
            // a * b *
            // [ ] a * b *
            // T, F, T, F, T
            // 0, 1, 2, 3, 4
            if (p.charAt(i - 1) == '*') {
                matches[0][i] = matches[0][i - 2];
            }
        }

        for (int row = 1; row < matches.length; row++) {
            for (int col = 1; col < matches[0].length; col++) {
                if (p.charAt(col - 1) == '.' || p.charAt(col - 1) == s.charAt(row - 1)) {
                    // If the two chars match, or if the pattern is a .,
                    // then we can imagine just removing these 2 from the string and pattern
                    // -- i.e., use the value in the upper left.
                    matches[row][col] = matches[row - 1][col - 1];
                } else if (p.charAt(col - 1) == '*') {

                    // Can we match by just not taking any of the preceding character
                    matches[row][col] = matches[row][col - 2];

                    if (p.charAt(col - 2) == '.' || p.charAt(col - 2) == s.charAt(row - 1)) {
                        matches[row][col] = matches[row][col] || matches[row - 1][col];
                    }
                } else {
                    matches[row][col] = false;
                }
            }
        }

        return matches[s.length()][p.length()];
    }
    
    /*
     * Merge overlapping intervals
     * Sort the intervals and then check of the start is after the end.
     * 
     * Input:  [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0) {
            return new ArrayList<Interval>();
        }
        
        LinkedList<Interval> merged = new LinkedList<Interval>();
        
        Collections.sort(intervals, new IntervalComparator());
        
        for(Interval interval: intervals) {
            // If the end comes before the start, add this
            if(merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            } else {
                // Otherwise it ends after the next element starts, so we have an overlap (remember intervals are sorted y first element)
                // -- set the end of the last element to be the max of the two elements
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        
        return merged;
    }
    
    private static class IntervalComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval arg0, Interval arg1) {
            if(arg0.start < arg1.start) {
                return -1;
            } else if (arg0.start == arg1.start) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    
    /* 
     * Quickly computers the power of x^n using the fast power method
     * 
     * This is based on the power property: X^2n = (X^n)^2
     * So if we have X^n/2, how do we get X^n?
     * 
     * Well, if n is even, we can use (X^n)^2 = X^2*n
     * If n is odd, we can use (X^n)^2  = X^n-1 = (X^n/2)^2 * x 
     * 
     * Since we are halving n each time...
     * Performance: O(log n)
     * Space: O(log n) -- for recursive calls (store the result of x^n/2 O(log n) times
     * 
     */
    public static double myPow(double x, int n) {
        long N = n;
        
        if(N < 0) {
            x = 1/x;
            N = -N;
        }
        
        return fastPow(x, N);
    }
    
    private static double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        
        double half = fastPow(x, n/2);
        
        if(n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
    
    /* 
     * Returns the set of unique letters in a string
     * with the lowest lexicographical order
     * 
     * The String S contains all of the unique characters
     * We find the leftmost index of the lowest character, and take the substring
     * of the remaining characters.
     * 
     * To make sure we don't cut off the substring and lose characters, we decrement
     * the count of each character. Once the count for a character hits 0, we break out.
     * 
     * This gives us the position of the smallest character we can take, while leaving
     * a substring the contains the remaining unique characters.
     * 
     * Examples:
     * abbcab > bbcb > c
     * abcabc > bcbc > cc
     * 
     * O(n) -- recurse 26*n, where n is the number of letters in the string
     */
    public static String removeDuplicateLetters(String s) {
        
        if(s.length() == 0) {
            // base case
            return "";
        }
        
        int[] counts = new int[26];
        int posOfSmallest = 0;  // Position of the smallest char in the string
        
        // Get the counts for each letter
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            
            if (s.charAt(i) < s.charAt(posOfSmallest)) {
                posOfSmallest = i;
            }
            
            counts[s.charAt(i) - 'a']--;
            if (counts[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        
        List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        
        
        
        String restOfStringWithCharRemoved = s.substring(posOfSmallest + 1).replaceAll("" + s.charAt(posOfSmallest), "");
        return s.charAt(posOfSmallest) + removeDuplicateLetters(restOfStringWithCharRemoved);
    }

    /*
     * Returns the length of the longest consecutive sequence
     * Runtime: O(n)
     * 
     * Note: This looks like quadratic time, but look carefully at the for loop.
     * We only start iterating our while loop from the lowest number in a sequence,
     * so each element should only get checked once, giving us an O(2N) runtime.     * 
     */
    public static int longestConsecutive(int[] nums) {
        
        if(nums.length == 0) {
            return 0;
        }
        
        Set<Integer> numSet = new HashSet<Integer>();
        
        for(int num: nums) {
            numSet.add(num);
        }
        
        int maxSequence = 1;
        
        for(int num: numSet) {
            // This ensures we only examine every
            // number once, starting from the lowest
            // element in the sequence
            if(!numSet.contains(num-1)) {
                int sequenceLength = 1;
                int currentNum = num;
                
                while(numSet.contains(currentNum+1)) {
                    currentNum++;
                    sequenceLength++;
                }
                
                maxSequence = Math.max(maxSequence, sequenceLength);
            }
        }
        
        return maxSequence;
    }
    
    /*
     * Iterates through a matrix in spiral order
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
    	if(matrix.length == 0) {
    		return new ArrayList<Integer>();
    	}
    	
    	List<Integer> spiralOrder = new ArrayList<Integer>();
    	
    	int startRow = 0;
    	int endRow = matrix.length-1;
    	int startCol = 0;
    	int endCol = matrix[0].length-1;
    	
    	while(startRow <= endRow && startCol <= endCol) {
    		
    		// Add the top row
    		for(int col = startCol; col <= endCol; col++) {
    			spiralOrder.add(matrix[startRow][col]);
    		}
    		
    		// Add the right col
    		for(int row = startRow+1; row <= endRow; row++) {
    			spiralOrder.add(matrix[row][endCol]);
    			
    		}
    		
    		// If the start and end rows are the same,
    		// or start and end columns are the same, then we've already
    		// added these elements.
    		if(startRow < endRow && startCol < endCol) {
    			
    			// Add the bottom row
    			for(int col = endCol-1; col > startCol; col--) {
    				spiralOrder.add(matrix[endRow][col]);
    			}
    			
    			// Add left col
    			for(int row = endRow; row > startRow; row--) {
    				spiralOrder.add(matrix[row][startCol]);
    			}
    		}
    		
    		startRow++;
    		endRow--;
    		startCol++;
    		endCol--;
    	}
    	
    	return spiralOrder;
    }
    
    /*
     * Finds the next permutation that is the next
     * lexagraphical ordering of numbers.
     * 
     * 1. Find the smallest element starting from left (e.g., 1231 -- first smallest is 2)
     * 2. Go from the right, and find the first element larger than this (e.g., 1231 -- next largest is 3)
     * 3. Swap these 2 elements (e.g., 1321)
     * 4. Now to make it so it's the NEXT smallest permutation, sort everything after the swap position
     *    (e.g., 1312)
     */
    public void nextPermutation(int[] nums) {
    	if(nums.length == 0) {
    		return;
    	}
    	
    	int firstSmallerNum = nums.length-2;
    	
    	while(firstSmallerNum >= 0 && nums[firstSmallerNum+1] <= nums[firstSmallerNum]) {
    		firstSmallerNum--;
    	}
    
    	if(firstSmallerNum >= 0) {
    		int nextLargerNum = nums.length-1;
    		while(nextLargerNum >= 0 && nums[nextLargerNum] <= nums[firstSmallerNum]) {
    			nextLargerNum--;
    		}
    		
    		swap(nums, firstSmallerNum, nextLargerNum);
    	}
    	// else nums are reverse sorted, there is
    	// no next permutation, so we return the sorted array
    	
    	reverse(nums, firstSmallerNum+1);
    }
    
    private void reverse(int [] nums, int start) {
    	int i = start;
    	int j = nums.length-1;
    	
    	while(i < j) {
    		swap(nums, i, j);
    		i++;
    		j--;
    	}
    }
    
    private void swap(int[] nums, int a, int b) {
    	int temp = nums[a];
    	nums[a] = nums[b];
    	nums[b] = temp;
    }
    
    /*
     * Finds the minimum number of perfect squares that can
     * be used to make n.
     * 
     * Build up an array of the min perfect squares that can be used to build up
     * every element in the array.
     * 
     * Take away the number squared, and add the minimum number of ways we can make the remainder,
     * which we already calculated dynamically.
     * 
     * Dynamic programming approach.
     */
    public static int numSquares(int n) {
    	int [] numOfSquares = new int[n+1];
    	
    	Arrays.fill(numOfSquares, Integer.MAX_VALUE);
    	numOfSquares[0] = 0;
    	
    	for(int target = 1; target <= n; target++) {
    		for(int square = 1; square*square <= target; square++) {
    			// Think about it: +1 for square^2, and then + the number of ways we can make
    			// whatever is left over. Hence we derive target-(square*square)-1
    			numOfSquares[target] = Math.min(numOfSquares[target], numOfSquares[target-square*square]+1);
    		}
    	}
    	
    	return numOfSquares[n];
    }
    
    /* 
     * Evaluates a string as an arithmetic expression
     * 
     * We can get away with using a single stack if we evaluate the expression at each point,
     * and just keep track of the sign instead of a stack of operators.
     * 
     * 1 + 3 = 1 + (1*3)  = 4 (sign = 1)
     * 1 - 3 = 1 + (-1*3) = -2 (sign = -1)
     */
    public static int calculator(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int result = 0;
        int sumSoFar = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(Character.isDigit(c)) {
                // If we have a number, add it on to our current number
                sumSoFar = sumSoFar * 10 + (int)(c - '0');
            } else if (c == '+') {
                // If we have a plus, add on our sum so far * sign, then reset them
                result += sumSoFar * sign;
                
                sign = 1;
                sumSoFar = 0;
            } else if (c == '-') {
                // If we have a minus, add on our sum so far * sign, then set sign to -1
                // so next number will be subtracted
                result += sumSoFar * sign;
                
                sign = -1;
                sumSoFar = 0;
            } else if (c == '(') {
                // If we have a left bracket, push on the result and sign (saving sum we got before
                // this expression, then reset the result and sign)
                
                stack.push(result);
                stack.push(sign);
                
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Once we get to the end of the expression,
                // add on the last value
                // reset the sum so far and sign
                // multiply it by the sign before this expression
                // add the result to the sign before this expression
                result += sumSoFar * sign;
                
                sign = 1;
                sumSoFar = 0;
                
                result *= stack.pop();
                result += stack.pop();
            }
        }
        
        // Deal with the last digits, if we have any
        if(sumSoFar != 0) {
            result += sumSoFar * sign;
        }
        
        return result;
    }
    
    /*
     * Returns a string representing a decimal number
     * given two integers representing a fraction
     * 
     * 1. If either number (not both) are -ve, append '-' to the StringBuilder
     * 2. Convert both numbers to the absolute long value
     * 3. Append the number before the decimal part
     * 4. If the remainder is 0, just return
     * 5. Otherwise set up a map of remainder to position in fractional part
     * 		-- if we get a repeating remainder, surround it by () and return
     * 6. While the remainder != 0
     * 		-- Put it in the map
     * 		-- Multiply it by 10
     * 		-- Append the new remainder / denominator
     * 		-- Set the remainder to be the new remainder
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        
        // If either one is negative (not both)
        // then the result will be -ve
        if (numerator < 0 ^ denominator < 0) {
            stringBuilder.append("-");
        }
        
        // Convert to Long or else abs(-2147483648) overflows
        // Also, since we already checked for -ve, make them positive.
        long longNumerator = Math.abs(Long.valueOf(numerator));
        long longDenominator = Math.abs(Long.valueOf(denominator));
        
        // Append the part before the decimal point
        stringBuilder.append(String.valueOf(longNumerator / longDenominator));
        
        long remainder = longNumerator % longDenominator;
        
        if (remainder == 0) {
            return stringBuilder.toString();
        }
        
        stringBuilder.append(".");
        
        // Map of remainder to position in fractional part
        Map<Long, Integer> map = new HashMap<>();
        
        while (remainder != 0) {
            
        	if (map.containsKey(remainder)) {
        		// We have a repeating fraction -- this already exists
                stringBuilder.insert(map.get(remainder), "(");
                stringBuilder.append(")");
                break;
            }
        	
            map.put(remainder, stringBuilder.length());
            remainder *= 10;
            stringBuilder.append(String.valueOf(remainder / longDenominator));
            remainder %= longDenominator;
        }
        
        return stringBuilder.toString();
    }
}