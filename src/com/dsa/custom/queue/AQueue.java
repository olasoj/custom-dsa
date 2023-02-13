package com.dsa.custom.queue;

// Array-based queue implementation
public class AQueue<E> implements Queue<E> {
    private static final int DEFAULT_SIZE = 10;
    private final int maxSize;  // Maximum size of queue
    private final E[] listArray;    // Array holding queue elements
    private int front;    // Index of front element
    private int rear;    // Index of rear element

    public AQueue() {
        this(DEFAULT_SIZE);
    }

    // For generic array
    @SuppressWarnings("unchecked")
    public AQueue(int size) {
        maxSize = size + 1;
        rear = 0;
        front = 1;
        listArray = (E[]) new Object[maxSize];   // Create listArray
    }

    public void clear() {        // Reinitialize
        rear = 0;
        front = 1;
    }

    public void enqueue(E it) {  // Put "it" in queue
        if (isFull()) return;

        rear = (rear + 1) % maxSize; // Circular increment
        listArray[rear] = it;
    }

    @Override
    public boolean isFull() {
        return ((rear + 2) % maxSize) == front;
    }

    public E dequeue() {         // Take element out of queue
        if (isEmpty()) return null;
        E it = listArray[front];
        front = (front + 1) % maxSize; // Circular increment
        return it;
    }

    public boolean isEmpty() {
        if (length() == 0) {
            System.out.println("Queue is empty");
            return true;
        }
        return false;
    }

    public E frontValue() {      // Get front value
        if (isEmpty()) return null;
        return listArray[front];
    }

    public int length() {      // Return length
        return ((rear + maxSize) - front + 1) % maxSize;
    }
}
