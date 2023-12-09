package com.dsa.ops.design.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class CoinProblem {

    static int counterGlobal = 0;

    public static void main(String[] args) {
        int[] coins = {1, 3, 4};
        int n = 10;

        algorithm(coins, n);
        algorithm(coins, 5);
        algorithm2(coins, n);
        algorithm2(new int[]{2}, 3);

        out.println(algorithm3(new int[]{2}, 3));
        out.println(algorithm3(coins, n));
        out.println(algorithm4(coins, 5));
        out.println(algorithm5(coins, 5));
    }

    private static int algorithm5(int[] coins, int n) {
        int[] computedValue = new int[n + 1];

        computedValue[0] = 1;
        for (int x = 1; x <= n; x++) {
            for (int c : coins) {
                if (x - c >= 0) {
                    computedValue[x] += computedValue[x - c];
                }
            }
        }
        return computedValue[n];
    }

    private static int algorithm4(int[] coins, int n) {
        return solve(coins, n);
    }

    private static int solve(int[] coins, int n) {
        if (n == 0) return 1;

        int counter = 0;
        for (int coin : coins) {
            if (n - coin >= 0) {
                counter = 1 + solve(coins, n - coin);
            }

        }

        return counter;
    }

    //Minimum number of coins needed to make a coin. Iterative
    private static int algorithm3(int[] coins, int n) {
        int[] computedValue = new int[n + 1];
        int[] path = new int[n + 1];

        computedValue[0] = 0;

        for (int i = 1; i <= n; i++) {
            computedValue[i] = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i - c >= 0) {
                    int i1 = computedValue[i - c];
                    computedValue[i] = Math.min(computedValue[i], i1 == Integer.MAX_VALUE ? i1 : 1 + i1);
                    path[i] = c;
                }
            }
        }

        out.println(Arrays.toString(path));
        return computedValue[n];
    }

    //Complete search for coin change
    private static void algorithm(int[] coins, int n) {

        List<Integer> selectedCoins = new ArrayList<>();
        List<List<Integer>> allSelectedCoins = new ArrayList<>();

        solve(coins, n, allSelectedCoins, selectedCoins);
        out.println(allSelectedCoins);
        out.println("Number of ways to make a coin change: " + allSelectedCoins.size());
    }


    //Minimum number of coins needed to make a coin. Recursive
    private static void algorithm2(int[] coins, int n) {
        int[] path = new int[n + 1];
        boolean[] ready = new boolean[n + 1];
        int[] value = new int[n + 1];
        int numberOfCoins = solve(coins, n, path, ready, value);

        out.println(numberOfCoins);
        out.println(Arrays.toString(path));
        out.println(Arrays.toString(value));
    }


    private static int solve(int[] coins, int x, int[] path, boolean[] ready, int[] value) {
        if (x < 0) return Integer.MAX_VALUE;
        if (x == 0) return 0;
        if (ready[x]) return value[x];

        int best = Integer.MAX_VALUE;
        for (int c : coins) {
            if (x - c >= 0) {
                path[x] = c;
                int solve = solve(coins, x - c, path, ready, value);
                best = Math.min(best, solve == Integer.MAX_VALUE ? solve : solve + 1);
            }
        }
        ready[x] = true;
        value[x] = best;
        return best;// < 0 ? Integer.MAX_VALUE : best;
    }


    private static void solve(int[] coins, int n, List<List<Integer>> allSelectedCoins, List<Integer> selectedCoins) {
        if (n == 0) {
            //Add selected coins
            allSelectedCoins.add(List.copyOf(selectedCoins));
        }
        if (n < 0) {
            return;
        }

        for (int coin : coins) {
            if (n - coin >= 0) {
                selectedCoins.add(coin);
                solve(coins, n - coin, allSelectedCoins, selectedCoins);
                selectedCoins.remove(selectedCoins.size() - 1);
            }
        }
    }
}
