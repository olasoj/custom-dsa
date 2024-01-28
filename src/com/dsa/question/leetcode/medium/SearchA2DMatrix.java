package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class SearchA2DMatrix {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

        out.println(searchMatrix(arr, 3));
        out.println(searchMatrix(arr, 60));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = 0;

        while (i < m && j < n) {
            if (matrix[i][j] == target) return true;
            else if (i + 1 < m && matrix[i + 1][j] <= target) {
                i++;
            } else if (j + 1 < n && matrix[i][j + 1] <= target) {
                j++;
            } else {
                return false;
            }
        }

        return false;
    }

}
