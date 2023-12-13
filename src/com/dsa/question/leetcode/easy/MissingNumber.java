package com.dsa.question.leetcode.easy;

import java.util.Arrays;

import static java.lang.System.out;

public class MissingNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 0, 1};
        int[] arr2 = new int[]{0, 1};

        int x = 10;
        for (int i = 31; i >= 0; i--) {
            int i1 = x & (1 << i);
            out.println(i1);
//            if (i1) out.println("1");
//            else out.println("0");
        }

        out.println(algorithm(arr));
        out.println(algorithm(arr2));
    }

    private static int algorithm(int[] arr) {
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            if (i != a) return i;
        }

        if (arr[arr.length - 1] != arr.length) return arr.length;
        return arr[arr.length - 1];
    }
}
