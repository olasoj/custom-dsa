package com.dsa.custom.graph.directed;

import com.dsa.custom.graph.lgraph.Edge;

public interface Digraph {

    /**
     * number of vertices
     *
     * @return
     */
    int E();

    /**
     * number of edges
     *
     * @return
     */
    int V();

    /**
     * add edge v->w to this digraph
     *
     * @param v
     * @param w
     */
    void addEdge(int v, int w);

    /**
     * vertices connected to v by edges pointing from v
     *
     * @param v
     * @return
     */
    Iterable<Edge> adj(int v);

    /**
     * reverse of this digraph
     *
     * @return
     */
    Digraph reverse();

    String toString();
}
