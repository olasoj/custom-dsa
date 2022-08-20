package com.dsa.custom.tree.binary.node;

/**
 * ADT for binary tree nodes
 */
public interface BinaryNode<E> {
    /**
     * Return and set the element value
     */
    E element();

    void setElement(E v);

    /**
     * Return the left child
     */
    BinaryNode<E> left();

    /**
     * Return the right child
     */
    BinaryNode<E> right();

    /**
     * Return true if this is a leaf node
     */
    boolean isLeaf();

    int numberOfNodes();

    int height();
}
