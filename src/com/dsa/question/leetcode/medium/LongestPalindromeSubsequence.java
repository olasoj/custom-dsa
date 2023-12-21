package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class LongestPalindromeSubsequence {

    public static void main(String[] args) {
        algorithm("ABBDCACB");
    }

    private static int algorithm(String s) {
        int solve = solve(s, 0, s.length() - 1);
        out.println(solve);
        return solve;
    }


    private static int solve(String s, int i, int j) {

        int h = 0;

        if (i > j) {
            return 0;
        } else if (i == j) {
            return 1;
        } else {
            if (s.charAt(i) == s.charAt(j)) {
                h = Math.max(h, 2 + solve(s, i + 1, j - 1));
            } else {
                h = Math.max(h, solve(s, i, j - 1));
                h = Math.max(h, solve(s, i + 1, j));
            }
        }

        return h;
    }
}
