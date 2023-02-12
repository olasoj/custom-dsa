package com.dsa.custom.graph.agraph;

import com.dsa.custom.graph.Graph;

public class Graphm implements Graph {

    // The edge matrix
    private int[] mark;

    // The mark array
    private int[][] matrix;

    // Number of edges
    private int numEdge;

    public Graphm() {
    }

    public Graphm(int n) {
        init(n);
    }

    public void init(int n) {
        mark = new int[n];
        matrix = new int[n][n];
        numEdge = 0;
    }

    public int noOfVertices() {
        return mark.length;
    } // # of vertices

    public int noOfEdges() {
        return numEdge;
    }     // # of edges

    public int first(int v) { // Get v’s first neighbor
        for (int i = 0; i < mark.length; i++)
            if (matrix[v][i] != 0) return i;
        return mark.length;  // No edge for this vertex
    }

    public int next(int v, int w) { // Get v’s next edge
        for (int i = w + 1; i < mark.length; i++)
            if (matrix[v][i] != 0)
                return i;
        return mark.length;  // No next edge;
    }

    // Is (i, j) an edge?
    public boolean isEdge(int i, int j) {
        return matrix[i][j] != 0;
    }


    // Set edge weight
    public void setEdge(int i, int j, int weight) {

        if (weight < 1) throw new IllegalArgumentException("Cannot set weight to 0");

        if (matrix[i][j] == 0) numEdge++;
        matrix[i][j] = weight;
    }

    public void delEdge(int i, int j) { // Delete edge (i, j)
        if (matrix[i][j] != 0) {
            matrix[i][j] = 0;
            numEdge--;
        }
    }

    public int weight(int i, int j) { // Return edge weight
        if (i == j) return 0;
        if (matrix[i][j] == 0) return Integer.MAX_VALUE;
        return matrix[i][j];
    }

    // Get and set marks
    public void setMark(int v, int val) {
        mark[v] = val;
    }

    public int getMark(int v) {
        return mark[v];
    }
}
