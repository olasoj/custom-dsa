package com.dsa.custom.graph.lgraph;


import java.util.Objects;

// Edge class for Adjacency List graph representation
public class Edge implements Comparable<Edge> {
    private final int vert;
    private final int wt;
    private int otherVert;

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

    public int other(int vertex) {

        if (vertex == vert) return otherVert;
        else if (vertex == otherVert) return vert;
        else throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(Edge that) {
        return Integer.compare(this.weight(), that.weight());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if ((!(obj instanceof Edge))) return false;

        Edge otherEdge = (Edge) obj;
        return (otherEdge.vertex() == this.vertex())
                && (this.wt == otherEdge.wt)
                && (this.otherVert == otherEdge.otherVert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vert, wt, otherVert);
    }

    public String toString() {
        return String.format("%d-%d %.2f", vert, otherVert, wt);
    }
}
