package com.dsa.custom.graph;

import com.dsa.util.Assert;

import java.util.NoSuchElementException;

public class GraphIterator implements java.util.Iterator<Integer> {

    private final Graph g;
    private int vertex;
    private int weight;

    public GraphIterator(Graph g, int vertex) {
        Assert.notNull(g, "Graph cannot be null");
        this.g = g;
        this.vertex = vertex;
        this.weight = g.first(vertex);
    }

    @Override
    public boolean hasNext() {
        return weight < g.noOfVertices();
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        int first = weight;
        weight = g.next(vertex, weight);
        return first;
    }
}
