package com.dsa.custom.graph.cc;

import com.dsa.custom.graph.Graph;

import java.util.Iterator;

public class DefaultConnectedComponent {

    private final boolean[] marked;
    private final int[] id;
    private int count;

    public DefaultConnectedComponent(Graph graph) {
        marked = new boolean[graph.noOfVertices()];
        id = new int[graph.noOfVertices()];

        for (int s = 0; s < graph.noOfVertices(); s++)
            if (!marked[s]) {
                dfs(graph, s);
                count++;
            }
    }


    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;

        for (Iterator<Integer> it = graph.iterator(v); it.hasNext(); ) {
            int w = it.next();
            if (!marked[w])
                dfs(graph, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

}
