package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class HouseRobber {

    public static void main(String[] args) {
        int[] nums = new int[]{
                1, 2, 3, 1
        };

        out.println(algorithm(nums));
    }

    private static int algorithm(int[] nums) {
        int numsL = nums.length;
        int[] dp = new int[numsL + 1];
        boolean[] ready = new boolean[numsL + 1];
        return solve(nums, 0, dp, ready);
    }

    private static int solve(int[] nums, int startIndex, int[] dp, boolean[] ready) {
        int h = 0;

        if (startIndex < nums.length && ready[startIndex]) return dp[startIndex];

        if (startIndex < nums.length)
            h = Math.max(h, nums[startIndex] + solve(nums, startIndex + 2, dp, ready));

        if (startIndex + 1 < nums.length)
            h = Math.max(h, nums[startIndex + 1] + solve(nums, startIndex + 3, dp, ready));

        if (startIndex < nums.length) {
            ready[startIndex] = true;
            dp[startIndex] = h;
        }

        return h;
    }

}
