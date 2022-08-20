package com.dsa.custom.tree.binary.node.pointer;

import com.dsa.custom.tree.binary.node.BinaryNode;

/**
 * Binary tree node implementation: Pointers to children
 */
public class BinarySearchTreeNode<K, E> implements BinaryNode<E> {
    private K key;              // Key for this node
    private E element;          // Element for this node
    private BinarySearchTreeNode<K, E> left;  // Pointer to left child
    private BinarySearchTreeNode<K, E> right; // Pointer to right child

    /**
     * Constructors
     */
    public BinarySearchTreeNode() {
        left = right = null;
    }

    public BinarySearchTreeNode(K k, E val) {
        left = right = null;
        key = k;
        element = val;
    }

    public BinarySearchTreeNode(K k, E val, BinarySearchTreeNode<K, E> l, BinarySearchTreeNode<K, E> r) {
        left = l;
        right = r;
        key = k;
        element = val;
    }

    /**
     * Return and set the key value
     */
    public K key() {
        return key;
    }

    public void setKey(K k) {
        key = k;
    }

    /**
     * Return and set the element value
     */
    @Override
    public E element() {
        return element;
    }

    @Override
    public void setElement(E v) {
        element = v;
    }

    /**
     * Return and set the left child
     */
    @Override
    public BinarySearchTreeNode<K, E> left() {
        return left;
    }

    public void setLeft(BinarySearchTreeNode<K, E> p) {
        left = p;
    }

    /**
     * Return and set the right child
     */
    @Override
    public BinarySearchTreeNode<K, E> right() {
        return right;
    }

    public void setRight(BinarySearchTreeNode<K, E> p) {
        right = p;
    }

    /**
     * Return true if this is a leaf node
     */
    @Override
    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    @Override
    public int numberOfNodes() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }
}

