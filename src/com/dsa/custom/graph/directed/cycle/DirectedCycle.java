package com.dsa.custom.graph.directed.cycle;

import com.dsa.custom.graph.directed.Digraph;
import com.dsa.custom.graph.lgraph.Edge;
import com.dsa.custom.stack.LStack;
import com.dsa.custom.stack.Stack;

public class DirectedCycle {

    private final boolean[] marked;
    private final int[] edgeTo;
    private final boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph digraph) {
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++)
            if (!marked[v]) dfs(digraph, v);
    }

    private void dfs(Digraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;

        for (Edge edge : digraph.adj(v)) {
            int w = edge.vertex();

            if (this.hasCycle()) return;

            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new LStack<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
