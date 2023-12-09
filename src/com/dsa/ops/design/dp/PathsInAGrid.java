package com.dsa.ops.design.dp;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class PathsInAGrid {

    public static void main(String[] args) {

        int[][] paths = new int[][]{
                {3, 7, 9, 2, 7},
                {9, 8, 3, 5, 5},
                {1, 7, 9, 8, 5},
                {3, 8, 6, 4, 10},
                {6, 3, 9, 7, 8}
        };
        algorithm(paths);
        algorithm2(paths);
    }

    private static void algorithm(int[][] paths) {
        int i = 0;
        int j = 0;

        int maxI = paths.length;
        int maxJ = paths[0].length;

        boolean[][] ready = new boolean[maxI][maxJ];
        int[][] value = new int[maxI][maxJ];

        int solve = solve(paths, i, j, ready, value);
        out.println(solve);
    }

    private static int solve(int[][] paths, int i, int j, boolean[][] ready, int[][] value) {
        int maxI = paths.length;
        int maxJ = paths[0].length;
        int h = paths[i][j];

        if (ready[i][j]) return value[i][j];

        boolean isIInbound = i < maxI && i + 1 < maxI;
        boolean isJInbound = j < maxJ && j + 1 < maxJ;

        if (isIInbound && isJInbound) {
            int rowPath = h + solve(paths, i + 1, j, ready, value);
            int columPath = h + solve(paths, i, j + 1, ready, value);
            h = Math.max(rowPath, columPath);
        } else if (isIInbound) {
            h = h + solve(paths, i + 1, j, ready, value);
        } else if (isJInbound) {
            h = h + solve(paths, i, j + 1, ready, value);
        }

        ready[i][j] = true;
        value[i][j] = h;
        return h;
    }


    /**
     * Complete search
     *
     * @param paths
     */
    private static void algorithm2(int[][] paths) {
        int i = 0;
        int j = 0;

        List<List<Integer[]>> allPaths = new ArrayList<>();
        List<Integer[]> path = new ArrayList<>();
        solve(paths, i, j, allPaths, path);

        printCompleteSearchResult(paths, allPaths);
    }

    private static void printCompleteSearchResult(int[][] paths, List<List<Integer[]>> allPaths) {
        int counter = 0;
        for (List<Integer[]> result : allPaths) {
            out.print(++counter + ". " + paths[0][0] + " -> ");
            for (Integer[] inResult : result) {
                out.print(paths[inResult[0]][inResult[1]] + " -> ");
            }
            out.println();
        }
    }

    private static void solve(int[][] paths, int i, int j, List<List<Integer[]>> allPaths, List<Integer[]> path) {
        int maxI = paths.length;
        int maxJ = paths[0].length;

        if (i == maxI - 1 && j == maxJ - 1) {
            allPaths.add(List.copyOf(path));
        }

        boolean isIInbound = i < maxI && i + 1 < maxI;
        boolean isJInbound = j < maxJ && j + 1 < maxJ;

        if (isIInbound && isJInbound) {
            path.add(new Integer[]{i + 1, j});
            solve(paths, i + 1, j, allPaths, path);
            path.remove(path.size() - 1);

            path.add(new Integer[]{i, j + 1});
            solve(paths, i, j + 1, allPaths, path);
            path.remove(path.size() - 1);

        } else if (isIInbound) {
            path.add(new Integer[]{i + 1, j});
            solve(paths, i + 1, j, allPaths, path);
            path.remove(path.size() - 1);
        } else if (isJInbound) {
            path.add(new Integer[]{i, j + 1});
            solve(paths, i, j + 1, allPaths, path);
            path.remove(path.size() - 1);
        }

    }


}
