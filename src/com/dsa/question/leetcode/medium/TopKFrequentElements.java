package com.dsa.question.leetcode.medium;

import java.util.*;

import static java.lang.System.out;

public class TopKFrequentElements {

    public static void main(String[] args) {
        out.println(Arrays.toString(topKFrequent(new int[]{
                1, 1, 1, 2, 2, 3
        }, 2)));
    }


    public static int[] topKFrequent(int[] nums, int k) {

        if (k == 1) return nums;

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>(nums.length);

        for (int fc : nums) {
            if (map.get(fc) == null) {
                map.put(fc, 1);
            } else {
                int cfc = map.get(fc);
                map.put(fc, cfc + 1);

                if (cfc == k) list.add(fc);
            }
        }


        int[] result = new int[list.size()];

        int counter = 0;
        for (int i : list)
            result[counter++] = i;

        return result;

    }

}