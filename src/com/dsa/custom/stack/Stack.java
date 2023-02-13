package com.dsa.custom.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Stack<E> extends Iterable<E> {
    /**
     * Reinitialize the stack.  The user is responsible for
     * reclaiming the storage used by the stack elements.
     */
    void clear();

    /**
     * Push an element onto the top of the stack.
     *
     * @param it The element being pushed onto the stack.
     */
    void push(E it);

    /**
     * Remove and return the element at the top of the stack.
     *
     * @return The element at the top of the stack.
     */
    E pop();

    /**
     * @return A copy of the top element.
     */
    E topValue();

    /**
     * @return The number of elements in the stack.
     */
    int length();

    @Override
    default Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return length() > 0;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return pop();
            }
        };
    }
}
