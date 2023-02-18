package com.dsa.custom.graph.cc;

public interface ConnectedComponent {

    //     are v and w connected?
    boolean connected(int v, int w);

    //    number of connected components
    int count();

    //    component identifier for v ( between 0 and count()-1 )
    int id(int v);

}
