package com.dsa.custom.graph.lgraph;


// Edge class for Adjacency List graph representation
public class Edge {
    private final int vert;
    private final int wt;

    public Edge(int v, int w) {
        vert = v;
        wt = w;
    }

    public int vertex() {
        return vert;
    }

    public int weight() {
        return wt;
    }
}
