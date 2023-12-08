package com.dsa.ops.design.completesearch;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class GeneratingSubsets {
    static final List<List<Integer>> subsets = new ArrayList<>();


    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2};

        algorithm1(arr);
        algorithm2(arr);
    }

    private static void algorithm1(int[] arr) {
        search(0, arr, new ArrayList<>());
        out.println(subsets);
    }

    private static void search(int k, int[] arr, List<Integer> subset) {
        int n = arr.length;
        if (k == n) {
            // process subset
            subsets.add(List.copyOf(subset));
        } else {
            search(k + 1, arr, subset);
            subset.add(arr[k]);
            search(k + 1, arr, subset);
            subset.remove(subset.size() - 1);
        }
    }

    private static void algorithm2(int[] arr) {
        List<List<Integer>> subsets = new ArrayList<>();

        int n = arr.length;
        for (int b = 0; b < (1 << n); b++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (b < (1 << i)) subset.add(i);
            }
            subsets.add(subset);
        }

        out.println(subsets);
    }
}
