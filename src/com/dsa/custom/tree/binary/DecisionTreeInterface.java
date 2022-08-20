package com.dsa.custom.tree.binary;

import com.dsa.custom.tree.binary.core.BinaryTreeInterface;

public interface DecisionTreeInterface<T> extends BinaryTreeInterface<T> {
    /**
     * Gets the data in the current node.
     *
     * @return The data object in the current node, or
     * null if the current node is null.
     */
    T getCurrentData();

    /**
     * Sets the data in the current node.
     *
     * @param newData The new data object.
     */
    void setCurrentData(T newData);

    /**
     * Sets the data in the children of the current node,
     * creating them if they do not exist.
     *
     * @param responseForNo  The new data object for the left child.
     * @param responseForYes The new data object for the right
     *                       child.
     */

    void setResponses(T responseForNo, T responseForYes);

    /**
     * Sees whether the current node contains an answer.
     *
     * @return True if the current node is a leaf, or
     * false if it is a nonleaf.
     */
    boolean isAnswer();

    /**
     * Sets the current node to its left child.
     * If the child does not exist, sets the current node to null.
     * public void advanceToNo();
     * /** Sets the current node to its right child.
     * If the child does not exist, sets the current node to null.
     * public void advanceToYes();
     * /** Sets the current node to the root of the tree.
     */
    void resetCurrentNode();
}
