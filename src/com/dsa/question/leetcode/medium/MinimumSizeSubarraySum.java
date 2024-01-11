package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public static int minSubArrayLen(int target, int[] nums) {

        int minimalLength = 0;

        int right = 0;
        int left = 0;
        int windowWeight = 0;

        while (right < nums.length && windowWeight < target)
            windowWeight = windowWeight + nums[right++];

        if (windowWeight >= target) minimalLength = right - left;

        while (right < nums.length || (left < right && windowWeight >= target)) {

            if (windowWeight >= target)
                windowWeight = windowWeight - nums[left++];
            else
                windowWeight = windowWeight + nums[right++];

            if (windowWeight >= target && right >= left)
                minimalLength = Math.min(minimalLength, right - left);
        }

        return minimalLength;

    }
}
