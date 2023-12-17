package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class FruitIntoBaskets {

    public static void main(String[] args) {
        FruitIntoBaskets fruitIntoBaskets = new FruitIntoBaskets();

        int i = fruitIntoBaskets.totalFruit(new int[]{0, 0, 1, 1});
        out.println(i);
    }

    public int totalFruit(int[] fruits) {

        if (fruits.length < 2) return fruits.length;

        //Check arr size
        List<Integer> picked = new ArrayList<>(2);
        int max = 0;
        int result = 0;

        for (int i = 0; i < fruits.length; i++) {
            boolean contains = picked.contains(fruits[i]);
            if ((picked.isEmpty()) || picked.size() == 1 && !contains) {
                picked.add(fruits[i]);
                max++;
            } else if (picked.contains(fruits[i])) {
                max++;
            }


            if (i + 1 < fruits.length && !picked.contains(fruits[i + 1]) && picked.size() == 2) {
                result = Math.max(result, max);
                picked.clear();
                max = 0;

                picked.add(fruits[i]);
                int f = fruits[i];
                int j = i;
                while (j >= 0 && fruits[--j] == f) {
                    max++;
                }

                max++;
            }

        }

        return Math.max(result, max);
    }
}
