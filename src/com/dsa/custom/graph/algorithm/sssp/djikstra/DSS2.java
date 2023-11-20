package com.dsa.custom.graph.algorithm.sssp.djikstra;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.lgraph.Edge;
import com.dsa.custom.heap.MaxHeap1;
import com.dsa.custom.queue.Queue;
import com.dsa.custom.stack.LStack;
import com.dsa.custom.stack.Stack;

import java.util.Iterator;


//TODO: To be continued
public class DSS2 {

    private final Edge[] edgeTo;
    private final double[] distTo;
    private final Queue<Double> pq;

    public DSS2(Graph graph, int s) {
        int noOfVertices = graph.noOfVertices();
        edgeTo = new Edge[noOfVertices];
        distTo = new double[noOfVertices];
        pq = new MaxHeap1<>(noOfVertices);

        for (int v = 0; v < noOfVertices; v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
//        pq.enqueue(s, 0.0);
        while (!pq.isEmpty()) {
            relax(graph, 0);
        }
//            relax(graph, pq.dequeue())
    }


    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new LStack<>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.vertex()])
            path.push(e);
        return path;
    }


    private void relax(Graph graph, int v) {

        Iterator<Integer> iterator = graph.iterator(v);

        while (iterator.hasNext()) {
            Integer w = iterator.next();
            int weight = graph.weight(v, w);
            if (distTo[w] > distTo[v] + weight) {
                distTo[w] = distTo[v] + weight;
                edgeTo[w] = new Edge(v, w);
//                if (pq.contains(w)) pq.change(w, distTo[w]);
//                else                pq.insert(w, distTo[w]);
            }
        }
    }
}

