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
}