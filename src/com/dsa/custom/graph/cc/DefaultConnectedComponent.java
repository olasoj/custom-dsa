package com.dsa.custom.graph.cc;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.search.DepthFirstSearch;
import com.dsa.custom.graph.search.GraphSearch;

public class DefaultConnectedComponent {

    private final int[] id;
    private int count;

    public DefaultConnectedComponent(Graph graph) {
        id = new int[graph.noOfVertices()];

        GraphSearch graphSearch = new DepthFirstSearch(graph, null, null, v -> id[v] = count);

        for (int s = 0; s < graph.noOfVertices(); s++) {
            if (!graphSearch.marked(s)) {
                graphSearch.search(s);
                count++;
            }
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
