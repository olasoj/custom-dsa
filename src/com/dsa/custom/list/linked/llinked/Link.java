package com.dsa.custom.list.linked.llinked;

public class Link<E> {
    // Extensions to support freelists
    static Link freelist = null;     // Freelist for the class
    private E element;    // Value for this node
    private Link<E> next; // Point to next node in list

    // Constructors
    public Link(E it, Link<E> nextVal) {
        element = it;
        next = nextVal;
    }

    public Link(Link<E> nextVal) {
        next = nextVal;
    }

    // Get new link
    static <E> Link<E> get(E it, Link<E> nextVal) {
        if (freelist == null)
            return new Link<E>(it, nextVal); // Get from "new"

        // Get from freelist
        Link<E> temp = freelist;
        freelist = freelist.next();
        temp.setElement(it);
        temp.setNext(nextVal);
        return temp;

    }

    public Link<E> next() {
        return next;
    }

    public Link<E> setNext(Link<E> nextVal) {
        return next = nextVal;
    }

    public E element() {
        return element;
    }

    E setElement(E it) {
        return element = it;
    }

    void release() {
        element = null;
        next = freelist;
        freelist = this;
    }
}
