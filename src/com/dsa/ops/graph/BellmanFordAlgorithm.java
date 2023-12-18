package com.dsa.ops.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.out;

public class BellmanFordAlgorithm {

    private static final int[][] graph;
    private static final int vertices;
    private static final boolean[] marked;

    static {
        graph = new int[][]{
                //1  2   3  4  5  6
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 5, 3, 7, 0, 0}, //1
                {0, 0, 0, 0, 3, 0, 2},//2
                {0, 0, 0, 0, 1, 0, 0},//3
                {0, 0, 0, 0, 0, 0, 2},//4
                {0, 0, 0, 0, 0, 0, 0},//5
                {0, 0, 0, 0, 0, 0, 0}//6
        };

        vertices = graph.length;
        marked = new boolean[vertices];
    }

    public static void main(String[] args) {
        bellmanFord(1);
        bellmanFordQueue(1);
    }


    private static void bellmanFordQueue(int s) {

        int cost = 0;
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean nCF = false;

        boolean[] onQ = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<>();
        distance[s] = 0;

        queue.offer(s);
        while (!queue.isEmpty() && !nCF) {
            Integer poll = queue.poll();
            onQ[poll] = false;

            //Relax edges
            for (int i = 1; i < vertices; i++) { //Edges
                if (graph[poll][i] != 0 && (distance[i] > distance[poll] + graph[poll][i])) {
                    distance[i] = distance[poll] + graph[poll][i];
                    if (!onQ[i]) {
                        queue.offer(i);
                        onQ[i] = true;
                    }
                    if (++cost % vertices == 0)
                        nCF = true;
                }
            }
        }

        out.println(Arrays.toString(distance));
    }

    private static void bellmanFord(int s) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        for (int x = 1; x < vertices; x++) {
            for (int j = s; j < vertices; j++) { //Vertices
                for (int i = 1; i < vertices; i++) { //Edges
                    if (graph[j][i] != 0 && (distance[i] > distance[j] + graph[j][i])) { //Is an edge && We found a lesser path.
                        distance[i] = distance[j] + graph[j][i];
                    }
                }
            }
        }

        out.println(Arrays.toString(distance));
    }
}
