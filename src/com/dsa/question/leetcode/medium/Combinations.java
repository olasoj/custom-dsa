package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Combinations {

    public static void main(String[] args) {
        List<List<Integer>> combine = combine(4, 2);
        out.println(combine);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> currentC = new ArrayList<>();

        solve(combinations, currentC, n, k, 1);

        return combinations;
    }

    private static void solve(
            List<List<Integer>> combinations,
            List<Integer> currentC,
            int n, int k, int i) {

        if (currentC.size() == k) {
            combinations.add(List.copyOf(currentC));
        } else if (currentC.size() < k) {

            for (; i <= n; i++) {
                currentC.add(i);
                solve(combinations, currentC, n, k, i + 1);
                currentC.remove(currentC.size() - 1);
            }
        }
    }
}
