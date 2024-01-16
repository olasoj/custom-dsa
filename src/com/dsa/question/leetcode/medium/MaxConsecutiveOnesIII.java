package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        MaxConsecutiveOnesIII max = new MaxConsecutiveOnesIII();

        int longestOnes = max.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2);
        out.println(longestOnes);

        int longestOnes2 = max.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
        out.println(longestOnes2);

        int longestOnes3 = max.longestOnes(new int[]{0, 0, 1, 1, 1, 0, 0}, 0);
        out.println(longestOnes3);
    }

    public int longestOnes(int[] nums, int k) {
        int numsL = nums.length;
        int tempK = k;
        int left = 0;
        int right = 0;
        int result = 0;

        while (right < numsL) {

            while (right < numsL && (nums[right] == 0) && tempK == 0) {
                right++;
                left = right;
            }

            while (right < numsL && (nums[right] == 1 || k > 0)) {
                if (nums[right] == 0) k--;
                right++;
            }

            result = Math.max(result, (right - left));

            while (k == 0 && tempK != 0 && left < right) {
                if (nums[left] == 0) k++;
                left++;
            }

        }

        return result;
    }
}
