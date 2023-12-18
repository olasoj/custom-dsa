package com.dsa.question.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

public class LowestCommonAncestorBinarySearchTree {

    public static void main(String[] args) {

    }

    private static List<TreeNode> searchTree(TreeNode root, TreeNode p) {

        TreeNode rootT = root;
        List<TreeNode> queue = new LinkedList<>();

        while (rootT != null) {

            if (p.val < rootT.val) {
                queue.add(rootT);
                rootT = rootT.left;
            } else if (p.val > rootT.val) {
                queue.add(rootT);
                rootT = rootT.right;
            } else {
                queue.add(rootT);
                rootT = null;
            }
        }

        return queue;
    }

    private static TreeNode searchTree(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {

            if ((p.val < root.val) && (q.val < root.val)) {
                root = root.left;
            } else if ((p.val > root.val) && (q.val > root.val)) {
                root = root.right;
            } else {
                return root;
            }
        }

        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> searchTreeeP = searchTree(root, p);
        List<TreeNode> searchTreeeQ = searchTree(root, q);

        TreeNode lca = null;
        for (int i = 0; i < Math.min(searchTreeeP.size(), searchTreeeQ.size()); i++) {
            if (searchTreeeP.get(i).equals(searchTreeeQ.get(i))) lca = searchTreeeQ.get(i);
            else break;
        }

        return lca;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
