package com.dsa.custom.graph.algorithm.cycle;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.stack.LStack;
import com.dsa.custom.stack.Stack;

import java.util.Iterator;

public class DirectedCycle {

    private final boolean[] marked;
    private final int[] edgeTo;
    private final boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Graph graph) {
        int noOfVertices = graph.noOfVertices();

        onStack = new boolean[noOfVertices];
        edgeTo = new int[noOfVertices];
        marked = new boolean[noOfVertices];

        for (int v = 0; v < noOfVertices; v++)
            if (!marked[v]) dfs(graph, v);
    }

    private void dfs(Graph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;

        Iterator<Integer> iterator = digraph.iterator(v);

        while (iterator.hasNext()) {
            Integer w = iterator.next();

            if (this.hasCycle()) return;

            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) { //Has been visited
                cycle = new LStack<>();

                //Build the path
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
