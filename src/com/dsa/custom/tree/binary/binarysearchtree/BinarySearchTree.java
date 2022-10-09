package com.dsa.custom.tree.binary.binarysearchtree;

import com.dsa.custom.dictionaries.Dictionary;
import com.dsa.custom.tree.binary.node.pointer.BinarySearchTreeNode;

import java.util.Objects;

/**
 * Binary Search Tree implementation for Dictionary ADT
 */
public class BinarySearchTree<K extends Comparable<? super K>, E> implements Dictionary<K, E> {
    int nodeCount;             // Number of nodes in the BST
    private BinarySearchTreeNode<K, E> root; // Root of the BST

    /**
     * Constructor
     */
    public BinarySearchTree() {
        root = null;
        nodeCount = 0;
    }

    /**
     * Reinitialize tree
     */
    public void clear() {
        root = null;
        nodeCount = 0;
    }

    /**
     * Insert a record into the tree.
     *
     * @param k Key value of the record.
     * @param e The record to insert.
     */
    public void insert(K k, E e) {
        root = insertHelp(root, k, e);
        nodeCount++;
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
            nodeCount--;
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
    public E removeAny() {
        if (root != null) {
            E temp = root.element();
            root = removeHelp(root, root.key());
            nodeCount--;
            return temp;
        } else return null;
    }

    /**
     * @param k The key value to find.
     * @return Record with key value k, null if none exist.
     */
    public E find(K k) {
        return findHelp(root, k);
    }

    /**
     * @return The number of records in the dictionary.
     */
    public int size() {
        return nodeCount;
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

    private BinarySearchTreeNode<K, E> getMin(BinarySearchTreeNode<K, E> right) {
        return null;
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
