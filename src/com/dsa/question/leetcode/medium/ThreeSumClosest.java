package com.dsa.question.leetcode.medium;

import com.dsa.misc.Factorial;

import java.util.*;

import static java.lang.System.out;

public class ThreeSumClosest {

    private static int expectedResultSize = 0;

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 2, 1, -4};
        int t = 1;
        int[] arr2 = new int[]{0, 0, 0};
        int t2 = 0;

        int[] arr3 = new int[]{1, 1, 1, 0};
        int t3 = -100;

        out.println(algorithm1(arr, t));
        out.println(algorithm1(arr2, t2));
        out.println(algorithm1(arr3, t3));
    }

    private static int algorithm1(int[] arr, int t2) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();

        expectedResultSize = Factorial.factCorecursion(arr.length) / Factorial.factCorecursion(3);
        SortedMap<Integer, Integer> perWithValues = new TreeMap<>();

        search(arr, permutation, permutations, new boolean[arr.length], t2, perWithValues, 0);

        out.println(permutations);
        out.println(perWithValues);


        return Optional.ofNullable(perWithValues.get(perWithValues.firstKey())).orElse(-1);
    }

    private static boolean search(int[] arr, List<Integer> permutation, List<List<Integer>> permutations, boolean[] chosen, int target, Map<Integer, Integer> perWithValues, int result) {
        if (permutation.size() == 3) {
            // process permutation
            List<Integer> copyOf = List.copyOf(permutation);
            permutations.add(copyOf);
            perWithValues.putIfAbsent(target - result, result);
            return perWithValues.size() == expectedResultSize || target - result == 0;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (chosen[i]) continue;
                chosen[i] = true;
                permutation.add(arr[i]);
                boolean search = search(arr, permutation, permutations, chosen, target, perWithValues, result + arr[i]);
                chosen[i] = false;
                permutation.remove(permutation.size() - 1);
                if (search) return true;
            }
        }

        return false;
    }

}
