package com.dsa.custom.list.linked.llinked;

import java.util.Objects;
import java.util.StringJoiner;

public class Link<E> {
    // Extensions to support freelists
    private static Link FREE_LIST = null;     // Freelist for the class
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
        if (FREE_LIST == null)
            return new Link<E>(it, nextVal); // Get from "new"

        // Get from freelist
        Link<E> temp = FREE_LIST;
        FREE_LIST = FREE_LIST.next();
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
        this.element = it;
        return it;
    }

    void release() {
        element = null;
        next = FREE_LIST;
        FREE_LIST = this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Link)) return false;
        Link<?> link = (Link<?>) obj;
        return Objects.equals(element, link.element) && Objects.equals(next, link.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, next);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Link.class.getSimpleName() + "[", "]")
                .add("element=" + element)
                .add("next=" + next)
                .toString();
    }
}
