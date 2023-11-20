package com.dsa.custom.graph.algorithm.mst;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.lgraph.Edge;
import com.dsa.custom.heap.MaxPQ;
import com.dsa.custom.queue.LQueue;
import com.dsa.custom.queue.Queue;
import com.dsa.custom.tree.general.ParPtrTree;

public class KruskalMST {

    private final Queue<Edge> mst;

    public KruskalMST(Graph graph) {
        mst = new LQueue<>();
        Queue<Edge> pq = new MaxPQ<>(graph.noOfEdges());

        int noOfVertices = graph.noOfVertices();
        ParPtrTree uf = new ParPtrTree(noOfVertices);

        while (!pq.isEmpty() && mst.length() < (noOfVertices - 1)) {
            Edge e = pq.dequeue();
            int v = e.either();
            int w = e.other(v); //   and its vertices.
            if (!uf.differ(v, w)) continue;
            uf.UNION(v, w);
            mst.enqueue(e);
        }

    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return Double.NaN;
    }
}
