package com.dsa.custom.queue;

import com.dsa.custom.list.linked.llinked.Link;

import java.util.Objects;
import java.util.StringJoiner;

// Linked queue implementation
public class LQueue<E> implements Queue<E> {
    int size;
    private Link<E> front;
    private Link<E> rear;

    // Pointer to front queue node
    // Pointer to rear queueNode
    // Number of elements in queue
    // Constructors
    public LQueue() {
        init();
    }

    public LQueue(int size) {
        init();
    } // Ignore size

    private void init() {              // Initialize queue
        front = rear = new Link<>(null);
        size = 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public void clear() {
        init();
    }   // Reinitialize queue

    public void enqueue(E it) {  // Put element on rear
        rear.setNext(new Link<>(it, null));
        rear = rear.next();
        size++;
    }

    public E dequeue() {         // remove element from front
        if (isEmpty()) return null;

        Link<E> frontLocal = front.next();
        E it = frontLocal.element();  // Store dequeued value
        front.setNext(frontLocal.next());  // Advance front
        size--;
        return it;                      // Return Object
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E frontValue() {       // Get front element
        if (isEmpty()) return null;
        return front.next().element();
    }

    public int length() {
        return size;
    } // Return length

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LQueue)) return false;
        LQueue<?> lQueue = (LQueue<?>) obj;
        return size == lQueue.size && Objects.equals(front, lQueue.front) && Objects.equals(rear, lQueue.rear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, front, rear);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LQueue.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("front=" + front)
                .add("rear=" + rear)
                .toString();
    }
}
