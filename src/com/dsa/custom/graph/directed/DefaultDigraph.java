package com.dsa.custom.graph.directed;

import com.dsa.custom.graph.lgraph.Edge;
import com.dsa.custom.graph.lgraph.GraphList;

public class DefaultDigraph implements Digraph {

    private final int vertices;
    private final GraphList<Edge>[] adj;
    private int noOfEdges;

    public DefaultDigraph(int vertices) {
        this.vertices = vertices;
        this.noOfEdges = 0;
        adj = (GraphList<Edge>[]) new GraphList[vertices];

        for (int v = 0; v < vertices; v++)
            adj[v] = new GraphList<>();
    }

    @Override
    public int V() {
        return vertices;
    }

    public int E() {
        return noOfEdges;
    }


    @Override
    public void addEdge(int v, int w) {
        Edge edge = new Edge(v, w);
        adj[v].insert(edge);
        noOfEdges++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }


    public Digraph reverse() {
        Digraph reverseDigraph = new DefaultDigraph(vertices);

        for (int v = 0; v < vertices; v++)
            for (Edge edge : adj(v))
                reverseDigraph.addEdge(edge.vertex(), edge.weight());

        return reverseDigraph;
    }
}
