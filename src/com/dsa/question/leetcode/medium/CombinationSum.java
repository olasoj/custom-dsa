package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> lists = combinationSum(candidates, target);
        out.println(lists);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> comboSum = new ArrayList<>();
        solve(result, comboSum, candidates, target, 0, 0);
        return result;
    }


    private static void solve(
            List<List<Integer>> result,
            List<Integer> comboSum,
            int[] candidates, int target,
            int acc, int i
    ) {

        if (target == acc) {
            result.add(List.copyOf(comboSum));
        } else if (acc < target) {
            for (; i < candidates.length; i++) {
                int candidate = candidates[i];

                comboSum.add(candidate);
                solve(result, comboSum, candidates, target, acc + candidate, i);
                comboSum.remove(comboSum.size() - 1);
            }
        }

    }
}
