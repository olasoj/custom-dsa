package com.dsa.question.leetcode.medium;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static java.lang.System.out;

public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        int[] arr = {
                4, 5, 6, 7, 0, 1, 2
        };

        out.println(algorithm(arr));
    }

    public static int algorithm(int[] nums) {

        int arrLength = nums.length;

        if (arrLength == 1) return nums[0];
        if (arrLength == 2) return Math.min(nums[0], nums[1]);

        int startIndex = 0;

        while (startIndex < arrLength) {
            int mid = (startIndex + arrLength) / 2;

            int startElement = nums[startIndex];
            int endElement = nums[arrLength - 1];

            if (startElement < endElement) {
                int count = startIndex;
                while (--count > 0 && nums[count] < startElement) startElement = nums[count];
                return startElement;
            } else if (startElement > endElement) startIndex = mid;
            else arrLength = mid;
        }

        int count = startIndex;
        int startElement = nums[startIndex];
        while (++count < arrLength && nums[count] < startElement) startElement = nums[count];
        return startElement;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null || subRoot == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (Objects.nonNull(node)) {
                if (node.equals(subRoot)) return true;
                else {
                    queue.offer(root.left);
                    queue.offer(root.right);
                }
            }
        }

        return false;
    }
}
