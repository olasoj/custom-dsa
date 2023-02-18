package com.dsa.custom.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Graph {
    void init(int n); //Init to n vertices

    int noOfVertices(); //Number of vertices

    int noOfEdges(); //Number of edges

    int first(int v);  // Get v’s first neighbor

    int next(int v, int w); // Get v’s next neighbor

    void setEdge(int i, int j, int weight); // Set weight

    void delEdge(int i, int j);   // Delete edge (i, j)

    boolean isEdge(int i, int j); // If (i,j) an edge?

    int weight(int i, int j);     // Return edge weight

    void setMark(int v, int val); // Set Mark for v

    int getMark(int v);           // Get Mark for v

    default Iterator<Integer> iterator(int v) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return first(v) == noOfVertices();
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return first(v);
            }
        };
    }
}

