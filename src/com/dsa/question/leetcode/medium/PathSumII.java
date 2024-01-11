package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    
    public static void main(String[] args) {

    }

    private static void solve(List<List<Integer>> result, int targetSum, int currentSum,
                              List<Integer> tempResult,
                              TreeNode root) {

        if (targetSum == currentSum && (root.left == null && root.right == null)) {
            result.add(List.copyOf(tempResult));
        } else {
            if (root.left != null) {
                tempResult.add(root.left.val);
                solve(result, targetSum, currentSum + root.left.val, tempResult, root.left);
                tempResult.remove(tempResult.size() - 1);
            }

            if (root.right != null) {
                tempResult.add(root.right.val);
                solve(result, targetSum, currentSum + root.right.val, tempResult, root.right);
                tempResult.remove(tempResult.size() - 1);
            }
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new ArrayList<>();

        tempResult.add(root.val);
        solve(result, targetSum, root.val, tempResult, root);

        return result;
    }
}
