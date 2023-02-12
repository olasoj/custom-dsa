package com.dsa.custom.graph.search;

import com.dsa.custom.graph.Graph;

public class DepthFirstSearch {

    //Change this to a stack of objects.
    private final boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.noOfVertices()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;

        while (graph.first(v) != 0) {
            if (!marked[v]) dfs(graph, v);
            v = graph.first(v);
        }
//        for (int w : graph.adj(v))
//            if (!marked[w]) dfs(graph, w);
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

}
