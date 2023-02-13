package com.dsa.custom.graph.path;

//find paths in G from source s
public interface Paths {

    //is there a path from s to v?
    boolean hasPathTo(int v);

    //    path from s to v; null if no such path
    Iterable<Integer> pathTo(int v);

}
