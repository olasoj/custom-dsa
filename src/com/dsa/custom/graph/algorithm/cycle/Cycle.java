package com.dsa.custom.graph.algorithm.cycle;

import com.dsa.custom.graph.Graph;

import java.util.Iterator;

public class Cycle {

    private final boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        int noOfVertices = graph.noOfVertices();
        marked = new boolean[noOfVertices];
        for (int s = 0; s < noOfVertices; s++)
            if (!marked[s])
                dfs(graph, s, s);
    }

    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        Iterator<Integer> it = graph.iterator(v);
        while (it.hasNext()) {
            int w = it.next();
            if (!marked[w])
                dfs(graph, w, v);
            else if (w != u) hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

}
