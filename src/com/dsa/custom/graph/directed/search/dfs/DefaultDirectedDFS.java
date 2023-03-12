package com.dsa.custom.graph.directed.search.dfs;

import com.dsa.custom.graph.directed.Digraph;
import com.dsa.custom.graph.lgraph.Edge;

public class DefaultDirectedDFS implements DirectedDFS {

    private final boolean[] marked;

    public DefaultDirectedDFS(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        dfs(digraph, s);
    }

    public DefaultDirectedDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.V()];
        for (int s : sources)
            if (!marked[s]) dfs(digraph, s);
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (Edge edge : digraph.adj(v))
            if (!marked[edge.vertex()]) dfs(digraph, edge.vertex());
    }

    public boolean marked(int v) {
        return marked[v];
    }

}
