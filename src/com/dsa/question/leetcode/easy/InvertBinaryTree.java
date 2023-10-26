package com.dsa.question.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

    public static void main(String[] args) {

    }

    public TreeNode invertTree(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) return null;

        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode rootTemp = queue.poll();

            if (rootTemp.left != null) queue.offer(rootTemp.left);
            if (rootTemp.right != null) queue.offer(rootTemp.right);

            TreeNode tempLeft = rootTemp.left;
            rootTemp.left = rootTemp.right;
            rootTemp.right = tempLeft;

        }

        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
