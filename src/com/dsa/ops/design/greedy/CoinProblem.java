package com.dsa.ops.design.greedy;

import com.dsa.util.DSUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

import static java.lang.System.out;

public class CoinProblem {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 10, 20, 50, 100, 200};
        int n = 520;

        algorithm(arr, n);

        arr = new int[]{186, 419, 83, 408};
        n = 6249;
        algorithm(arr, n);
    }

    private static void algorithm(int[] arr, int n) {
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        Queue<Integer> queue = DSUtil.toPQ(arr, integerComparator.reversed());

        List<Integer> selectedCoins = new ArrayList<>(arr.length);
        int counter = 0;
        int max = queue.remove();

        while (n > 0) {
            while (n - max < 0 && !queue.isEmpty())
                max = queue.remove();

            if (n - max < 0 || counter >= arr.length) break;
            n = n - max;
            selectedCoins.add(max);
        }

        out.println(selectedCoins);
        out.println(selectedCoins.size());
        out.println(n);
    }
}
