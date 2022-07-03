package com.dsa.custom.list.linked;

public class Link<E> {
    // Extensions to support freelists
    static Link freelist = null;     // Freelist for the class
    private E element;    // Value for this node
    private Link<E> next; // Point to next node in list

    // Constructors
    public Link(E it, Link<E> nextval) {
        element = it;
        next = nextval;
    }

    public Link(Link<E> nextval) {
        next = nextval;
    }

    // Get new link
    static <E> Link<E> get(E it, Link<E> nextval) {
        if (freelist == null)
            return new Link<E>(it, nextval); // Get from "new"
        Link<E> temp = freelist;
        freelist = freelist.next();
        temp.setElement(it);
        temp.setNext(nextval);
        return temp;
// Get from freelist
    }

    public Link<E> next() {
        return next;
    }

    public Link<E> setNext(Link<E> nextval) {
        return next = nextval;
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
