package com.dsa.custom.tree.binary.node;

import java.util.Objects;

public class GenericBinaryTreeNode<E> implements BinaryNode<E> {

    private E data;
    private BinaryNode<E> leftChild;
    private BinaryNode<E> rightChild;

    public GenericBinaryTreeNode() {
        this(null);
    }

    public GenericBinaryTreeNode(E data) {
        this(data, null, null);
    }

    public GenericBinaryTreeNode(E data, BinaryNode<E> leftChild, BinaryNode<E> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public E element() {
        return data;
    }

    @Override
    public void setElement(E v) {
        this.data = v;
    }

    @Override
    public BinaryNode<E> left() {
        return leftChild;
    }

    public void setLeft(BinaryNode<E> p) {
        leftChild = p;
    }

    /**
     * Detects whether this node has a left child.
     *
     * @return True if the node has a left child.
     */
    public boolean hasLeft() {
        return leftChild != null;
    }

    @Override
    public BinaryNode<E> right() {
        return rightChild;
    }

    public void setRight(BinaryNode<E> p) {
        rightChild = p;
    }


    @Override
    public boolean isLeaf() {
        return (Objects.isNull(leftChild) && Objects.isNull(rightChild));
    }

    /**
     * Counts the nodes in the subtree rooted at this node.
     *
     * @return The number of nodes in the subtree rooted at
     */
    @Override
    public int numberOfNodes() {
//          <See Segment 25.10 >
        return 1;
    }

    /**
     * Counts the nodes in the subtree rooted at this node.
     *
     * @return The height of the subtree rooted at this node.
     */
    @Override
    public int height() {
        return 1;
    }

    /**
     * Copies the subtree rooted at this node.
     *
     * @return The root of a copy of the subtree rooted at this
     */
    public BinaryNode<E> copy() {
        return null;
    }


}
