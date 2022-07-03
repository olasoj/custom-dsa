package com.dsa.custom.list.linked.dlinked;

public class DLink<E> {

    private E element;
    private DLink<E> next;
    private DLink<E> prev;

    // Doubly linked list node
// Value for this node
// Pointer to next node in list
// Pointer to previous node
// Constructors
    DLink(E it, DLink<E> n, DLink<E> p) {
        element = it;
        next = n;
        prev = p;
    }

    DLink(DLink<E> n, DLink<E> p) {
        next = n;
        prev = p;
    }

    DLink<E> next() {
        return next;
    }

    DLink<E> setNext(DLink<E> nextval) {
        return next = nextval;
    }

    DLink<E> prev() {
        return prev;
    }

    DLink<E> setPrev(DLink<E> prevval) {
        return prev = prevval;
    }

    E element() {
        return element;
    }

    E setElement(E it) {
        return element = it;
    }
}
