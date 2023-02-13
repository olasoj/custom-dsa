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
        if (isStackFull()) return;
        listArray[top++] = it;
    }

    private boolean isStackFull() {
        if (top == maxSize) {
            System.out.println("Stack is full");
            return true;
        }
        return false;
    }

    public E pop() {            // Pop top element
        if (isStackEmpty()) return null;
        return listArray[--top];
    }

    public E topValue() {       // Return top element
        if (isStackEmpty()) return null;
        return listArray[top - 1];

    }

    private boolean isStackEmpty() {
        if (top == 0) {
            System.out.println("Stack is empty");
            return true;
        }
        return false;
    }

    public int length() {
        return top;
    }    // Return length
}
