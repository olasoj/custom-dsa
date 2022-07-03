package com.dsa.custom.tree.binary;

/**
 * ADT for binary tree nodes
 */
public interface BinNode<E> {
    /**
     * Return and set the element value
     */
    E element();

    void setElement(E v);

    /**
     * Return the left child
     */
    BinNode<E> left();

    /**
     * Return the right child
     */
    BinNode<E> right();

    /**
     * Return true if this is a leaf node
     */
    boolean isLeaf();
}
