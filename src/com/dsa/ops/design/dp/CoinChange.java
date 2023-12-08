package com.dsa.ops.design.dp;

import com.dsa.util.PerformanceUtil;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.out;

public class CoinChange {

    public static void main(String[] args) {

        int[] coins = new int[]{2, 3, 5, 10};
        int answer3 = PerformanceUtil.measureOperationDuration(() -> cCoinChange(9, coins));
        int answer4 = PerformanceUtil.measureOperationDuration(() -> oCoinChange(9, coins));
        int answer5 = PerformanceUtil.measureOperationDuration(() -> rOCoinChange(9, coins));
        int answer6 = PerformanceUtil.measureOperationDuration(() -> rOMCoinChange(9, coins));
        out.println(answer3);
        out.println(answer4);
        out.println(answer5);
        out.println(answer6);
    }

    private static int cCoinChange(int n, int[] coins) {

        int[] computedValue = new int[n + 1];

        computedValue[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    computedValue[i] += computedValue[i - coin];
                }
            }
        }
        return computedValue[n];
    }

    private static int rOCoinChange(int n, int[] coins) {
        if (n <= 0) return 0;
        int h = 0;

        for (int coin : coins) {
            if ((n - coin) >= 0)
                h = Math.max(1 + rOCoinChange(n - coin, coins), h);
        }

        return h;
    }

    private static int rOMCoinChange(int n, int[] coins) {
        if (n <= 0) return 0;
        int h = 0;

        for (int i = 0; i < coins.length - 1; i++) {
            int coin = coins[i];
            int coin2 = coins[i + 1];
            int rOCoinChange = ((n - coin) >= 0) ? 1 + rOCoinChange(n - coin, coins) : 0;
            int rOCoinChange1 = ((n - coin2) >= 0) ? 1 + rOCoinChange(n - coin2, coins) : 0;

            if (rOCoinChange == 0 && rOCoinChange1 == 0) return
                    h = Math.min(rOCoinChange, rOCoinChange1);
        }

        return h;
    }

    private static int oCoinChange(int n, int[] coins) {

        int[] computedValue = new int[n + 1];

        computedValue[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    computedValue[i] = computedValue[i] + Math.min(computedValue[i], computedValue[i - coin]);
                }
            }
        }
        return computedValue[n];
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public class TreeNode {
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

    class QueueItem {
        TreeNode t;
        int depth;

        public QueueItem(TreeNode t, int depth) {
            this.t = t;
            this.depth = depth;
        }
    }

    class Solution {
        public int minDepth(TreeNode root) {

            if (root == null) return 0;

            Queue<QueueItem> queue = new LinkedList<>();
            queue.add(new QueueItem(root, 0));

            while (!queue.isEmpty()) {

                QueueItem queueItem = queue.poll();
                TreeNode treeNode = queueItem.t;
                int depth = queueItem.depth;

                if (treeNode.right == null && treeNode.left == null) {
                    return depth + 1;
                }

                if (treeNode.left != null) {
                    queue.add(new QueueItem(treeNode.left.left, depth + 1));
                    queue.add(new QueueItem(treeNode.left.right, depth + 1));
                }

                if (treeNode.right != null) {
                    queue.add(new QueueItem(treeNode.right.left, depth + 1));
                    queue.add(new QueueItem(treeNode.right.right, depth + 1));
                }
            }


            if (root == null) return 0;
            if (root.right == null) return 1 + minDepth(root.left);
            if (root.left == null) return 1 + minDepth(root.right);
            if (root.left == null && root.right == null) return 0;

            // while (root != null) {
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
            // }

        }
    }
}
