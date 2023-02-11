package com.dsa.custom.dictionaries.rbtree;

import com.dsa.custom.dictionaries.Dictionary;

import java.util.concurrent.atomic.AtomicInteger;

public class RedBlackSearchTree<K extends Comparable<? super K>, E> implements Dictionary<K, E> {

    private final AtomicInteger nodeCount;
    private TTNode<K, E> rootInternal;

    public RedBlackSearchTree() {
        this.rootInternal = null;
        this.nodeCount = new AtomicInteger(0);
    }

    @Override
    public void clear() {
        nodeCount.set(0);
        rootInternal = null;
    }

    @Override
    public void insert(K k, E e) {
        rootInternal = insertHelp(rootInternal, k, e);
        nodeCount.getAndIncrement();
    }

    private TTNode<K, E> insertHelp(TTNode<K, E> rt, K k, E e) {
        TTNode<K, E> retval;

        // Empty tree: create a leaf node for root
        if (rt == null) return new TTNode<>(k, e, null, null, null, null, null);

        // At leaf node: insert here
        if (rt.isLeaf()) return rt.add(new TTNode<K, E>(k, e, null, null, null, null, null));

        // Add to internal node
        if (k.compareTo(rt.leftKey()) < 0) { // Insert left
            retval = insertHelp(rt.leftChild(), k, e);
            if (retval == rt.leftChild()) return rt;
            else return rt.add(retval);
        } else if ((rt.rightKey() == null) || (k.compareTo(rt.rightKey()) < 0)) {
            retval = insertHelp(rt.centerChild(), k, e);
            if (retval == rt.centerChild()) return rt;
            else return rt.add(retval);
        } else { // Insert right
            retval = insertHelp(rt.centerChild(), k, e);
            if (retval == rt.centerChild()) return rt;
            else return rt.add(retval);
        }
    }

    @Override
    public E remove(K k) {
        return null;
    }

    @Override
    public E removeAny() {
        return null;
    }

    @Override
    public E find(K k) {
        return findHelp(rootInternal, k);
    }

    private E findHelp(TTNode<K, E> root, K k) {
        if (root == null) return null;          // val not found
        if (k.compareTo(root.leftKey()) == 0) return root.leftValue();
        if ((root.rightKey() != null) && (k.compareTo(root.rightKey()) == 0)) return root.rightValue();

        if (k.compareTo(root.leftKey()) < 0) // Search left
            return findHelp(root.leftChild(), k);
        else if (root.rightKey() == null) // Search center
            return findHelp(root.centerChild(), k);

        else if (k.compareTo(root.rightKey()) < 0)  // Search center
            return findHelp(root.centerChild(), k);
        else return findHelp(root.rightChild(), k); // Search right
    }

    @Override
    public int size() {
        return nodeCount.get();
    }
}
