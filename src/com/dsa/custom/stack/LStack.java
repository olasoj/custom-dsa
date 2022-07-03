package com.dsa.custom.stack;

import com.dsa.custom.list.linked.Link;

// Linked stack implementation
class LStack<E> implements Stack<E> {
    private Link<E> top;          // Pointer to first element
    private int size;             // Number of elements

    //Constructors
    public LStack() {
        top = null;
        size = 0;
    }

    public LStack(int size) {
        top = null;
        size = 0;
    }

    public void clear() {
        top = null;
    } // Reinitialize stack

    public void push(E it) {      // Put "it" on stack
        top = new Link<E>(it, top);
        size++;
    }

    public E pop() {              // Remove "it" from stack
        assert top != null : "Stack is empty";
        E it = top.element();
        top = top.next();
        size--;
        return it;
    }

    public E topValue() {
        assert top != null : "Stack is empty";
        return top.element();
    }

    public int length() {
        return size;
    } // Return length
}
