package com.dsa.custom.tree;

import java.util.Iterator;

public interface TreeIteratorInterface<T> {
    Iterator<T> preorderIterator();

    Iterator<T> postorderIterator();

    Iterator<T> inorderIterator();

    Iterator<T> levelOrderIterator();
}
