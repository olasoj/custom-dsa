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

    public LStack(int size) {
        top = null;
        this.size = size;
    }

    @Override
    public void clear() {// Reinitialize stack
        top = null;
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
        if (isStackEmpty()) return null;

        E it = top.element();
        top = top.prev();
        size--;
        return it;
    }

    private boolean isStackEmpty() {
        if (size == 0) {
            System.out.println("Stack is empty");
            return true;
        }
        return false;
    }

    @Override
    public E topValue() {
        if (isStackEmpty()) return null;
        return top.next().element();
    }

    @Override
    public int length() {
        return size; // Return length
    }
}
