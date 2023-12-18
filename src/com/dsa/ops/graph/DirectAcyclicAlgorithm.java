package com.dsa.ops.graph;

import java.util.Arrays;
import java.util.Stack;

import static java.lang.System.out;

public class DirectAcyclicAlgorithm {

    private static final int[][] graph;
    private static final int vertices;
    private static final boolean[] marked;


    private static final Stack<Integer> stack = new Stack<>();

    static {
        graph = new int[][]{
                //1  2   3  4  5  6
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0}, //1
                {0, 0, 0, 1, 0, 0, 0},//2
                {0, 0, 0, 0, 0, 0, 1},//3
                {0, 1, 0, 0, 0, 1, 0},//4
                {0, 0, 1, 1, 0, 0, 0},//5
                {0, 0, 0, 0, 0, 0, 0}//6
        };

        vertices = graph.length;
        marked = new boolean[vertices];
    }


    public static void main(String[] args) {

        algorithm(1);
    }


    private static void algorithm(int s) {
        for (int i = 1; i < vertices; i++)
            if (!marked[i])
                dfs(i);

        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        while (!stack.isEmpty()) {
            int ele = stack.pop();
            out.println(ele);

            if (distance[ele] != Integer.MAX_VALUE) {
                //Relax edges
                for (int i = 1; i < vertices; i++) {
                    if (graph[ele][i] != 0) {
                        if (distance[i] > distance[ele] + graph[ele][i]) {
                            distance[i] = distance[ele] + graph[ele][i];
                        }
                    }
                }
            }
        }

        out.println(Arrays.toString(distance));
    }

    private static void dfs(int sourceNode) {
        marked[sourceNode] = true;
        //Pre-visit

        for (int i = 1; i < vertices; i++) {
            if (graph[sourceNode][i] != 0 && !marked[i]) {
                //Visit
                marked[i] = true;
                dfs(i);
            }
        }

        //Post visit
        stack.push(sourceNode);
    }
}
