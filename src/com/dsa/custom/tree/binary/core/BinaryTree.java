package com.dsa.custom.tree.binary.core;

import com.dsa.custom.tree.binary.node.GenericBinaryTreeNode;

import java.util.Iterator;
import java.util.Objects;

public class BinaryTree<E> implements BinaryTreeInterface<E> {
    private GenericBinaryTreeNode<E> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(E rootData) {
        root = new GenericBinaryTreeNode<>(rootData);
    }

    //} // end constructor
    public BinaryTree(E rootData, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        initializeTree(rootData, leftTree, rightTree);
    }

    private void initializeTree(E rootData, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new GenericBinaryTreeNode<>(rootData);
        if (Objects.nonNull(leftTree)) root.setLeft(leftTree.root);
        if (Objects.nonNull(rightTree)) root.setRight(rightTree.root);
    }

    public void setTree(E rootData, BinaryTreeInterface<E> leftTree, BinaryTreeInterface<E> rightTree) {
        initializeTree(rootData, (BinaryTree<E>) leftTree, (BinaryTree<E>) rightTree);
    }

    @Override
    public E rootData() {
        return null;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int numberOfNodes() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<E> preorderIterator() {
        return null;
    }

    @Override
    public Iterator<E> postorderIterator() {
        return null;
    }

    @Override
    public Iterator<E> inorderIterator() {
        return null;
    }

    @Override
    public Iterator<E> levelOrderIterator() {
        return null;
    }

    @Override
    public void setRootData(E rootData) {

    }

    public GenericBinaryTreeNode<E> copy(E data) {
        GenericBinaryTreeNode<E> newRoot = new GenericBinaryTreeNode<>(data);

//        if (leftChild != null) newRoot.setLeft(leftChild.copy());
//        if (rightChild != null) newRoot.setRight(rightChild.copy());

        return newRoot;
    } // end copy

}
