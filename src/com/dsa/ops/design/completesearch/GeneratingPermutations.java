package com.dsa.ops.design.completesearch;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class GeneratingPermutations {

    public static void main(String[] args) {

        int[] arr = new int[]{0, 1, 2};

        algorithm1(arr);
        algorithm2(arr);
    }

    private static void algorithm1(int[] arr) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();

        search(arr, permutation, permutations, new boolean[arr.length]);
        out.println(permutations);
    }

    private static void search(int[] arr, List<Integer> permutation, List<List<Integer>> permutations, boolean[] chosen) {
        int n = arr.length;  //Change to return permutation of small size
        if (permutation.size() == n) {
            // process permutation
            permutations.add(List.copyOf(permutation));
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (chosen[i]) continue;
                chosen[i] = true;
                permutation.add(arr[i]);
                search(arr, permutation, permutations, chosen);
                chosen[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    private static void algorithm2(int[] arr) {
        //TODO
    }
}
