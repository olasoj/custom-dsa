package com.dsa.custom.graph.algorithm.sssp.bellmanford;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.lgraph.Edge;
import com.dsa.custom.queue.LQueue;
import com.dsa.custom.queue.Queue;

import java.util.Iterator;


public class ShortestPathFasterAlgorithm {

    private final double[] distTo;
    private final Edge[] edgeTo;
    private final boolean[] onQ;
    private final Queue<Integer> queue;
    private int cost;
    private Iterable<Edge> cycle;  // negative cycle in edgeTo[]?

    public ShortestPathFasterAlgorithm(Graph graph) {
        int v1 = graph.noOfVertices();

        distTo = new double[v1];
        edgeTo = new Edge[v1];
        onQ = new boolean[v1];
        queue = new LQueue<>();

    }

    public void BellmanFordSP(Graph graph, int s) {

        int v1 = graph.noOfVertices();

        for (int v = 0; v < v1; v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;

        while (!queue.isEmpty() && !this.hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(graph, v);
        }
    }

    private void relax(Graph graph, int v) {
        Iterator<Integer> iterator = graph.iterator(v);

        while (iterator.hasNext()) {
            int w = iterator.next();
            Edge e = new Edge(v, w);
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
                if (cost++ % graph.noOfVertices() == 0)
                    findNegativeCycle();
            }
        }
    }

    private void findNegativeCycle() {
        //TODO: Later
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<Edge> negativeCycle() {
        return cycle;
    }
}
