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
import java.util.Queue;
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
     * Merge overlapping intervals Sort the intervals and then check of the start is
     * after the end.
     * 
     * Input: [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<Interval>();
        }

        LinkedList<Interval> merged = new LinkedList<Interval>();

        Collections.sort(intervals, new IntervalComparator());

        for (Interval interval : intervals) {
            // If the end comes before the start, add this
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            } else {
                // Otherwise it ends after the next element starts, so we have an overlap
                // (remember intervals are sorted y first element)
                // -- set the end of the last element to be the max of the two elements
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }

        return merged;
    }

    private static class IntervalComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval arg0, Interval arg1) {
            if (arg0.start < arg1.start) {
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
     * This is based on the power property: X^2n = (X^n)^2 So if we have X^n/2, how
     * do we get X^n?
     * 
     * Well, if n is even, we can use (X^n)^2 = X^2*n If n is odd, we can use
     * (X^n)^2 = X^n-1 = (X^n/2)^2 * x
     * 
     * Since we are halving n each time... Performance: O(log n) Space: O(log n) --
     * for recursive calls (store the result of x^n/2 O(log n) times
     * 
     */
    public static double myPow(double x, int n) {
        long N = n;

        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    private static double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }

        double half = fastPow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /*
     * Returns the set of unique letters in a string with the lowest lexicographical
     * order
     * 
     * The String S contains all of the unique characters We find the leftmost index
     * of the lowest character, and take the substring of the remaining characters.
     * 
     * To make sure we don't cut off the substring and lose characters, we decrement
     * the count of each character. Once the count for a character hits 0, we break
     * out.
     * 
     * This gives us the position of the smallest character we can take, while
     * leaving a substring the contains the remaining unique characters.
     * 
     * Examples: abbcab > bbcb > c abcabc > bcbc > cc
     * 
     * O(n) -- recurse 26*n, where n is the number of letters in the string
     */
    public static String removeDuplicateLetters(String s) {

        if (s.length() == 0) {
            // base case
            return "";
        }

        int[] counts = new int[26];
        int posOfSmallest = 0; // Position of the smallest char in the string

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

        String restOfStringWithCharRemoved = s.substring(posOfSmallest + 1).replaceAll("" + s.charAt(posOfSmallest),
                "");
        return s.charAt(posOfSmallest) + removeDuplicateLetters(restOfStringWithCharRemoved);
    }

    /*
     * Returns the length of the longest consecutive sequence Runtime: O(n)
     * 
     * Note: This looks like quadratic time, but look carefully at the for loop. We
     * only start iterating our while loop from the lowest number in a sequence, so
     * each element should only get checked once, giving us an O(2N) runtime. *
     */
    public static int longestConsecutive(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<Integer>();

        for (int num : nums) {
            numSet.add(num);
        }

        int maxSequence = 1;

        for (int num : numSet) {
            // This ensures we only examine every
            // number once, starting from the lowest
            // element in the sequence
            if (!numSet.contains(num - 1)) {
                int sequenceLength = 1;
                int currentNum = num;

                while (numSet.contains(currentNum + 1)) {
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
        if (matrix.length == 0) {
            return new ArrayList<Integer>();
        }

        List<Integer> spiralOrder = new ArrayList<Integer>();

        int startRow = 0;
        int endRow = matrix.length - 1;
        int startCol = 0;
        int endCol = matrix[0].length - 1;

        while (startRow <= endRow && startCol <= endCol) {

            // Add the top row
            for (int col = startCol; col <= endCol; col++) {
                spiralOrder.add(matrix[startRow][col]);
            }

            // Add the right col
            for (int row = startRow + 1; row <= endRow; row++) {
                spiralOrder.add(matrix[row][endCol]);

            }

            // If the start and end rows are the same,
            // or start and end columns are the same, then we've already
            // added these elements.
            if (startRow < endRow && startCol < endCol) {

                // Add the bottom row
                for (int col = endCol - 1; col > startCol; col--) {
                    spiralOrder.add(matrix[endRow][col]);
                }

                // Add left col
                for (int row = endRow; row > startRow; row--) {
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
     * Finds the next permutation that is the next lexagraphical ordering of
     * numbers.
     * 
     * 1. Find the smallest element starting from left (e.g., 1231 -- first smallest
     * is 2) 2. Go from the right, and find the first element larger than this
     * (e.g., 1231 -- next largest is 3) 3. Swap these 2 elements (e.g., 1321) 4.
     * Now to make it so it's the NEXT smallest permutation, sort everything after
     * the swap position (e.g., 1312)
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int firstSmallerNum = nums.length - 2;

        while (firstSmallerNum >= 0 && nums[firstSmallerNum + 1] <= nums[firstSmallerNum]) {
            firstSmallerNum--;
        }

        if (firstSmallerNum >= 0) {
            int nextLargerNum = nums.length - 1;
            while (nextLargerNum >= 0 && nums[nextLargerNum] <= nums[firstSmallerNum]) {
                nextLargerNum--;
            }

            swap(nums, firstSmallerNum, nextLargerNum);
        }
        // else nums are reverse sorted, there is
        // no next permutation, so we return the sorted array

        reverse(nums, firstSmallerNum + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;

        while (i < j) {
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
     * Finds the minimum number of perfect squares that can be used to make n.
     * 
     * Build up an array of the min perfect squares that can be used to build up
     * every element in the array.
     * 
     * Take away the number squared, and add the minimum number of ways we can make
     * the remainder, which we already calculated dynamically.
     * 
     * Dynamic programming approach.
     */
    public static int numSquares(int n) {
        int[] numOfSquares = new int[n + 1];

        Arrays.fill(numOfSquares, Integer.MAX_VALUE);
        numOfSquares[0] = 0;

        for (int target = 1; target <= n; target++) {
            for (int square = 1; square * square <= target; square++) {
                // Think about it: +1 for square^2, and then + the number of ways we can make
                // whatever is left over. Hence we derive target-(square*square)-1
                numOfSquares[target] = Math.min(numOfSquares[target], numOfSquares[target - square * square] + 1);
            }
        }

        return numOfSquares[n];
    }

    /*
     * Evaluates a string as an arithmetic expression
     * 
     * We can get away with using a single stack if we evaluate the expression at
     * each point, and just keep track of the sign instead of a stack of operators.
     * 
     * 1 + 3 = 1 + (1*3) = 4 (sign = 1) 1 - 3 = 1 + (-1*3) = -2 (sign = -1)
     */
    public static int calculator(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int result = 0;
        int sumSoFar = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // If we have a number, add it on to our current number
                sumSoFar = sumSoFar * 10 + (int) (c - '0');
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
                // If we have a left bracket, push on the result and sign (saving sum we got
                // before
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
        if (sumSoFar != 0) {
            result += sumSoFar * sign;
        }

        return result;
    }

    /*
     * Finds the first missing positive integer
     * 
     * This solution is very similar to the "mark using negatives" solution pattern.
     * Except since we already have negatives in the array, we shift each element
     * into its "correct" (i-1) place. So 1 goes into element 0, 2 goes into element
     * 1, etc.
     * 
     * Then we check through the array. The first element not to equal i+1 is the
     * missing element.
     * 
     * Time: O(N) 1, 2, 0 Space: O(1)
     */
    public int firstMissingPositive(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > 0 && arr[i] <= arr.length && arr[arr[i] - 1] != arr[i]) {
                // arr[i] > 0 : can we shift this element into 0+ index (remember it will be
                // swapped with i-1)
                // arr[i] <= arr.length : can we shift this element into an index < length of
                // the array
                // arr[arr[i]-1] != arr[i] : is this number already in the correct place?
                // Because of this last condition, we will swap at most n times.
                swap(arr, i, arr[i] - 1);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }

        // Everything is sorted -- missing is off the end of the array
        return arr.length + 1;

    }

    /*
     * Returns a string representing a decimal number given two integers
     * representing a fraction
     * 
     * 1. If either number (not both) are -ve, append '-' to the StringBuilder 2.
     * Convert both numbers to the absolute long value 3. Append the number before
     * the decimal part 4. If the remainder is 0, just return 5. Otherwise set up a
     * map of remainder to position in fractional part -- if we get a repeating
     * remainder, surround it by () and return 6. While the remainder != 0 -- Put it
     * in the map -- Multiply it by 10 -- Append the new remainder / denominator --
     * Set the remainder to be the new remainder
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
    
    /*
     * Counts the number of prime numbers less than n
     */
    public static int countPrimes(int n) {
        boolean [] notPrime = new boolean[n];
        
        int count = 0;
        
        for(int i = 2; i < n; i++) {
            if(!notPrime[i]) {
                // I.e., is prime...
                count++;
            }
            
            for(int j = 2; i*j < n; j++) {
                // Since we make make this index by multiplying i*j
                // it can't be prime. This results in some duplicate
                // work but is quicker than checking from scratch.
                notPrime[i*j] = true;
            }
        }
        
        return count;
    }
    
    /*
     * Returns the number of possible mappings for
     * a numerical string representing a series
     * of letters mapped to numbers. (A > 1, B > 2, etc.)
     * 
     * Input: "12"
	 * Output: 2
	 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
	 * 
	 * Basically we will use a dynamic programming approach starting from the back of the string.
     */
    public static int numDecodings(String s) {
    	if(s == null || s.length() == 0) {
    		return 0;
    	}
    	
    	int [] memo = new int[s.length()+1];
    	
    	memo[s.length()] = 1;
    	
    	// If the last char is 0, there are 0 ways to make this taking only 1
    	// char at a time. Otherwise we can get there taking 1 char at a time.
    	memo[s.length()-1] = s.charAt(s.length()-1) == '0' ? 0 : 1;
    	
    	for(int i = s.length()-2; i >= 0; i--) {
    		if(s.charAt(i) == '0') {
    			// We can't start/break from 0
    			continue;
    		}
    		
    		// If the substring between i and i+1 is <= 26, we will be able to make it in 2 ways (e.g., 2+2 or 22)
    		// Otherwise there is only one way we can make it (e.g., 3 + 3)
    		memo[i] = Integer.parseInt(s.substring(i, i+2)) <= 26 ? memo[i+1] + memo[i+2] : memo[i+1];
    	}
    	
    	return memo[0];
    }
    
    /*
     * Conway's Game of Life simulation
     * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
     * 
     * 1: Live
     * 2: Dead
     * 
     * So we can use 2 bits to represent [next state/current state]
     * 00 (dead < dead)
     * 01 (dead < live)
     * 10 (live < dead)
     * 11 (live < live)
     * 
     * current state & 1 = current state
     * current state >> 1 = next state
     */
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0) {
            return;
        }
        
        // First, iterate through the board
        // and set the second bit
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
               
                // Get the number of live neighbours
                int liveNeighbours = getLiveNeighbours(board, row, col);
            
                // To start, every 2nd bit is 0.
                // We only need to worry worry when it becomes 1.
                if(board[row][col] == 1 && liveNeighbours >= 2 && liveNeighbours <= 3) {
                    board[row][col] = 3;    // Second bit 1 (01 > 11)
                }
                
                if(board[row][col] == 0 && liveNeighbours == 3) {
                    board[row][col] = 2;    // Second bit 1 (00 > 10)
                }
            }
        }
        
        for (int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                board[row][col] >>= 1;  // Get the next state
            }
        }
    }
    
    private int getLiveNeighbours(int[][] board, int row, int col) {
        int liveNeighbours = 0;
        
        // The max/min checks here are to check for out of bounds
        for(int neighbourRow = Math.max(row-1, 0); neighbourRow <= Math.min(row+1,  board.length-1); neighbourRow++) {
            for(int neighbourCol = Math.max(col-1, 0); neighbourCol <= Math.min(col+1, board[0].length-1); neighbourCol++) {
                liveNeighbours += board[neighbourRow][neighbourCol] & 1;   // & 1 = Get current state
            }
        }
        
        liveNeighbours -= board[row][col] & 1;
        
        return liveNeighbours;
    }
    
    /*
     * Returns the permutations for an integer array
     */
    public static List<List<Integer>> getPermutations(int [] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	
    	if(nums.length == 0) {
    		return results;
    	}
    	
    	getPermutations(nums, results, new ArrayList<Integer>(), 0);
    	
    	return results;
    }
    
    private static void getPermutations(int [] nums, List<List<Integer>> permutations, List<Integer> permutation, int index) {
    	if(permutation.size() == nums.length) {
    		permutations.add(permutation);
    		return;
    	}
    	
    	// For each position in this permutation
    	for(int insertPosition = 0; insertPosition <= permutation.size(); insertPosition++) {
    		List<Integer> newPermutation = new ArrayList<Integer>(permutation);
    		// Insert the next number in every position
    		newPermutation.add(insertPosition, nums[index]);
    		// Recurse until we hit the size of the original array
    		getPermutations(nums, permutations, newPermutation, index+1);
    	}
    }
    
    /*
     * Generate String permutations recursively
     * (Non-base case and build method)
     * 
     * We can think of this like, take a prefix (E.g., A)
     * and then append every variant of the suffix (BC/CB) to it.
     * Then move on to the next prefix.
     * 
     * Backtracking -- pick a char, remove it from your string and recurse
     */
    public static List<String> getPermutations(String s) {
        
        List<String> results = new ArrayList<String>();
     
        getPermutations("", s, results);
        
        return results;
    }
    
    private static void getPermutations(String prefix, String suffix, List<String> results) {
        if(suffix.length() == 0) {
            results.add(prefix);
            return;
        }
        
        for(int i = 0; i < suffix.length(); i++) {
            getPermutations(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i+1, suffix.length()), results);
        }
    }
    
    /*
     * Get permutations of array using a simpler backtracking method
     * Like before, we take an element as a prefix, and then generate
     * the permutations of the suffix. Because we are using an array,
     * we have to clone it, and we swap elements.
     * 
     * 1. Swap the start and i...
     * 2. Generate permutations
     * 3. Swap start and i back
     */
    public static List<int[]> permutations(int [] arr) {
        List<int[]> results = new ArrayList<int[]>();
        
        permutations(arr, 0, results);
        
        return results;
    }
    
    private static void permutations(int [] arr, int start, List<int[]> results) {
        if(start == arr.length) {
            // Remember we have to clone the array to stop it being
            // modified after we add it
            results.add(arr.clone());
            return;
        }
        
        for(int i = start; i < arr.length; i++) {
            swapStatic(arr, start, i);
            permutations(arr, start+1, results);
            swapStatic(arr, start, i);
        }
    }
    
    private static void swapStatic(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /*
     * Returns the shortest path from a begin word to end word, using a BFS approach
     * 
     * 1. Set up a queue
     * 2. Add our start word to the queue
     * 3. While the queue is not empty...
     * 4. Remove the top of the queue...
     * 5. If it matches our end word, return the distance
     * 6. Otherwise, generate all permutations of this word
     * 7. If a permutation is in the dictionary, add it to the queue with distance+1, remove it from the dictionary
     *
     *  So the shortest path will be found. 
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       Set<String> wordSet = new HashSet<String>();
       
       for(String word: wordList) {
           wordSet.add(word);
       }
       
       return ladderLength(beginWord, endWord, wordSet);
    }
    
    private int ladderLength(String beginWord, String endWord, Set<String> wordSet) {
        LinkedList<WordDistance> queue = new LinkedList<WordDistance>();
        
        queue.add(new WordDistance(beginWord, 1));
        
        while(!queue.isEmpty()) {
            WordDistance top = queue.remove();
            String word = top.word;
            
            if(word.equals(endWord)) {
                return top.distance;
            }
            
            char[] arr = word.toCharArray();
            
            for(int i = 0; i < arr.length; i++) {
                for(char c = 'a'; c <= 'z'; c++) {
                    // Swap in the char to the array
                    char temp = arr[i];
                    arr[i] = c;
                    String newWord = new String(arr);
                    
                    if(wordSet.contains(newWord)) {
                        queue.add(new WordDistance(newWord, top.distance+1));
                        wordSet.remove(newWord);
                    }
                    
                    arr[i] = temp;
                }
            }
        }
        
        return 0;
    }
    
    private class WordDistance {
        String word;
        int distance;
        
        WordDistance(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
    }
    
    /*
     * Finds the max sum where elements cannot be adjacent
     * 
     * This is basically a dynamic programming question,
     * but since we only need to look at i-1 and i-2, we
     * can do it using just two variables.
     * 
     * 1. At nums[i], the max f(i) must be nums[i] (something is better than nothing)
     * 2. At nums[i+1], the max f(i) is either nums[i-1], or nums[i]
     * 3. Therefore, at nums[x], the max f(x) is Math.max(f(i-2) + nums[i], f(i-1))
     * 
     * Ex. [2, 7, 9, 3, 1]
     * 
     */
    public static int maxNonAdjacentSum(int [] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int prevMax = 0;        
        int currMax = 0;

        for(int i = 0; i < nums.length; i++) {
            int temp = currMax;
            currMax = Math.max(prevMax+nums[i], currMax);
            prevMax = temp;
        }
        
        return currMax;
    }
    
    /*
     * Traverse a tree, returning the list of node values
     * at each level. We could also use a recursive approach,
     * where we create a list for each level, and then index
     * into that list depending on the level (height) we are at.
     * 
     * This is basically like a breadth-first search.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        
        if(root == null) {
            return results;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            List<Integer> result = new ArrayList<Integer>();
            
            // Have to save this since queue will be modified
            // in the loop
            int nodesAtThisLevel = queue.size();
            
            for(int i = 0; i < nodesAtThisLevel; i++) {
                TreeNode node = queue.poll();
                
                if(node.left != null) {
                    queue.add(node.left);
                }
                
                if(node.right != null) {
                    queue.add(node.right);
                }
                
                result.add(node.val);
            }
            
            results.add(result);
        }
        
        return results;
        }
    
     /* Returns the common ancestor for 2 binary tree nodes
     * 
     * If the current subtree contains one of the nodes in the left
     * and one of the nodes in the right, then the answer is this subtree.
     * Otherwise the answer must be node that is not null.
     * 
     * (We will return from the first node we find, so that node must contain
     * the other node underneath it, if the other node was not found in the other
     * subtree)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	if(root == null || root == p || root == q) {
    		return root;
    	}
    	
    	TreeNode left = lowestCommonAncestor(root.left, p, q);
    	TreeNode right = lowestCommonAncestor(root.right, p, q);
    	
    	if(left == null) {
    		return right;
    	} else if (right == null) {
    		return left;
    	} else {
    		return root;
    	}
    }
    
    /*
     * DFS a matrix for a string
     */
    public boolean exists(char[][] board, String word) {
    	if(word == null || word.length() == 0) {
    		return false;
    	}
    	
    	boolean[][] visited = new boolean[board.length][board[0].length];
    	
    	for(int row = 0; row < board.length; row++) {
    		for(int col = 0; col < board[0].length; col++) {
    			if(board[row][col] == word.charAt(0) && search(board, row, col, visited, word, 0)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    public boolean search(char[][] board, int row, int col, boolean[][] visited, String word, int index) {
    	if(index == word.length()) {
    		return true;
    	}
    	
    	if(row < 0 || row >= board.length || col < 0 || col >= board[0].length 
    			|| visited[row][col] || board[row][col] != word.charAt(index)) {
    		return false;
    	}
    	
    	visited[row][col] = true;
    	
    	if(search(board, row-1, col, visited, word, index+1)
    			|| search(board, row, col+1, visited, word, index+1)
    			|| search(board, row, col-1, visited, word, index+1)
    			|| search(board, row+1, col, visited, word, index+1)) {
    		return true;
    	}
    	
    	visited[row][col] = false;
    	
    	return false;
     }
}