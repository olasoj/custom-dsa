package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = maxArea(height);
        out.println(maxArea);
    }

    public static int maxArea(int[] height) {

        int a = 0;
        int b = height.length - 1;
        int result = 0;

        while (a < b) {
            result = Math.max(result, (b - a) * Math.min(height[b], height[a]));
            if (height[b] > height[a]) a++;
            else b--;
        }

        return result;
    }
}
