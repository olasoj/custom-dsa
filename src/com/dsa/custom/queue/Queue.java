package com.dsa.custom.queue;

/**
 * Queue ADT
 */
public interface Queue<E> {
    /**
     * Reinitialize the queue.  The user is responsible for
     * reclaiming the storage used by the queue elements.
     */
    void clear();

    /**
     * Place an element at the rear of the queue.
     *
     * @param it element being enqueued.
     */
    void enqueue(E it);

    boolean isFull();

    /**
     * Remove and return element at the front of the queue.
     *
     * @return The element at the front of the queue.
     */
    E dequeue();

    /**
     * @return The front element.
     */
    E frontValue();

    /**
     * @return The number of elements in the queue.
     */
    int length();

    boolean isEmpty();
}

