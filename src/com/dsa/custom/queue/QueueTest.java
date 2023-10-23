package com.dsa.custom.queue;

public class QueueTest {

    public static void main(String[] args) {
        LQueue<Integer> objects = new LQueue<>();

        objects.enqueue(10);
        objects.enqueue(12);
        objects.enqueue(13);

        while (!objects.isEmpty()) {
            System.out.println(objects.dequeue());
        }
    }
}
