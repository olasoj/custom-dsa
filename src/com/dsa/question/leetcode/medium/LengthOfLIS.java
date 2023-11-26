package com.dsa.question.leetcode.medium;


import static java.lang.System.out;

public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int i = lengthOfLIS(nums);
        out.println(lengthOfLIS2(nums));
        out.println(lengthOfLIS2(new int[]{3, 4, -1, 0, 6, 2, 3}));
        out.println(i);
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for (int x : nums) {
            int i = binarySearch(dp, x, len);

            if (i < 0) {
                dp[len] = x;
                len++;
            } else {
                dp[i] = x;
            }
        }

        return len;
    }

    public static int binarySearch(int[] tempStore, int item, int len) {

        if (len == 0) return -1;

        int start = 0;
        int end = len;
        int mid;

        while (start <= end) {

            mid = start + ((end - start) / 2);
            if (item < tempStore[mid]) end = mid - 1;
            else if (item == tempStore[mid]) return mid;
            else if (item > tempStore[mid]) start = mid + 1;
        }

        return (start >= len) || tempStore[start] < item ? -1 : start;
    }

    public static int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        int totalJIterations = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                totalJIterations++;
                if ((nums[j] < nums[i]) && ((1 + dp[j]) > dp[i])) {
                    dp[i] = 1 + dp[i];
                }
                max = Math.max(max, dp[i]);
            }
        }

//        (n-1)(3n)
//        System.out.println(Arrays.toString(dp));
        out.println("TotalJIterations: " + totalJIterations);
        out.println("Num size: " + nums.length);

        return max + 1;
    }

}
