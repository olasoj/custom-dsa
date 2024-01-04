package com.dsa.question.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class ValidateBinarySearchTree {

    public static void main(String[] args) {
    }

    private static boolean isInvalidBST(TreeNode root, TreeNode searchNode) {

        TreeNode rootTemp = root;

        while (rootTemp != null) {
            if (searchNode.val < rootTemp.val) {
                rootTemp = rootTemp.left;
            } else if (searchNode.val > rootTemp.val) {
                rootTemp = rootTemp.right;
            } else {
                return !searchNode.equals(rootTemp);
            }
        }

        return true;
    }

    public boolean isValidBST(TreeNode root) { // O(nlog(n))

        if (root == null || root.left == null && root.right == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode t = queue.poll();
            TreeNode leftChild = t.left;
            TreeNode rightChild = t.right;

            if (leftChild != null) {
                if (isInvalidBST(root, leftChild)) return false;
                queue.offer(leftChild);
            }

            if (rightChild != null) {
                if (isInvalidBST(root, rightChild)) return false;
                queue.offer(rightChild);

            }
        }

        return true;
    }
}
