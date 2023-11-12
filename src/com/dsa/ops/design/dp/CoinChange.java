package com.dsa.ops.design.dp;

import com.dsa.util.PerformanceUtil;

import static java.lang.System.out;

public class CoinChange {

    public static void main(String[] args) {

        int[] coins = new int[]{1, 3, 5, 10};
        int answer3 = PerformanceUtil.measureOperationDuration(() -> cCoinChange(5, coins));
        out.println(answer3);
    }

    private static int cCoinChange(int n, int[] coins) {

        int[] computedValue = new int[n + 1];

        computedValue[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    computedValue[i] += computedValue[i - coin];
                }
            }
        }
        return computedValue[n];
    }
}
