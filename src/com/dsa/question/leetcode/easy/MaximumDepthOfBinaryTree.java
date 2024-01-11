package com.dsa.question.leetcode.easy;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {

    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return solve(root) + 1;
    }

    public int solve(TreeNode root) {
        int h = 0;

        if (root == null) return 0;

        h = Math.max(h, 1 + solve(root.left));
        h = Math.max(h, 1 + solve(root.right));
        return h;
    }

}
