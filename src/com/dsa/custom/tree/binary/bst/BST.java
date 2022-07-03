package com.dsa.custom.tree.binary.bst;

import com.dsa.custom.dictionaries.Dictionary;
import com.dsa.custom.tree.binary.pointer.BSTNode;

/**
 * Binary Search Tree implementation for Dictionary ADT
 */
class BST<K extends Comparable<? super K>, E> implements Dictionary<K, E> {
    int nodecount;             // Number of nodes in the BST
    private BSTNode<K, E> root; // Root of the BST

    /**
     * Constructor
     */
    BST() {
        root = null;
        nodecount = 0;
    }

    /**
     * Reinitialize tree
     */
    public void clear() {
        root = null;
        nodecount = 0;
    }

    /**
     * Insert a record into the tree.
     *
     * @param k Key value of the record.
     * @param e The record to insert.
     */
    public void insert(K k, E e) {
        root = inserthelp(root, k, e);
        nodecount++;
    }

    /**
     * Remove a record from the tree.
     *
     * @param k Key value of record to remove.
     * @return Record removed, or null if there is none.
     */
    public E remove(K k) {
        E temp = findhelp(root, k);   // First find it
        if (temp != null) {
            root = removehelp(root, k); // Now remove it
            nodecount--;
        }
        return temp;
    }

    private E findhelp(BSTNode<K, E> rt, K k) {
        if (rt == null) return null;
        if (rt.key().compareTo(k) > 0)
            return findhelp(rt.left(), k);
        else if (rt.key().compareTo(k) == 0) return rt.element();
        else return findhelp(rt.right(), k);
    }


    /**
     * Remove and return the root node from the dictionary.
     *
     * @return The record removed, null if tree is empty.
     */
    public E removeAny() {
        if (root != null) {
            E temp = root.element();
            root = removehelp(root, root.key());
            nodecount--;
            return temp;
        } else return null;
    }

    /**
     * @param k The key value to find.
     * @return Record with key value k, null if none exist.
     */
    public E find(K k) {
        return findhelp(root, k);
    }

    /**
     * @return The number of records in the dictionary.
     */
    public int size() {
        return nodecount;
    }

    private BSTNode<K, E> inserthelp(BSTNode<K, E> rt, K k, E e) {
        if (rt == null) return new BSTNode<K, E>(k, e);
        if (rt.key().compareTo(k) > 0)
            rt.setLeft(inserthelp(rt.left(), k, e));
        else
            rt.setRight(inserthelp(rt.right(), k, e));
        return rt;
    }


    private BSTNode<K, E> deletemin(BSTNode<K, E> rt) {
        if (rt.left() == null)
            return rt.right();
        else {
            rt.setLeft(deletemin(rt.left()));
            return rt;
        }
    }

    /**
     * Remove a node with key value k
     *
     * @return The tree with the node removed
     */
    private BSTNode<K, E> removehelp(BSTNode<K, E> rt, K k) {
        if (rt == null) return null;
        if (rt.key().compareTo(k) > 0)
            rt.setLeft(removehelp(rt.left(), k));
        else if (rt.key().compareTo(k) < 0)
            rt.setRight(removehelp(rt.right(), k));
        else { // Found it
            if (rt.left() == null)
                return rt.right();
            else if (rt.right() == null)
                return rt.left();
            else { // Two children
                BSTNode<K, E> temp = getmin(rt.right());
                rt.setElement(temp.element());
                rt.setKey(temp.key());
                rt.setRight(deletemin(rt.right()));
            }
        }
        return rt;
    }

    private BSTNode<K, E> getmin(BSTNode<K, E> right) {
        return null;
    }


    private void printhelp(BSTNode<K, E> rt) {
        if (rt == null) return;
        printhelp(rt.left());
        printVisit(rt.element());
        printhelp(rt.right());
    }

    private void printVisit(E element) {

    }


}
