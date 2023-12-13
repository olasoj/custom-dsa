package com.dsa.custom.graph.algorithm.sssp.djikstra;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.heap.MaxHeap1;
import com.dsa.custom.queue.Queue;

import java.util.Objects;

public class DSS3 {

    private static final int VISITED = 1;
    private static final int UNVISITED = 0;

    // Dijkstra’s shortest-paths: priority queue version
    static void Dijkstra(Graph graph, int s, int[] distance) {

        DijkElem[] E = new DijkElem[graph.noOfEdges()]; // Heap for edges

        E[0] = new DijkElem(s, 0);          // Initial vertex
        //TODO:MIN PQ
        Queue<DijkElem> dijkElems = new MaxHeap1<>(E, 1, graph.noOfEdges());

        int noOfVertices = graph.noOfVertices();
        for (int i = 0; i < noOfVertices; i++)         // Initialize distance
            distance[i] = Integer.MAX_VALUE;

        int v; // The current vertex
        distance[s] = 0;
        for (int i = 0; i < noOfVertices; i++) {       // For each vertex
            do {
                v = (dijkElems.dequeue()).vertex();// Get position
            } while (graph.getMark(v) == VISITED && !dijkElems.isEmpty());

            graph.setMark(v, VISITED);
            if (distance[v] == Integer.MAX_VALUE) return;  // Unreachable

            for (int w = graph.first(v); w < noOfVertices; w = graph.next(v, w))
                if (distance[w] > (distance[v] + graph.weight(v, w))) { // Update distance
                    distance[w] = distance[v] + graph.weight(v, w);
                    //TODO: If already enqueued replace
                    dijkElems.enqueue(new DijkElem(w, distance[w]));
                }
        }
    }
}

class DijkElem implements Comparable<DijkElem> {
    private final int vertex;
    private final int weight;

    public DijkElem(int inv, int inw) {
        vertex = inv;
        weight = inw;
    }

    public DijkElem() {
        vertex = 0;
        weight = 0;
    }

    public int key() {
        return weight;
    }

    public int vertex() {
        return vertex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DijkElem dijkElem)) return false;
        return vertex == dijkElem.vertex && weight == dijkElem.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, weight);
    }

    public int compareTo(DijkElem that) {
        return Integer.compare(weight, that.key());
    }
}