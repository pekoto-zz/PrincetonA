package com.pekoto.challenges;

public class LeetCode {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums2.length < nums1.length) {
            // Ensure nums1 is the shorter array
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = nums1.length;

        // Essentially perform a kind of binary search on the smaller array
        // to find a partition where the sum of left elements and right elements
        // in both arrays are equal, and the conditions for a median are satisfied.
        while(left <= right) {

            int nums1Partition = left + right/2;
            // add +1 to make dealing with odd and even arrays easier
            int nums2Partition = (nums1.length + nums2.length + 1)/2 - nums1Partition;

            // if numsParition == 0, it means there are no elements on the left for this array, so use -INF
            // if numsParition == length of array, it means there are no elements on the right, so use +INF for right
            int nums1LeftMax = (nums1Partition == 0) ? Integer.MIN_VALUE : nums1[nums1Partition - 1];
            int nums1RightMin = (nums1Partition == nums1.length) ? Integer.MAX_VALUE : nums1[nums1Partition];

            int nums2LeftMax = (nums2Partition == 0) ? Integer.MIN_VALUE : nums2[nums2Partition - 1];
            int nums2RightMin = (nums2Partition == nums2.length) ? Integer.MAX_VALUE : nums2[nums2Partition];

            // Now check to see if we've found the partition that splits the arrays in a way that
            // satisfies the conditions for finding the median
            if(nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {

                // In a sorted array, the max element of the left sides, or min of the right side mins would be in the middle

                // Even array
                if((nums1.length + nums2.length) % 2 == 0) {
                    return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))/2;
                } else {
                    // Odd array
                    return Math.max(nums1LeftMax, nums2LeftMax);
                }
            } else if (nums1LeftMax > nums2RightMin) {
                // The smaller array partition is great than the min of array 2's right side
                // so move the partition left (to a smaller element)
                right = nums1Partition-1;
            } else {
                // The larger array's LHS max is > smaller array's RHS min, so move the smaller array's partition right
                left = nums1Partition+1;
            }
        }

        // If we get here, the arrays were not sorted -- some error
        return -1;
    }
}
