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
    public DLink(E it, DLink<E> n, DLink<E> p) {
        element = it;
        next = n;
        prev = p;
    }

    public DLink(DLink<E> next, DLink<E> previous) {
        this.next = next;
        prev = previous;
    }

    public DLink<E> next() {
        return next;
    }

    public DLink<E> setNext(DLink<E> nextVal) {
        return next = nextVal;
    }

    public DLink<E> prev() {
        return prev;
    }

    public DLink<E> setPrev(DLink<E> prevVal) {
        return prev = prevVal;
    }

    public E element() {
        return element;
    }

    public E setElement(E it) {
        return element = it;
    }
}
