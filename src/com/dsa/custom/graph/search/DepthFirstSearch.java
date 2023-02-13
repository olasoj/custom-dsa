package com.dsa.custom.graph.search;

import com.dsa.custom.graph.Graph;

import java.util.Iterator;

public class DepthFirstSearch implements GraphSearch {

    //Change this to a stack of objects.
    private final boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.noOfVertices()];
        dfs(graph, s);
    }

    //We are using Java function (stack) to recursively
    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;

        while (graph.first(v) != graph.noOfVertices()) {
            v = graph.first(v);
            if (!marked[v]) dfs(graph, v);
        }

        for (Iterator<Integer> it = graph.iterator(v); it.hasNext(); ) {
            int w = it.next();
            if (!marked[w]) dfs(graph, w);
        }
    }

    @Override
    public boolean marked(int w) {
        return marked[w];
    }

    @Override
    public int count() {
        return count;
    }

}
