package com.dsa.ops.design.rangequeries;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static java.lang.System.out;

public class PrefixSum {

    public static void main(String[] args) {


        int[] arr = new int[]{3, 3, 1, 1, 1, 5, 2, 2};

        int[] prefixSum = buildPrefixSum(arr);

        out.println(Arrays.toString(arr));
        out.println(Arrays.toString(prefixSum));

        //Get array value at x position
        int queryIndex = 6;
        int originalValue = getOriginalValue(prefixSum, queryIndex);
        out.println(originalValue);
    }

    @NotNull
    private static int[] buildPrefixSum(int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = arr[i] - arr[i - 1];
        }
        return prefixSum;
    }

    private static int getOriginalValue(int[] prefixSum, int queryIndex) {
        int result = 0;
        for (int i = 0; i <= queryIndex; i++) {
            result = result + prefixSum[i];
        }
        return result;
    }
}
