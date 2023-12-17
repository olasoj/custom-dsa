package com.dsa.question.leetcode.medium;

import com.dsa.util.DSUtil;

import java.util.Comparator;
import java.util.Queue;

import static java.lang.System.out;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] arr = new int[]{100, 4, 200, 1, 3, 2};
        int[] arr2 = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        int r = algorithm(arr);
        out.println(r);
        out.println(algorithm(arr2));
    }

    private static int algorithm(int[] arr) {
        if (arr.length == 0) return 0;
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        Queue<Integer> queue = DSUtil.toPQ(arr, integerComparator);

        int result = 0;
        int lcs = 1;
        int cc = queue.poll();

        while (!queue.isEmpty()) {

            int nextCC = queue.poll();
            if (cc + 1 == nextCC || cc == nextCC) {
                if (cc + 1 == nextCC) lcs = lcs + 1;
                cc = nextCC;
                if (queue.isEmpty()) {
                    result = Math.max(lcs, result);
                }
            } else {
                result = Math.max(lcs, result);
                cc = nextCC;
                lcs = 1;
            }

        }

        return (result);
    }
}
