package com.dsa.question.leetcode.easy;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int counter = 0;
        for (int num : nums) {

            for (int j = 0; j < nums.length; j++) {
                if (j != counter && (nums[j] + num == target)) {
                    return new int[]{counter, j};
                }
            }
            counter++;
        }
        return new int[0];
    }
}
