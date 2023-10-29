package com.dsa.custom.graph.path;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.search.BreathFirstSearch;
import com.dsa.custom.graph.search.DepthFirstSearch;
import com.dsa.custom.graph.search.GraphSearch;
import com.dsa.custom.graph.search.GraphSearchType;
import com.dsa.custom.stack.LStack;
import com.dsa.custom.stack.Stack;

import java.util.function.BiConsumer;

public class Paths {

    protected final boolean[] marked; // Is a shortest path to this vertex known?
    protected final int[] edgeTo;     // last vertex on known path to this vertex
    private final int s;      // source
    private final GraphSearch graphSearch;

    public Paths(Graph graph, int s, GraphSearchType searchType) {
        marked = new boolean[graph.noOfVertices()];
        edgeTo = new int[graph.noOfEdges()];
        this.s = s;

        BiConsumer<Integer, Integer> visit = (v1, w1) -> edgeTo[w1] = v1;

        this.graphSearch = switch (searchType) {
            case DFS -> new DepthFirstSearch(graph, visit, null, null);
            case BFS -> new BreathFirstSearch(graph, visit, null, null);
        };

        graphSearch.search(s);
    }

    public boolean hasPathTo(int v) {
        return graphSearch.marked(v);
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
