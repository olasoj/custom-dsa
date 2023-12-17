package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TargetSum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        out.println(algorithm(arr, target));
        out.println(algorithm2(arr, target));
    }

    private static int algorithm(int[] arr, int target) {
        List<Integer> permutation = new ArrayList<>(arr.length);
        List<List<Integer>> permutations = new ArrayList<>(arr.length);

        int result = 0;
        solve(arr, target, permutations, permutation, result, 0);
        out.println(permutations);
        return permutations.size();
    }

    private static void solve(int[] arr, int target, List<List<Integer>> permutations, List<Integer> permutation, int result, int currentIndex) {
        if (arr.length == currentIndex) {
            if (target == result) {
                permutations.add(List.copyOf(permutation));
            }
        } else {
            permutation.add(+arr[currentIndex]);
            solve(arr, target, permutations, permutation, result + arr[currentIndex], currentIndex + 1);
            permutation.remove(permutation.size() - 1);

            permutation.add(-arr[currentIndex]);
            solve(arr, target, permutations, permutation, result - arr[currentIndex], currentIndex + 1);
            permutation.remove(permutation.size() - 1);

        }
    }

    private static int algorithm2(int[] arr, int target) {
        Map<String, Integer> value = new HashMap<>();
        return solve2(arr, target, 0, 0, value);
    }

    private static int solve2(int[] arr, int target, int result, int currentIndex, Map<String, Integer> value) {
        int counter = 0;

        String key = result + "-" + currentIndex;
        if (value.containsKey(key)) return value.get(key);
        if (arr.length == currentIndex) {
            if (target == result) {
                return 1;
            }
            return 0;
        } else {
            counter = counter + solve2(arr, target, result + arr[currentIndex], currentIndex + 1, value);
            counter = counter + solve2(arr, target, result - arr[currentIndex], currentIndex + 1, value);
        }

        value.put(key, counter);
        return counter;
    }
}
