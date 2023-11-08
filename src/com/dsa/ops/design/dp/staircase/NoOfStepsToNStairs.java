package com.dsa.ops.design.dp.staircase;

import com.dsa.util.PerformanceUtil;

import java.util.Arrays;

import static java.lang.System.out;

public class NoOfStepsToNStairs {

    public static void main(String[] args) {
        //Combination
        int answer = PerformanceUtil.measureOperationDuration(() -> cFunc(11_000));
        int answer2 = PerformanceUtil.measureOperationDuration(() -> kCFunc(11_000, 2));
        out.println(answer);
        out.println(answer2);

        //Optimization
        int[] prices = new int[]{3, 2, 1, 4, 6, 4, 9, 4};
        int answer3 = PerformanceUtil.measureOperationDuration(() -> oFunc(prices.length - 1, prices));
        int answer4 = PerformanceUtil.measureOperationDuration(() -> kOFunc(prices.length - 1, 2, prices));
        out.println(answer3);
        out.println(answer4);

    }

    //Optimization
    private static int oFunc(int n, int[] prices) {

        int[] computedValue = new int[n + 1];
        int[] from = new int[n + 1];

        computedValue[0] = 0;
        computedValue[1] = prices[1];
        from[1] = 1;

        for (int i = 2; i <= n; i++) {
            computedValue[i] = prices[i] + Math.min(computedValue[i - 1], computedValue[i - 2]);
            if (computedValue[i - (1)] < computedValue[i - 2]) from[i] = i - 1;
            else from[i] = i - (2);
        }

        out.println(Arrays.toString(from));
        return computedValue[n];
    }

    private static int kOFunc(int n, int k, int[] prices) {

        int[] computedValue = new int[n + 1];
        int[] from = new int[n + 1];

        computedValue[0] = 0;
        computedValue[1] = prices[1];
        from[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    computedValue[i] = prices[i] + Math.min(computedValue[i - (j - 1)], computedValue[i - j]);

                    if (computedValue[i - (j - 1)] < computedValue[i - j]) from[i] = i - (j - 1);
                    else from[i] = i - (j);
                }
            }
        }

        int path = n;
        out.println(path);
        do {
            int i = from[path];
            out.println(i);
            path = i;
        } while (path != 0);

        out.println(Arrays.toString(from));
        return computedValue[n];
    }


    //Combinations
    private static int cFunc(int n) {
        int[] calculatedSteps = new int[n + 1];

        calculatedSteps[0] = 1;
        calculatedSteps[1] = 1;

        for (int i = 2; i <= n; i++) {
            calculatedSteps[i] = calculatedSteps[i - 1] + calculatedSteps[i - 2];
        }

        return calculatedSteps[n];
    }

    private static int kCFunc(int n, int k) {
        int[] calculatedSteps = new int[n + 1];

        calculatedSteps[0] = 1;
        calculatedSteps[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    calculatedSteps[i] = calculatedSteps[i - (j - 1)] + calculatedSteps[i - j];
                }
            }
        }

        return calculatedSteps[n];
    }


}
