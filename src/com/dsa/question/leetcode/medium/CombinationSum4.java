package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class CombinationSum4 {

    public static void main(String[] args) {
        out.println(algorithm(new int[]{
                1, 2, 3
        }, 35));

        out.println(algorithm(new int[]{
                4, 2, 3
        }, 20));
    }

    private static int algorithm(int[] nums, int target) {
        int[] dp = new int[target + 1];
        boolean[] ready = new boolean[target + 1];

        return solve(nums, target, dp, ready);
    }

    private static int solve(int[] nums, int target, int[] dp, boolean[] ready) {

        int counter = 0;

        if (target >= 0 && ready[target]) return dp[target];

        if (target == 0) {
            return 1;
        } else if (target > 0) {
            for (int num : nums) {
                counter = counter + solve(nums, target - num, dp, ready);
            }
        }

        if (target >= 0) dp[target] = counter;
        if (target >= 0) ready[target] = true;

        return counter;
    }
}
