package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class FindAllDuplicatesInAnArray {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        out.println(algorithm(arr));
    }

    private static List<Integer> algorithm(int[] arr) {

        List<Integer> ans = new ArrayList<>(arr.length);
        boolean[] seen = new boolean[arr.length + 1];

        for (int a : arr) {
            if (seen[a]) ans.add(a);
            else seen[a] = true;
        }

        return ans;
    }
}
