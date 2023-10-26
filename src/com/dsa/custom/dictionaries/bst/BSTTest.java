package com.dsa.custom.dictionaries.bst;

public class BSTTest {

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> kObjectBinarySearchTree = new BinarySearchTree<>();

        kObjectBinarySearchTree.insert(10, "yes please");
        kObjectBinarySearchTree.insert(11, "yes ");
        kObjectBinarySearchTree.insert(9, " please");
        kObjectBinarySearchTree.insert(16, "maybe please");
        kObjectBinarySearchTree.insert(8, "yes no  please");
        kObjectBinarySearchTree.insert(12, "yes how please");
        kObjectBinarySearchTree.insert(7, "no please");

        kObjectBinarySearchTree.print();
        System.out.println(kObjectBinarySearchTree.find(16));
    }
}
