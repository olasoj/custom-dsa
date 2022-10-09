package com.dsa.custom.queue;

// Array-based queue implementation
public class AQueue<E> implements Queue<E> {
    private static final int defaultSize = 10;
    private final int maxSize;
    private final E[] listArray;
    private int front;
    private int rear;

    // Maximum size of queue
    // Index of front element
    // Index of rear element
    // Array holding queue elements
    // Constructors
    public AQueue() {
        this(defaultSize);
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
        if (isQueueFull()) return;

        rear = (rear + 1) % maxSize; // Circular increment
        listArray[rear] = it;
    }

    private boolean isQueueFull() {
        if (((rear + 2) % maxSize) == front) {
            System.out.println("Queue is full");
            return true;
        }
        return false;
    }

    public E dequeue() {         // Take element out of queue
        if (isQueueEmpty()) return null;
        E it = listArray[front];
        front = (front + 1) % maxSize; // Circular increment
        return it;
    }

    private boolean isQueueEmpty() {
        if (length() == 0) {
            System.out.println("Queue is empty");
            return true;
        }
        return false;
    }

    public E frontValue() {      // Get front value
        if (isQueueEmpty()) return null;
        return listArray[front];
    }

    public int length() {      // Return length
        return ((rear + maxSize) - front + 1) % maxSize;
    }
}
