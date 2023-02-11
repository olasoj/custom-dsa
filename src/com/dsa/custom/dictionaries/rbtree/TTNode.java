package com.dsa.custom.dictionaries.rbtree;

/**
 * 2-3 tree node implementation
 */
public class TTNode<K extends Comparable<? super K>, E> {

    //The node's left key
    private K leftKey;
    //The left record
    private E leftValue;

    //The node's right key
    private K rightKey;
    //The right record
    private E rightValue;

    // Pointer to left child
    private TTNode<K, E> left;

    // Pointer to middle child
    private TTNode<K, E> center;

    // Pointer to right child
    private TTNode<K, E> right;

    public TTNode() {
        center = left = right = null;
    }

    public TTNode(K lk, E lv, K rk, E rv, TTNode<K, E> p1,
                  TTNode<K, E> p2, TTNode<K, E> p3) {
        this.leftKey = lk;
        this.rightKey = rk;
        this.leftValue = lv;
        this.rightValue = rv;
        this.left = p1;
        this.center = p2;
        this.right = p3;
    }

    public boolean isLeaf() {
        return left == null;
    }

    public TTNode<K, E> leftChild() {
        return left;
    }

    public TTNode<K, E> rightChild() {
        return right;
    }

    public TTNode<K, E> centerChild() {
        return center;
    }

    public K leftKey() {
        return leftKey;
    }  // Left key

    public E leftValue() {
        return leftValue;
    }  // Left value

    public K rightKey() {
        return rightKey;
    }  // Right key

    public E rightValue() {
        return rightValue;
    }  // Right value

    public void setLeft(K k, E e) {
        leftKey = k;
        leftValue = e;
    }

    public void setRight(K k, E e) {
        rightKey = k;
        rightValue = e;
    }

    public void setLeftChild(TTNode<K, E> it) {
        left = it;
    }

    public void setCenterChild(TTNode<K, E> it) {
        center = it;
    }

    public void setRightChild(TTNode<K, E> it) {
        right = it;
    }

    /**
     * Add a new key/value pair to the node. There might be a
     * subtree associated with the record being added. This
     * information comes in the form of a 2-3 tree node with
     * one key and a (possibly null) subtree through the
     * center pointer field.
     */
    public TTNode<K, E> add(TTNode<K, E> it) {
        if (rightKey == null) { // Only one key, add here
            if (leftKey.compareTo(it.leftKey()) < 0) {
                rightKey = it.leftKey();
                rightValue = it.leftValue();
                right = center;
                center = it.centerChild();
            } else {
                rightKey = leftKey;
                rightValue = leftValue;
                right = center;
                leftKey = it.leftKey();
                leftValue = it.leftValue();
                center = it.centerChild();
            }
            return this;
        } else if (leftKey.compareTo(it.leftKey()) >= 0) { // Add left
            center = new TTNode<K, E>(rightKey, rightValue, null, null,
                    center, right, null);
            rightKey = null;
            rightValue = null;
            right = null;
            it.setLeftChild(left);
            left = it;
            return this;
        } else if (rightKey.compareTo(it.leftKey()) < 0) { // Add center
            it.setCenterChild(new TTNode<K, E>(rightKey, rightValue, null,
                    null, it.centerChild(), right, null));
            it.setLeftChild(this);
            rightKey = null;
            rightValue = null;
            right = null;
            return it;
        } else { // Add right
            TTNode<K, E> N1 = new TTNode<K, E>(rightKey, rightValue, null,
                    null, this, it, null);
            it.setLeftChild(right);
            right = null;
            rightKey = null;
            rightValue = null;
            return N1;
        }
    }
}
