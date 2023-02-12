package com.dsa.custom.graph.search;

//    find vertices connected to a source vertex
public interface GraphSearch {

    //    how many vertices are connected to s?
    boolean marked(int v);

    //    s is v connected to s?
    int count();
}
