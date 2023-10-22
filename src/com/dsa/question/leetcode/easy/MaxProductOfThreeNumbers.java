package com.dsa.question.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class MaxProductOfThreeNumbers {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, 9, 7, 22, 12, 3, 44, 43};
        Solution solution = new Solution();
        int i = solution.maximumProduct(nums);
        out.println(i);

        int[] primitiveArray = new int[]{2, 1, 0, -2, -5};

        Comparator<Integer> comparator = Comparator.comparingInt(Math::abs);

        int ints = IntStream.of(primitiveArray)
                .boxed()
                .sorted(comparator.reversed())
                .mapToInt(Integer::valueOf)
                .limit(3)
                .reduce((left, right) -> left * right)
                .getAsInt();

        out.println("ints = " + ints);

    }
}

class Solution {
    public int maximumProduct(int[] nums) {

        int length = nums.length;
        //If the nums size is less 3 return 0
        if (length < 3) return 0;

        Arrays.sort(nums);

        int checkNegativeNumbers = nums[0] * nums[1] * ((nums[2] < 0) ? nums[length - 1] : nums[2]); //We dont want a negative
        int checkPositiveResult = nums[length - 1] * nums[length - 2] * nums[length - 3];

        return Math.max(
                checkNegativeNumbers,
                checkPositiveResult
        );
    }
}
