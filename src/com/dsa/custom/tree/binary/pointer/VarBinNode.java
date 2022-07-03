package com.dsa.custom.tree.binary.pointer;

public interface VarBinNode {
    boolean isLeaf();

    void traverse();
}

class VarLeafNode implements VarBinNode { // Leaf node
    private final String operand;                 // Operand value

    public VarLeafNode(String val) {
        operand = val;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    public String value() {
        return operand;
    }

    @Override
    public void traverse() {
//        Visit.VisitLeafNode(operand);
    }
}

class VarIntlNode implements VarBinNode { // Internal node
    private final VarBinNode left;
    private final VarBinNode right;
    private final Character operator;

    // Left child
// Right child
// Operator value
    public VarIntlNode(Character op,
                       VarBinNode l, VarBinNode r) {
        operator = op;
        left = l;
        right = r;
    }

    /**
     * Preorder traversal
     */
    public static void traverse(VarBinNode rt) {
        if (rt != null) rt.traverse();
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    public VarBinNode leftchild() {
        return left;
    }

    public VarBinNode rightchild() {
        return right;
    }

    public Character value() {
        return operator;
    }

    @Override
    public void traverse() {
//        Visit.VisitInternalNode(operator);
        if (left != null) left.traverse();
        if (right != null) right.traverse();
    }
}
