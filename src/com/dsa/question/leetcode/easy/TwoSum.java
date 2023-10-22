package com.dsa.question.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        TwoSum twoSum = new TwoSum();

        int[] ints = twoSum.twoSum(nums, 6);
        int[] ints2 = twoSum.twoSum2(nums, 6);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(ints2));
    }

    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] + nums[i] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum2(int[] nums, int target) {

        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }


    //Medium
    public int[] twoSumNonUnique(int[] nums, int target) {

        int[] indices = new int[2];

        if (nums == null || nums.length < 2) return indices;

        int left = 0;
        int right = (target < nums[nums.length / 2]) ? nums.length / 2 : nums.length - 1;

        while (left < right) {
            int v = nums[left] + nums[right];
            if (v == target) {
                indices[0] = left + 1;
                indices[1] = right + 1;
                break;
            } else if (v > target) {
                right--;
            } else {
                left++;
            }
        }
        return indices;
    }
}
