package com.dsa.custom.dictionaries.bst;

import com.dsa.custom.dictionaries.Dictionary;
import com.dsa.custom.tree.binary.node.pointer.BinarySearchTreeNode;

/**
 * Binary Search Tree implementation for Dictionary ADT
 */
public class BinarySearchTree<K extends Comparable<? super K>, E> implements Dictionary<K, E> {
    private int nodeCount;          // Number of nodes in the BST
    private BinarySearchTreeNode<K, E> root; // Root of the BST

    /**
     * Constructor
     */
    public BinarySearchTree() {
        this.root = null;
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

        while (rt != null) {

            if (rt.key().compareTo(k) > 0) {
                if (rt.left() == null) {
                    return null;
                } else {
                    rt = rt.left();
                }
            } else if (rt.key().compareTo(k) == 0) {
                return rt.element();
            } else if (rt.key().compareTo(k) < 0) {
                if (rt.right() == null) {
                    return null;
                } else
                    rt = rt.right();
            }
        }

        return null;
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
            nodeCount--;
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
        return nodeCount;
    }

    private BinarySearchTreeNode<K, E> insertHelp(BinarySearchTreeNode<K, E> rt, K k, E e) {

        BinarySearchTreeNode<K, E> rootLocal = rt; //Dummy node

        while (rootLocal != null) {

            if (rootLocal.key().compareTo(k) > 0) {
                if (rootLocal.left() != null) {
                    rootLocal = rootLocal.left();
                } else {
                    BinarySearchTreeNode<K, E> p = new BinarySearchTreeNode<>(k, e);
                    rootLocal.setLeft(p);
                    return rt;
                }
            } else if (rootLocal.key().compareTo(k) < 0) {
                if (rootLocal.right() != null) {
                    rootLocal = rootLocal.right();
                } else {
                    BinarySearchTreeNode<K, E> p = new BinarySearchTreeNode<>(k, e);
                    rootLocal.setRight(p);
                    return rt;
                }
            } else if (rootLocal.key().compareTo(k) == 0) {
                rootLocal.setElement(e);
                return rt;
            }
        }

        return new BinarySearchTreeNode<>(k, e);
    }


    private BinarySearchTreeNode<K, E> deleteMin(BinarySearchTreeNode<K, E> rt) {

        while (rt.left() != null) {
            rt = rt.left();
        }

        return rt.right();
    }

    /**
     * Remove a node with key value k
     *
     * @return The tree with the node removed
     */
    private BinarySearchTreeNode<K, E> removeHelp(BinarySearchTreeNode<K, E> rt, K k) {

        BinarySearchTreeNode<K, E> rootLocal = rt; //Dummy node

        while (rootLocal != null) {

            if (rootLocal.key().compareTo(k) > 0) {
                rootLocal = rootLocal.left();
            } else if (rootLocal.key().compareTo(k) < 0) {
                rootLocal = rootLocal.right();
            } else { // Found it:' rootLocal

                if (rootLocal.left() == null) return rootLocal.right();
                else if (rootLocal.right() == null) return rootLocal.left();
                else { // Two children
                    //TODO: Revisit
                    BinarySearchTreeNode<K, E> temp = getMin(rootLocal.right());

                    rootLocal.setElement(temp.element());
                    rootLocal.setKey(temp.key());
                    rootLocal.setRight(deleteMin(rootLocal.right()));
                    ///Return what???
                }
            }
        }

        return null;
    }

    private BinarySearchTreeNode<K, E> getMin(BinarySearchTreeNode<K, E> rt) {

        while (rt.left() != null) {
            rt = rt.left();
        }

        return rt;
    }


    public void print() {
        printHelp(root);
    }

    private void printHelp(BinarySearchTreeNode<K, E> rt) {
        if (rt == null) return;
        printVisit(rt.key(), rt.element());
        printHelp(rt.left());
        printHelp(rt.right());
    }

    private void printVisit(K key, E element) {
        System.out.println(key + ": x" + element);
    }


}
