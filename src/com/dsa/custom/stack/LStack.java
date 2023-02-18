package com.dsa.custom.stack;

import com.dsa.custom.list.linked.dlinked.DLink;

// Linked stack implementation
public class LStack<E> implements Stack<E> {
    private DLink<E> top;          // Pointer to first element
    private int size;             // Number of elements

    //Constructors
    public LStack() {
        top = new DLink<>(null, null);
        size = 0;
    }

    @Override
    public void clear() {// Reinitialize stack
        top = new DLink<>(null, null);
        size = 0;
    }

    @Override
    public void push(E it) {      // Put "it" on stack
        DLink<E> tempTopValue = new DLink<>(it, null, null);
        tempTopValue.setPrev(top);
        this.top = tempTopValue;
        size++;
    }

    @Override
    public E pop() {              // Remove "it" from stack
        if (isEmpty()) return null;

        E it = top.element();
        top = top.prev();
        size--;
        return it;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E topValue() {
        if (isEmpty()) return null;
        return top.element();
    }

    @Override
    public int length() {
        return size; // Return length
    }
}
