package com.dsa.question.hackerank.cf;

import java.util.List;

import static java.lang.System.out;

public class ProductDefect {

    public static void main(String[] args) {
        List<List<Integer>> lists = List.of(
                List.of(1, 1, 1),
                List.of(1, 1, 0),
                List.of(1, 0, 1)
        );


        List<List<Integer>> list2 = List.of(
                List.of(0, 1, 1),
                List.of(1, 1, 0),
                List.of(1, 0, 1)
        );

        int largestSquareSize = findLargestSquareSize(lists);
        int largestSquareSize2 = findLargestSquareSize(list2);

        out.println(largestSquareSize);
        out.println(largestSquareSize2);

    }

    public static int findLargestSquareSize(List<List<Integer>> samples) {
        // Write your code here
        int[][] dp = new int[samples.size() + 1][samples.get(0).size() + 1];
        boolean[][] ready = new boolean[samples.size() + 1][samples.get(0).size() + 1];

        int ans = 0;
        for (int i = 0; i < samples.size(); i++) {
            for (int j = 0; j < samples.get(0).size(); j++) {
                int r = solve(samples, i, 0, ready, dp, i);
                ans = Math.max(ans, r - i);
            }
        }

        return ans;
    }

    private static int solve(List<List<Integer>> samples, int i, int j, boolean[][] ready, int[][] dp, int start) {

        int ans = 0;
        int iL = samples.size();
        int jL = samples.get(0).size();

        if (ready[i][j]) return dp[i][j];

        if (samples.get(i).get(j) != 0 && i == j && i != start) {
            return i + 1;
        } else {
            if (i + 1 < iL && samples.get(i + 1).get(j) == 1) {
                ans = Math.max(ans, solve(samples, i + 1, j, ready, dp, start));
            }

            if (j + 1 < jL && samples.get(i).get(j + 1) == 1) {
                ans = Math.max(ans, solve(samples, i, j + 1, ready, dp, start));
            }
        }

        ready[i][j] = true;
        dp[i][j] = ans;

        return ans;
    }


}
