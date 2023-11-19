package com.dsa.custom.graph.algorithm.sort;

public interface Topological {

    boolean isDAG();

    Iterable<Integer> order();

}
