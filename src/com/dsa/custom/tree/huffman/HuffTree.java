package com.dsa.custom.tree.huffman;

public class HuffTree { // A Huffman coding tree
    private final HuffNode<LettFreq> root;  // Root of the tree

    public HuffTree(LettFreq val) {
        root = new HuffNode<>(val);
    }

    public HuffTree(LettFreq val, HuffTree l, HuffTree r) {
        root = new HuffNode<>(val, l.root(), r.root());
    }

    public HuffNode<LettFreq> root() {
        return root;
    }

    // Weight of tree is weight of root
    public int weight() {
        return root.element().weight();
    }
}