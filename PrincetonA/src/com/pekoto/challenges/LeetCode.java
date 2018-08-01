package com.pekoto.challenges;

import java.util.HashMap;

public class LeetCode {

    /**
     * Find the length of the longest unique substring in a string
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int start = 0;
        int maxLength = 0;
        
        HashMap<Character, Integer> charPos = new HashMap<Character, Integer>();
        
        for(int end = 0; end < s.length(); end++) {
            if(charPos.containsKey(s.charAt(end))) {
                start = Math.max(charPos.get(s.charAt(end)), start);
            }
            
            maxLength = Math.max(end-start+1, maxLength);
            charPos.put(s.charAt(end), end+1);
        }
        
        return maxLength;
    }

    
    /**
     * Find the median of 2 sorted arrays
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums2.length < nums1.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int left = 0;
        int right = nums1.length;
        
        while(left <= right) {
            
            int xPartition = left + (right-left)/2;
            int yPartition = ((nums1.length + nums2.length + 1)/2) - xPartition;
            
            int leftXMax = (xPartition == 0) ? Integer.MIN_VALUE : nums1[xPartition-1];
            int rightXMin = (xPartition == nums1.length) ? Integer.MAX_VALUE : nums1[xPartition];
            
            int leftYMax = (yPartition == 0) ? Integer.MIN_VALUE : nums2[yPartition-1];
            int rightYMin = (yPartition == nums2.length) ? Integer.MAX_VALUE : nums2[yPartition];

            if(leftXMax <= rightYMin && leftYMax <= rightXMin) {
                if((nums1.length+nums2.length) % 2 == 0) {
                    return (Math.max(leftXMax, leftYMax) + Math.min(rightXMin, rightYMin)) / 2.0;
                } else {
                    return Math.max(leftXMax, leftYMax);
                }
            } else if (leftXMax > rightYMin) {
                right = xPartition-1;
            } else {
                left = xPartition+1;
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
            int mid = left + (right - left)/2;
            
            if (mid > x/mid) {
                // Sqrt must be <= x/sqrt
                right = mid - 1;
            } else if (mid + 1 > x/(mid + 1)) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
    }
    
    /*
     *  RegEx matcher using bottom-up dynamic programming.
     *  * = 0 or move chars
     *  . = any single char
     *  
     *  Uses a boolean table to check if the string matches the pattern
     *  at each point. The pattern is the rows, and the string is the column.
     */
    public static boolean isMatch(String s, String p) {
        boolean [][] matches = new boolean[s.length()+1][p.length()+1];
        
        // An empty string matches and empty pattern
        matches[0][0] = true;
        
        // Set up the first row. This deals with patterns where
        // we could potentially match the empty string at various points
        // e.g., a* or a*b* or a*b*c*
        for(int i = 1; i < matches[0].length; i++) {
            // Remember we have a 1 offset before the pattern starts
            //  a  *  b  *
            // [ ] a  *  b  *
            //  T, F, T, F, T
            //  0, 1, 2, 3, 4
            if(p.charAt(i-1) == '*') {
                matches[0][i] = matches[0][i-2];
            }
        }
        
        for(int row = 1; row < matches.length; row++) {
            for(int col = 1; col < matches[0].length; col++) {
                if(p.charAt(col-1) == '.' || p.charAt(col-1) == s.charAt(row-1)) {
                    // If the two chars match, or if the pattern is a .,
                    // then we can imagine just removing these 2 from the string and pattern
                    // -- i.e., use the value in the upper left.
                    matches[row][col] = matches[row-1][col-1];
                } else if(p.charAt (col-1) == '*') {
                    
                    // Can we match by just not taking any of the preceding character
                    matches[row][col] = matches[row][col-2];
                    
                    // If the char to match is a ., or if it matches the char in the string,
                    // We could also match by checking the row above --
                    if(p.charAt(col-2) == '.' || p.charAt(col-2) == s.charAt(row-1)) {
                        matches[row][col] = matches[row][col] || matches[row-1][col];
                    }
                } else {
                    matches[row][col] = false;
                }
            }
        }
        
        return matches[s.length()][p.length()];
    }
}