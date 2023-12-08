package com.dsa.ops;

import static java.lang.System.out;

public class MaxSubArraySum {

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 2, 4, -3, 5, 2, -5, 2};

        //
        algorithm1(arr);
        algorithm2(arr);
        algorithm3(arr);
    }

    private static void algorithm3(int[] arr) {
        int best = 0;
        int sum = 0;
        for (int k = 0; k < arr.length; k++) {
            sum = Math.max(arr[k], sum + arr[k]);
            best = Math.max(best, sum);
        }

        out.println(best);
    }

    private static void algorithm2(int[] arr) {
        int n = arr.length;

        int best = 0;
        for (int a = 0; a < n; a++) {
            int sum = 0;
            for (int b = a; b < n; b++) {
                sum += arr[b];
                best = Math.max(best, sum);
            }
        }
        out.println(best);
    }

    private static void algorithm1(int[] arr) {
        int n = arr.length;
        int best = 0;
        for (int a = 0; a < n; a++) {
            for (int b = a; b < n; b++) {
                int sum = 0;
                for (int k = a; k <= b; k++) {
                    sum += arr[k];
                }
                best = Math.max(best, sum);
            }
        }
        out.println(best);
    }
}
