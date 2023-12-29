package com.dsa.question.leetcode.easy;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static java.lang.System.out;

public class SubtreeOfAnotherTree {

    public static void main(String[] args) {

        boolean subtree = isSubtree(new TreeNode(), new TreeNode());
        out.println(subtree);
    }

    private static boolean checkEquality(TreeNode root, TreeNode subRoot) {

        if (
                (root.left == null && root.right == null)
                        && (subRoot.left == null && subRoot.right == null)
                        && (root.val == (subRoot.val))
        ) return true;

        boolean result = (root.val == (subRoot.val));
        if (!result) return false;

        boolean checkLeftEquality;
        if (root.left != null && subRoot.left != null)
            checkLeftEquality = checkEquality(root.left, subRoot.left);
        else checkLeftEquality = root.left == null && subRoot.left == null;

        if (!checkLeftEquality) return false;

        boolean right;
        if (root.right != null && subRoot.right != null)
            right = checkEquality(root.right, subRoot.right);
        else right = root.right == null && subRoot.right == null;

        return right;
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null || subRoot == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (Objects.nonNull(node)) {
                if (node.val == (subRoot.val)) {
                    boolean c = checkEquality(node, subRoot);
                    if (c) return true;
                }
                queue.offer(node.left);
                queue.offer(node.right);

            }
        }

        return false;
    }
}
