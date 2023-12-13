package com.dsa.question.leetcode.easy;

import static java.lang.System.out;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        int[] arr2 = new int[]{7, 6, 4, 3, 1};
        int[] arr3 = new int[]{2, 1, 2, 1, 0, 1, 2};
        int r = algorithm(arr);
        int r2 = algorithm(arr2);
        out.println(r);
        out.println(r2);
        out.println(algorithm2(arr3));
        out.println(algorithm(arr3));
    }

    public static int algorithm2(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int sell = 0;

        for (int price : prices) {
            buy = Math.min(buy, price);
            sell = Math.max(sell, price - buy);
        }

        return sell;
    }

    private static int algorithm(int[] arr) {

        int startIndex = 0;
        int maxProfit = 0;
        int i = 0;
        while (i < arr.length - 1) {
            if (i == 0 || arr[i] < arr[startIndex]) {
                startIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] > arr[i] && arr[j] - arr[i] > maxProfit) {
                        maxProfit = arr[j] - arr[i];
                    }
                }
            }
            i++;
        }
        return maxProfit;
    }
}
