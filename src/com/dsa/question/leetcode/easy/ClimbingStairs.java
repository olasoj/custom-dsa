package com.dsa.question.leetcode.easy;

import static java.lang.System.out;

public class ClimbingStairs {

    public static void main(String[] args) {
        out.println(algorithm(4));
    }

    public static int algorithm(int n) {
        int[] dp = new int[n + 1];
        boolean[] ready = new boolean[n + 1];
        return solve(n, ready, dp);
    }

    private static int solve(int n, boolean[] ready, int[] dp) {
        int counter = 0;
        if (ready[n]) return dp[n];

        if (n == 0) {
            return 1;
        } else {
            if (n - 1 >= 0) counter = counter + solve(n - 1, ready, dp);
            if (n - 2 >= 0) counter = counter + solve(n - 2, ready, dp);
        }

        ready[n] = true;
        dp[n] = counter;

        return counter;
    }
}
