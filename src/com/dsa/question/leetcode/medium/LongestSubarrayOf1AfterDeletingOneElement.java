package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class LongestSubarrayOf1AfterDeletingOneElement {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,1,0,1,1,0,1};
        out.println(longestSubarray(nums));
    }

    public static int longestSubarray(int[] nums) {
        int numsL = nums.length;
        if(numsL <=  1) return 0;
        int right = 0;
        int left = 0;
        int k = 1;
        int result = 0;

        while(right < numsL){

            while (right < numsL && (k > 0 || nums[right] == 1)) {
                if(nums[right] == 0) k--;
                right++;
            }

            result = Math.max(result, (right - left) - 1);

            while(left < right && k == 0) {
                if(nums[left] == 0) k++;
                left++;
            }
        }

        return Math.max(result, (right - left) - 1);
    }
}
