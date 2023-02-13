package com.dsa.custom.graph.path;

import com.dsa.custom.graph.Graph;

import java.util.Iterator;

public class DepthFirstPaths extends AbstractPaths {

    // source
    private final boolean[] marked; // Has dfs() been called for this vertex?
    private final int[] edgeTo;     // last vertex on known path to this vertex

    public DepthFirstPaths(Graph graph, int s) {
        super(graph, s);
        marked = new boolean[graph.noOfVertices()];
        edgeTo = new int[graph.noOfEdges()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;

        for (Iterator<Integer> it = graph.iterator(v); it.hasNext(); ) {
            int w = it.next();
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

}
