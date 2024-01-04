package com.dsa.question.leetcode.medium;

public class NumberIslands {

    public static void main(String[] args) {

    }

    private static void dfs(char[][] grid, boolean[][] marked, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        marked[i][j] = true;

        if ((j + 1 < n) && !marked[i][j + 1] && (grid[i][j + 1] == '1')) {
            dfs(grid, marked, i, j + 1);
        }

        if ((j - 1 >= 0) && !marked[i][j - 1] && (grid[i][j - 1] == '1')) {
            dfs(grid, marked, i, j - 1);
        }

        if ((i + 1 < m) && !marked[i + 1][j] && (grid[i + 1][j] == '1')) {
            dfs(grid, marked, i + 1, j);
        }

        if ((i - 1 >= 0) && !marked[i - 1][j] && (grid[i - 1][j] == '1')) {
            dfs(grid, marked, i - 1, j);
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        boolean[][] marked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!marked[i][j] && (grid[i][j] == '1')) {
                    count++;
                    dfs(grid, marked, i, j);
                }
            }
        }

        return count;
    }
}
