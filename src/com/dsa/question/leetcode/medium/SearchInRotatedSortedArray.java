package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        out.println(search(new int[]{
                4, 5, 6, 7, 0, 1, 2
        }, 0));

        out.println(search(new int[]{
                4, 5, 6, 7, 8, 1, 2, 3
        }, 8));
    }

    public static int search(int[] nums, int target) {

        if (nums.length == 1 && target == nums[0]) return 0;

        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex < endIndex) {
            int midIndex = (startIndex + endIndex) / 2;

            if (nums[startIndex] == target) return startIndex;
            else if (nums[midIndex] == target) return midIndex;
            else if (nums[endIndex] == target) return endIndex;

            if (nums[startIndex] < nums[endIndex]) {
                if (nums[midIndex] < target) endIndex = midIndex - 1;
                else startIndex = midIndex;
            } else {
                if (target > nums[startIndex] && target < nums[midIndex]) endIndex = midIndex;
                else if (target > nums[midIndex]) startIndex = midIndex + 1;
                else return -1;
            }

            // return -1;
        }

        return -1;
    }
}
