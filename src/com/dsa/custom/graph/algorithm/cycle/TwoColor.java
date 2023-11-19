package com.dsa.custom.graph.algorithm.cycle;

import com.dsa.custom.graph.Graph;

import java.util.Iterator;

public class TwoColor {
    private final boolean[] marked;
    private final boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph graph) {

        int noOfVertices = graph.noOfVertices();
        marked = new boolean[noOfVertices];
        color = new boolean[noOfVertices];
        for (int s = 0; s < noOfVertices; s++)
            if (!marked[s])
                dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;

        Iterator<Integer> iterator = graph.iterator(v);
        while (iterator.hasNext()) {
            Integer w = iterator.next();
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(graph, w);
            } else if (color[w] == color[v]) isTwoColorable = false;
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
