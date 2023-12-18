package com.dsa.ops.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.System.out;

public class DijkstraAlgorithm {

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
        Comparator<DElement> comparator = (o1, o2) -> Integer.compare(o1.getWeight(), o2.getWeight());

        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        Queue<DElement> pq = new PriorityQueue<>(comparator);
        pq.offer(new DElement(s, 0));

        while (!pq.isEmpty()) {
            DElement poll;
            do {
                poll = pq.poll();
            } while (poll != null && marked[poll.getVertex()]);

            if (poll == null) continue;

            marked[poll.getVertex()] = true;
            if (Integer.MAX_VALUE == (poll.getWeight())) return;

            //Relax edges
            for (int i = 1; i < vertices; i++) {
                if (graph[poll.getVertex()][i] != 0) {
                    if (distance[i] > distance[poll.getVertex()] + graph[poll.getVertex()][i]) {
                        distance[i] = distance[poll.getVertex()] + graph[poll.getVertex()][i];
                        pq.offer(new DElement(i, distance[i]));
                    }
                }
            }
        }

        out.println(Arrays.toString(distance));
    }


}

class DElement {
    private final Integer vertex;
    private final Integer weight;

    public DElement(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getVertex() {
        return vertex;
    }
}