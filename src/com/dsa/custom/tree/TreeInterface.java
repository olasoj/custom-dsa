package com.dsa.custom.tree;

public interface TreeInterface<T> {
    T rootData();

    int height();

    int numberOfNodes();

    boolean isEmpty();

    void clear();
}
