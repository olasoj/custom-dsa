package com.dsa.custom.tree.huffman;

import com.dsa.custom.tree.binary.node.BinaryNode;

/**
 * Binary tree node implementation with just an element
 * field (no key) and pointers to children
 */
class HuffNode<E> implements BinaryNode<E> {
    private E element;         // Element for this node
    private HuffNode<E> left;   // Pointer to left child
    private HuffNode<E> right;  // Pointer to right child

    /**
     * Constructors
     */
    public HuffNode() {
        left = right = null;
    }

    public HuffNode(E val) {
        left = right = null;
        element = val;
    }

    public HuffNode(E val, HuffNode<E> l, HuffNode<E> r) {
        left = l;
        right = r;
        element = val;
    }

    /**
     * Return and set the element value
     */
    public E element() {
        return element;
    }

    public void setElement(E v) {
        element = v;
    }

    /**
     * Return and set the left child
     */
    public HuffNode<E> left() {
        return left;
    }

    public HuffNode<E> setLeft(HuffNode<E> p) {
        return left = p;
    }

    /**
     * Return and set the right child
     */
    public HuffNode<E> right() {
        return right;
    }

    public HuffNode<E> setRight(HuffNode<E> p) {
        return right = p;
    }

    /**
     * Return true if this is a leaf node
     */
    public boolean isLeaf() {
        return (left == null) && (right == null);
    }
}
