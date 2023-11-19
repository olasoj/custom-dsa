package com.dsa.custom.graph.algorithm.sort;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.algorithm.cycle.DirectedCycle;

public class DefaultTopological implements Topological {
    private Iterable<Integer> order;

    // topological order
    public DefaultTopological(Graph graph) {
        DirectedCycle cylinder = new DirectedCycle(graph);
        if (!cylinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(graph);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order == null;
    }
}
