package com.dsa.question.leetcode.hard;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

public class FindMedianFromDataStream {

    private final List<Integer> tempStore;

    public FindMedianFromDataStream() {
        this.tempStore = new LinkedList<>();
    }

    public static void main(String[] args) {
        FindMedianFromDataStream findMedianFromDataStream = new FindMedianFromDataStream();

        findMedianFromDataStream.addNum(6);
        findMedianFromDataStream.addNum(10);
        findMedianFromDataStream.addNum(2);
        findMedianFromDataStream.addNum(6);
        findMedianFromDataStream.addNum(5);
        findMedianFromDataStream.addNum(0);
        findMedianFromDataStream.addNum(6);
        findMedianFromDataStream.addNum(3);
        findMedianFromDataStream.addNum(1);
        findMedianFromDataStream.addNum(0);
        findMedianFromDataStream.addNum(0);

        out.println(findMedianFromDataStream.tempStore);
    }

    public void addNum(int num) {

        if (tempStore.isEmpty()) {
            tempStore.add(num);
        } else {
            int size = tempStore.size();

            int i = 0;
            int end = size;

            while (i <= end) {

                int mid = (i + end) / 2;

                if (
                        end == 0
                                || (num >= tempStore.get(end - 1) && (end < size && num <= tempStore.get(end)))
                                || (end >= size && num >= tempStore.get(end - 1))) {
                    tempStore.add(end, num);
                    end = -1;
                } else if (num < tempStore.get(mid)) end = mid;
                else if (num >= tempStore.get(mid)) i = mid;
            }
        }
    }

    public double findMedian() {

        int size = tempStore.size();
        int mid = size / 2;

        if (size % 2 == 1) {
            return tempStore.get(mid);
        }

        double agg = tempStore.get(mid - 1) + (double) tempStore.get(mid);
        return (agg) / 2;
    }
}
