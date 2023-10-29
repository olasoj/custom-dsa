package com.dsa.custom.graph.search;

//    find vertices connected to a source vertex
public interface GraphSearch {

    void search(int v);

    //    how many vertices are connected to s?
    boolean marked(int v);

    //    s is v connected to s?
    int count();

    void visit(int v, int e);

    void preVisit(int v);

    void postVisit(int v);
}
