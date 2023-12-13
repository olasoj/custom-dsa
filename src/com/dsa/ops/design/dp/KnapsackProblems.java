package com.dsa.ops.design.dp;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class KnapsackProblems {

    public static void main(String[] args) {
        int knapsackSize = 10;
        int[] itemWeights = new int[]{1, 3, 3, 5};
        boolean[] chosen = new boolean[itemWeights.length];

        algorithm(itemWeights, knapsackSize, chosen);
    }

    private static void algorithm(int[] itemWeights, int knapsackSize, boolean[] chosen) {

        List<Integer> selectedItems = new ArrayList<>();
        List<List<Integer>> allSelectedItems = new ArrayList<>();
        //Don,t take item
        solve(itemWeights, knapsackSize, chosen, selectedItems, allSelectedItems);
        out.println(allSelectedItems);
        //Take item
    }

    private static void solve(int[] itemWeights, int knapsackSize, boolean[] chosen, List<Integer> selectedItems, List<List<Integer>> allSelectedItems) {

        allSelectedItems.add(List.copyOf(selectedItems));
        for (int i = 0; i < itemWeights.length; i++) {
            if (chosen[i]) continue;
            if (knapsackSize - itemWeights[i] >= 0) {
                chosen[i] = true;
                selectedItems.add(itemWeights[i]);
                solve(itemWeights, knapsackSize - itemWeights[i], chosen, selectedItems, allSelectedItems);
                chosen[i] = false;
                selectedItems.remove(selectedItems.size() - 1);
            }
        }

    }


}
