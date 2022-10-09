package com.dsa.custom.queue;

import com.dsa.custom.list.linked.llinked.Link;

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
        front = rear = new Link<E>(null);
        size = 0;
    }

    public void clear() {
        init();
    }   // Reinitialize queue

    public void enqueue(E it) {  // Put element on rear
        rear.setNext(new Link<E>(it, null));
        rear = rear.next();
        size++;
    }

    public E dequeue() {         // remove element from front
        if (isQueueEmpty()) return null;

        E it = front.next().element();  // Store dequeued value
        front.setNext(front.next().next());  // Advance front
        if (front.next() == null) rear = front; // Last Object
        size--;
        return it;                      // Return Object
    }

    private boolean isQueueEmpty() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return true;
        }
        return false;
    }

    public E frontValue() {       // Get front element
        if (isQueueEmpty()) return null;
        return front.next().element();
    }

    public int length() {
        return size;
    } // Return length
}
