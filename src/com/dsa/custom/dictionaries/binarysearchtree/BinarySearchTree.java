package com.dsa.custom.dictionaries.binarysearchtree;

import com.dsa.custom.dictionaries.Dictionary;
import com.dsa.custom.tree.binary.node.pointer.BinarySearchTreeNode;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Binary Search Tree implementation for Dictionary ADT
 */
public class BinarySearchTree<K extends Comparable<? super K>, E> implements Dictionary<K, E> {
    private final AtomicInteger nodeCount;          // Number of nodes in the BST
    private BinarySearchTreeNode<K, E> root; // Root of the BST

    /**
     * Constructor
     */
    public BinarySearchTree() {
        this.root = null;
        nodeCount = new AtomicInteger();
    }

    /**
     * Reinitialize tree
     */
    public void clear() {
        root = null;
        nodeCount.set(0);
    }

    /**
     * Insert a record into the tree.
     *
     * @param k Key value of the record.
     * @param e The record to insert.
     */
    public void insert(K k, E e) {
        root = insertHelp(root, k, e);
        nodeCount.getAndIncrement();
    }

    /**
     * Remove a record from the tree.
     *
     * @param k Key value of record to remove.
     * @return Record removed, or null if there is none.
     */
    public E remove(K k) {
        E temp = findHelp(root, k);   // First find it
        if (temp != null) {
            root = removeHelp(root, k); // Now remove it
            nodeCount.getAndDecrement();
        }
        return temp;
    }

    private E findHelp(BinarySearchTreeNode<K, E> rt, K k) {
        if (rt == null) return null;
        if (rt.key().compareTo(k) > 0)
            return findHelp(rt.left(), k);
        else if (rt.key().compareTo(k) == 0) return rt.element();
        else return findHelp(rt.right(), k);
    }


    /**
     * Remove and return the root node from the dictionary.
     *
     * @return The record removed, null if tree is empty.
     */
    @Override
    public E removeAny() {
        if (root != null) {
            E temp = root.element();
            root = removeHelp(root, root.key());
            nodeCount.getAndDecrement();
            return temp;
        } else return null;
    }

    /**
     * @param k The key value to find.
     * @return Record with key value k, null if none exist.
     */
    @Override
    public E find(K k) {
        return findHelp(root, k);
    }

    /**
     * @return The number of records in the dictionary.
     */
    @Override
    public int size() {
        return nodeCount.get();
    }

    private BinarySearchTreeNode<K, E> insertHelp(BinarySearchTreeNode<K, E> rt, K k, E e) {
        if (rt == null) return new BinarySearchTreeNode<>(k, e);
        if (rt.key().compareTo(k) > 0)
            rt.setLeft(insertHelp(rt.left(), k, e));
        else
            rt.setRight(insertHelp(rt.right(), k, e));
        return rt;
    }


    private BinarySearchTreeNode<K, E> deleteMin(BinarySearchTreeNode<K, E> rt) {
        if (rt.left() == null)
            return rt.right();
        else {
            rt.setLeft(deleteMin(rt.left()));
            return rt;
        }
    }

    /**
     * Remove a node with key value k
     *
     * @return The tree with the node removed
     */
    private BinarySearchTreeNode<K, E> removeHelp(BinarySearchTreeNode<K, E> rt, K k) {
        if (rt == null) return null;
        if (rt.key().compareTo(k) > 0)
            rt.setLeft(removeHelp(rt.left(), k));
        else if (rt.key().compareTo(k) < 0)
            rt.setRight(removeHelp(rt.right(), k));
        else { // Found it
            if (rt.left() == null)
                return rt.right();
            else if (rt.right() == null)
                return rt.left();
            else { // Two children
                BinarySearchTreeNode<K, E> temp = getMin(rt.right());

                assert !Objects.isNull(temp);
                rt.setElement(temp.element());
                rt.setKey(temp.key());
                rt.setRight(deleteMin(rt.right()));
            }
        }
        return rt;
    }

    private BinarySearchTreeNode<K, E> getMin(BinarySearchTreeNode<K, E> binarySearchTreeNode) {
        if (Objects.isNull(binarySearchTreeNode)) return null;

        BinarySearchTreeNode<K, E> leftBinarySearchTreeNode = binarySearchTreeNode.left();
        BinarySearchTreeNode<K, E> rightBinarySearchTreeNode = binarySearchTreeNode.right();
        if (leftBinarySearchTreeNode.key().compareTo(rightBinarySearchTreeNode.key()) < 0)
            return leftBinarySearchTreeNode;

        return rightBinarySearchTreeNode;
    }


    private void printHelp(BinarySearchTreeNode<K, E> rt) {
        if (rt == null) return;
        printHelp(rt.left());
        printVisit(rt.element());
        printHelp(rt.right());
    }

    private void printVisit(E element) {

    }


}
