package com.dsa.custom.graph.algorithm.djikstra;

import com.dsa.custom.graph.Graph;

public class DSS {

    private static final int VISITED = 1;
    private static final int UNVISITED = 0;

    // Compute the shortest path distances from s, store them in D
    static void Dijkstra(Graph graph, int s, int[] D) {

        for (int i = 0; i < graph.noOfVertices(); i++)    // Initialize
            D[i] = Integer.MAX_VALUE;

        D[s] = 0;

        for (int i = 0; i < graph.noOfVertices(); i++) {  // Process the vertices
            int v = minVertex(graph, D);     // Find next-closest vertex
            graph.setMark(v, VISITED);
            if (D[v] == Integer.MAX_VALUE) return; // Unreachable
            for (int w = graph.first(v); w < graph.noOfVertices(); w = graph.next(v, w))
                if (D[w] > (D[v] + graph.weight(v, w)))
                    D[w] = D[v] + graph.weight(v, w);
        }
    }

    static int minVertex(Graph graph, int[] distance) {
        int v = 0;  // Initialize v to any unvisited vertex;

        for (int i = 0; i < graph.noOfVertices(); i++)
            if (graph.getMark(i) == UNVISITED) {
                v = i;
                break;
            }

        for (int i = 0; i < graph.noOfVertices(); i++)  // Now find the smallest value
            if ((graph.getMark(i) == UNVISITED) && (distance[i] < distance[v]))
                v = i;
        return v;
    }
}
