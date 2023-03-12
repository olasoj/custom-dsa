package com.dsa.custom.graph.directed.sort;

public interface Topological {

    boolean isDAG();

    Iterable<Integer> order();

}
