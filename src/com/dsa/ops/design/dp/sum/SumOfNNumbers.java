package com.dsa.ops.design.dp.sum;

import com.dsa.util.PerformanceUtil;

public class SumOfNNumbers {

    public static void main(String[] args) {

        Integer sum = PerformanceUtil.measureOperationDuration(() -> sumOfNNumbers(1_000_363));
        System.out.println(sum);
    }

    private static int sumOfNNumbers(int n) {

        int[] computedValues = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            computedValues[i] = computedValues[i - 1] + i;
        }

        return computedValues[n];
    }
}
