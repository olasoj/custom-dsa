package com.dsa.custom.graph.directed.sort;

import com.dsa.custom.graph.directed.Digraph;
import com.dsa.custom.graph.directed.cycle.DirectedCycle;

public class DefaultTopological implements Topological {
    private Iterable<Integer> order;

    // topological order
    public DefaultTopological(Digraph digraph) {
        DirectedCycle cylinder = new DirectedCycle(digraph);
        if (!cylinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(digraph);
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
