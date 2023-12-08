package com.dsa.ops.design.greedy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

public class Scheduling {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 3},
                {2, 5},
                {3, 9},
                {6, 8},
        };

        algorithm(arr);
    }

    private static void algorithm(int[][] arr) {

        List<Integer> queue = new LinkedList<>();
        queue.add(arr[0][1]);
        for (int i = 1; i < arr.length; i++) {
            int startTime = arr[i][0];
            int endTime = arr[i][1];

            Iterator<Integer> iterator = queue.iterator();

            boolean enqueued = false;
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next <= startTime) {
                    queue.remove(next);
                    queue.add(endTime);
                    enqueued = true;
                    break;
                }
            }
            if (!enqueued) queue.add(endTime);
        }

        out.println(queue.size());
    }
}
