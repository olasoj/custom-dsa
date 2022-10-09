package com.dsa;

import com.dsa.custom.queue.AQueue;
import com.dsa.custom.queue.Queue;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Queue<Integer> integerLStack = new AQueue<>();

        integerLStack.dequeue();
        integerLStack.enqueue(90);
        integerLStack.enqueue(30);
        integerLStack.dequeue();
        integerLStack.enqueue(30);
        integerLStack.enqueue(90);
        integerLStack.dequeue();

    }
}
