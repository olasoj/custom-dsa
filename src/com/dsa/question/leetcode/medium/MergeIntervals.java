package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {

    }

    public int[][] merge(int[][] intervals) {

        int length = intervals.length;
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<Integer[]> setR = new ArrayList<>(intervals.length);

        int i = 0;
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            while (i + 1 < length && start <= intervals[i + 1][0] && end >= intervals[i + 1][0]) {
                start = Math.min(start, intervals[i + 1][0]);
                end = Math.max(end, intervals[i + 1][1]);
                i++;
            }

            setR.add(new Integer[]{start, end});
            i++;
        }

        int[][] result = new int[setR.size()][2];

        int counter = 0;
        for (Integer[] r : setR) result[counter++] = new int[]{r[0], r[1]};
        return result;
    }
}
