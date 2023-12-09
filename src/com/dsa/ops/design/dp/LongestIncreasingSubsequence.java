package com.dsa.ops.design.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 5, 1, 7, 4, 8, 3};

        algorithm(arr);

        boolean solveMin = true;
        algorithm2(arr);
        algorithm1(arr, solveMin);
    }

    private static void algorithm2(int[] arr) {
        algorithm1(arr, false);
    }

    private static void algorithm1(int[] arr, boolean min) {

        boolean[] ready = new boolean[arr.length];
        int[] value = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (!ready[i]) {
                int solve = min ? solveMin(arr, i, ready, value) : solve(arr, i, ready, value);
                int result = min ? solve : solve + 1;
                out.println("Solved for item: " + arr[i] + ". Result: " + result);
            }
        }

        out.println(Arrays.toString(ready));
        out.println(Arrays.toString(value));
    }

    private static int solve(int[] arr, int index, boolean[] ready, int[] value) {
        int h = 0;

        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] > arr[index])
                h = Math.max(h, 1 + solve(arr, i, ready, value));
        }

        ready[index] = true;
        value[index] = h;
        return h;
    }

    private static int solveMin(int[] arr, int index, boolean[] ready, int[] value) {
        int h = Integer.MAX_VALUE;

        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] > arr[index]) {
                int solve = solve(arr, i, ready, value);
                h = Math.min(h, solve == Integer.MAX_VALUE ? solve : 1 + solve);
            }
        }

        ready[index] = true;
        value[index] = h;
        return h;
    }

    /**
     * Generate all increasing subsequence
     *
     * @param arr array
     */
    private static void algorithm(int[] arr) {
        List<List<Integer>> allIncreasingSubsequence = new ArrayList<>(arr.length);
        List<Integer> increasingSubsequence = new ArrayList<>(arr.length);

        for (int i = 0; i < arr.length; i++) {
            increasingSubsequence.add(arr[i]);
            solve(arr, i, allIncreasingSubsequence, increasingSubsequence);
            increasingSubsequence.remove(0);
        }
        out.println(allIncreasingSubsequence);
    }

    private static void solve(int[] arr, int index, List<List<Integer>> allIncreasingSubsequence, List<Integer> increasingSubsequence) {
        int length = arr.length;

        allIncreasingSubsequence.add(List.copyOf(increasingSubsequence));

        for (int i = index + 1; i < length; i++) {
            if (arr[index] < arr[i]) {
                increasingSubsequence.add(arr[i]);
                solve(arr, i, allIncreasingSubsequence, increasingSubsequence);
                increasingSubsequence.remove(increasingSubsequence.size() - 1);
            }

        }
    }
}
