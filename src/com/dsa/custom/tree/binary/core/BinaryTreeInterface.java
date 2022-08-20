package com.dsa.custom.tree.binary.core;

import com.dsa.custom.tree.TreeInterface;
import com.dsa.custom.tree.TreeIteratorInterface;

public interface BinaryTreeInterface<E> extends TreeInterface<E>, TreeIteratorInterface<E> {

    /**
     * Sets the data in the root of this binary tree.
     *
     * @param rootData The object that is the data for the
     *                 tree's root.
     */

    void setRootData(E rootData);

    /**
     * Sets this binary tree to a new binary tree.
     *
     * @param rootData  The object that is the data for the new
     *                  tree's root.
     *                  <p>
     *                  leftTree,
     *                  BinaryTreeInterface<T>
     *                  rightTree);
     * @param leftTree  The left subtree of the new tree.
     * @param rightTree The right subtree of the new tree.
     */
    void setTree(E rootData, BinaryTreeInterface<E> leftTree, BinaryTreeInterface<E> rightTree);
}
