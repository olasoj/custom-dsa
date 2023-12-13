package com.dsa.ops.design.amortizedanalysis.twopointersmethod;

import java.util.Arrays;

import static java.lang.System.out;

public class SubArraySum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 5, 1, 1, 2};
        int sum = 8;

        int[] algorithm = algorithm(arr, sum);
        out.println(Arrays.toString(algorithm));
        out.println(arr[algorithm[0]]);
        out.println(arr[algorithm[1]]);
    }

    private static int[] algorithm(int[] arr, int sum) {
        int leftPointer = 0;
        int rightPointer = 1;
        int foundSum = arr[leftPointer] + arr[rightPointer];

        while (rightPointer < arr.length - 1 && leftPointer < rightPointer) {
            if (sum > foundSum) {
                foundSum = foundSum + arr[++rightPointer];
            } else if (sum < foundSum) {
                foundSum = foundSum - arr[leftPointer++];
            } else {
                return new int[]{leftPointer, rightPointer};
            }
        }

        return new int[]{-1, -1};
    }
}
