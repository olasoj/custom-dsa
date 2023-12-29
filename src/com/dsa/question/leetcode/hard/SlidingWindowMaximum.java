package com.dsa.question.leetcode.hard;

import java.util.Arrays;

import static java.lang.System.out;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] ints = slidingWindowMaximum.maxSlidingWindow(new int[]{1, -1}, 1);
        out.println(Arrays.toString(ints));
    }

    private static int[] buildSegmentTree(int[] arr) {
        int n = arr.length;

        boolean isOdd = (n % 2 == 1);
        int[] segTree = new int[isOdd ? ((n + 1) * 2) : (n * 2)];

        if (isOdd) segTree[segTree.length - 1] = Integer.MIN_VALUE;

        //Populate child node
        int counter = n;
        int i1 = isOdd ? segTree.length - 2 : segTree.length - 1;
        for (int i = i1; i >= n; i--) { //O(n)
            --counter;
            if (counter >= 0)
                segTree[i] = arr[counter];
        }

        for (int i = n - 1; i >= 0; i--) { //O(n)
            // populate parent = LC + RC
            segTree[i] = Math.max(segTree[2 * i], segTree[2 * i + 1]);
        }

        out.println(Arrays.toString(segTree));
        return segTree;
    }

    private static int sum(int a, int b, int[] tree) {

        int n = tree.length / 2;

        a += n;  //Search in the original array.
        b += n;  //Search in the original array.
        int s = 0;

        while (a <= b) {
            if (a % 2 == 1) s = Math.max(s, tree[a++]);
            if (b % 2 == 0) s = Math.max(s, tree[b--]);
            a /= 2;
            b /= 2;
        }
        return s;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[(nums.length - k) + 1];

        int[] segmentTree = buildSegmentTree(nums);

        int left = 0;
        while (k <= nums.length) {
            int sum = sum(left, k - 1, segmentTree);
            result[left] = sum;
            left++;
            k++;
        }

        return result;
    }
}
