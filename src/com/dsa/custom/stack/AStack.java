package com.dsa.custom.stack;

/**
 * Array-based stack implementation
 */
public class AStack<E> implements Stack<E> {
    private static final int DEFAULT_SIZE = 10;
    private final int maxSize;
    private final E[] listArray;
    private int top;

    // Maximum size of stack
    // Index for top Object
    // Array holding stack
    // Constructors
    public AStack() {
        this(DEFAULT_SIZE);
    }

    // Generic array allocation
    @SuppressWarnings("unchecked")
    public AStack(int size) {
        maxSize = size;
        top = 0;
        listArray = (E[]) new Object[size];   // Create listArray
    }

    public void clear() {             // Reinitialize stack
        top = 0;
    }

    public void push(E it) {    // Push "it" onto stack
        if (isFull()) return;
        listArray[top++] = it;
    }

    private boolean isFull() {
        return top == maxSize;
    }

    public E pop() {            // Pop top element
        if (isEmpty()) return null;
        return listArray[--top];
    }

    public E topValue() {       // Return top element
        if (isEmpty()) return null;
        return listArray[top - 1];

    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int length() {
        return top;
    }    // Return length
}
