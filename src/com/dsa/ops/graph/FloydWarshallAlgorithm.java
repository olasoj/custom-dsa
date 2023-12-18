package com.dsa.ops.graph;

import java.util.Arrays;

import static java.lang.System.out;

public class FloydWarshallAlgorithm {


    private static final int[][] graph;
    private static final int vertices;
    private static final boolean[] marked;

    static {
        graph = new int[][]{
                // 1  2   3  4  5
                {0, 0, 0, 0, 0, 0},
                {0, 0, 5, 0, 9, 1}, //1
                {0, 5, 0, 2, 0, 0},//2
                {0, 0, 2, 0, 6, 0},//3
                {0, 9, 0, 6, 0, 2},//4
                {0, 1, 0, 0, 2, 0},//5
        };

        vertices = graph.length;
        marked = new boolean[vertices];
    }

    public static void main(String[] args) {
        algorithm(1);
    }

    private static void algorithm(int s) {

        int[][] relaxationMatrix = new int[vertices][vertices];

        for (int i = 1; i < vertices; i++) {
            for (int j = 1; j < vertices; j++) {
                if (i == j) relaxationMatrix[i][j] = 0;
                else if (graph[i][j] != 0) relaxationMatrix[i][j] = graph[i][j];
                else relaxationMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int k = 1; k < vertices; k++) {
            for (int i = 1; i < vertices; i++) {
                for (int j = 1; j < vertices; j++) {
                    relaxationMatrix[i][j] = Math.min(relaxationMatrix[i][j], relaxationMatrix[i][k] + relaxationMatrix[k][j]);
                }
            }
        }

        for (int[] arr : relaxationMatrix) {
            out.println(Arrays.toString(arr));
        }
    }
}
