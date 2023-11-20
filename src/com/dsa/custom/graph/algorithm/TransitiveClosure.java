package com.dsa.custom.graph.algorithm;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.search.DepthFirstSearch;
import com.dsa.custom.graph.search.GraphSearch;

public class TransitiveClosure {
    private final GraphSearch[] all;

    TransitiveClosure(Graph graph) {
        all = new DepthFirstSearch[graph.noOfVertices()];

        for (int v = 0; v < graph.noOfVertices(); v++) {
            DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
            depthFirstSearch.search(v);
            all[v] = depthFirstSearch;
        }

    }

    boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}

