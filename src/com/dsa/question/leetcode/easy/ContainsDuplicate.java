package com.dsa.question.leetcode.easy;

import com.dsa.util.PerformanceUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;

import static java.lang.System.out;

public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 1};
        int[] arr2 = new int[]{1, 2, 3, 4};
        int[] arr3 = new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        int[] arr4 = new int[]{1000000000, 1000000000, 11};
        int[] arr5 = new int[]{1, 5, -2, -4, 0};

        out.println(PerformanceUtil.measureOperationDuration(() -> algorithm(arr)));
        out.println(PerformanceUtil.measureOperationDuration(() -> algorithm2(arr)));

        out.println(algorithm2(arr2));
        out.println(algorithm2(arr3));
        out.println(algorithm2(arr4));
        out.println(algorithm2(arr5));
    }

    private static boolean algorithm2(int[] arr) {
        Set<Integer> arrSet = new HashSet<>();

        for (int leftElement : arr) {
            if (arrSet.contains(leftElement)) return true;
            arrSet.add(leftElement);
        }
        return false;
    }

    private static int algorithm3(int[] arr) {
        boolean[] seen = new boolean[arr.length + 1];

        for (int a : arr) {
            if (seen[a]) return a;
            seen[a] = true;
        }

        return -1;
    }

    private static boolean algorithm(int[] arr) {
        int length = arr.length;
        Set<Integer> arrSet = new HashSet<>(length);

        boolean isEven = length % 2 == 0;
        IntPredicate condition = isEven ? i -> i < length / 2 : i -> i <= length / 2;

        IntPredicate isMid = (i) -> !isEven && i == Math.floor((double) length / 2);

        for (int i = 0; condition.test(i); i++) {

            if (isMid.test(i)) {
                int leftElement = arr[i];
                if (arrSet.contains(leftElement)) return true;
            } else {
                int leftElement = arr[i];
                int rightElement = arr[(length - 1) - i];

                if (leftElement == rightElement) return true;
                if (arrSet.contains(leftElement) || arrSet.contains(rightElement)) return true;

                arrSet.add(leftElement);
                arrSet.add(rightElement);
            }

        }
        return false;
    }
}
