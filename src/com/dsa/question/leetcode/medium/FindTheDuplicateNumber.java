package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 2, 2};
        int[] arr2 = new int[]{3, 1, 3, 4, 2};
        out.println(algorithm(arr));
        out.println(findDuplicateMark(arr));
        out.println(algorithm(arr2));
        out.println(findDuplicateMark(arr2));
    }

    private static int algorithm(int[] arr) {
        boolean[] seen = new boolean[arr.length + 1];

        for (int a : arr) {
            if (seen[a]) return a;
            seen[a] = true;
        }

        return -1;
    }

    // Visited
    public static int findDuplicateMark(int[] nums) {
        int len = nums.length;
        for (int num : nums) {
            int idx = Math.abs(num);
            if (nums[idx] < 0) {
                return idx;
            }
            nums[idx] = -nums[idx];
        }

        return len;
    }
}
