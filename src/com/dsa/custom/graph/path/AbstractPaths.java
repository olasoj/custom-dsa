package com.dsa.custom.graph.path;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.stack.LStack;
import com.dsa.custom.stack.Stack;

public abstract class AbstractPaths implements Paths {

    private final int s;      // source
    private final boolean[] marked; // Is a shortest path to this vertex known?
    private final int[] edgeTo;     // last vertex on known path to this vertex

    protected AbstractPaths(Graph graph, int s) {
        marked = new boolean[graph.noOfVertices()];
        edgeTo = new int[graph.noOfEdges()];
        this.s = s;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new LStack<>();

        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);

        path.push(s);
        return path;
    }
}
