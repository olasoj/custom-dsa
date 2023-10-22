package com.dsa.question.leetcode.hard;

import java.util.OptionalInt;

public class FindMedianSortedArrays {

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double medianSortedArrays = findMedianSortedArrays.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(medianSortedArrays);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int nums1L = nums1.length;
        int nums2L = nums2.length;

        //Check if either num1 or num2 is empty
        if (nums1L == 0 && nums2L == 0) {
            return 0;
        } else if (nums1L == 0) {
            return getMedian(nums2);
        } else if (nums2L == 0) {
            return getMedian(nums1);
        }

        int totalSize = nums1L + nums2L;

        //Check overflow
        int[] mergedNums = new int[totalSize];
        int medianPoint = totalSize / 2;

        int num1Counter = 0;
        int num2Counter = 0;
        int totalCounter = 0;

        while (totalCounter < totalSize) {
            OptionalInt val1 = (num1Counter < nums1L) ? OptionalInt.of(nums1[num1Counter]) : OptionalInt.empty();
            OptionalInt val2 = (num2Counter < nums2L) ? OptionalInt.of(nums2[num2Counter]) : OptionalInt.empty();

            if (totalCounter >= medianPoint + 1) {
                if (totalSize % 2 > 0) {
                    return mergedNums[medianPoint];
                }
                return (mergedNums[medianPoint - 1] + mergedNums[medianPoint]) / 2.0;
            }

            if ((((val1.isPresent()) && val2.isPresent()) && val1.getAsInt() < val2.getAsInt()) || ((val1.isPresent()) && val2.isEmpty())) {
                mergedNums[totalCounter] = val1.getAsInt();
                num1Counter++;
                totalCounter++;
            } else if (val1.isPresent() && val2.getAsInt() < val1.getAsInt() || val1.isEmpty() && val2.isPresent()) {
                mergedNums[totalCounter] = val2.getAsInt();
                num2Counter++;
                totalCounter++;
            } else {
                if (val1.isPresent()) {
                    mergedNums[totalCounter] = val2.getAsInt();
                    num2Counter++;
                    totalCounter++;

                    mergedNums[totalCounter] = val1.getAsInt();
                    num1Counter++;
                    totalCounter++;
                }
            }
        }

        return getMedian(mergedNums);
    }

    private double getMedian(int[] mergedNums) {
        int size = mergedNums.length;
        int medianPoint = size / 2;

        if (size == 1) return mergedNums[0];
        if (size % 2 > 0) {
            return mergedNums[medianPoint];
        }
        return (mergedNums[medianPoint - 1] + mergedNums[medianPoint]) / 2.0;
    }
}
