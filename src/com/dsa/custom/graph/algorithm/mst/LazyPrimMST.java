package com.dsa.custom.graph.algorithm.mst;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.lgraph.Edge;
import com.dsa.custom.heap.MaxHeap1;
import com.dsa.custom.queue.LQueue;
import com.dsa.custom.queue.Queue;

import java.util.Iterator;

public class LazyPrimMST {
    private final boolean[] marked; // MST vertices
    private final Queue<Edge> mst; // MST edges

    //TODO: Revisit later MinPQ
    private final MaxHeap1<Edge> pq;  // crossing (and ineligible) edges


    public LazyPrimMST(Graph graph) {
        pq = new MaxHeap1<>(graph.noOfVertices());
        marked = new boolean[graph.noOfVertices()];

        mst = new LQueue<>();
        visit(graph, 0);   // assumes graph is connected (see Exercise 4.3.22)
        while (!pq.isEmpty()) {
            Edge e = pq.dequeue();                  // Get lowest-weight
            int v = e.either();
            int w = e.other(v);    //    edge from pq.
            if (marked[v] && marked[w]) continue;  // Skip if ineligible.
            mst.enqueue(e); // Add edge to tree.
            if (!marked[v]) visit(graph, v); // Add vertex to tree
            if (!marked[w]) visit(graph, w); //   (either v or w).
        }
    }


    private void visit(Graph graph, int v) {  // Mark v and add to pq all edges from v to unmarked vertices.
        marked[v] = true;
        Iterator<Integer> iterator = graph.iterator(v);
        while (iterator.hasNext()) {
            Integer e = iterator.next();
//            if (!marked[e.other(v)]) pq.insert(e);
        }

    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return 0;
    }   // See Exercise 4.3.31.
}

