package com.dsa.question.leetcode.easy;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        StringBuilder commonPrefix = new StringBuilder();

        String initialString = strs[0];
        if (strs.length <= 1) return initialString;

        int counter = 0;
        int iteration = 0;


        while (counter < initialString.length()) {

            for (String str : strs) {

                if (str.equals(initialString) && iteration == 0) {
                    counter++;
                    if (counter > initialString.length()) return commonPrefix.toString();
                    String chars = str.substring(0, counter);
                    commonPrefix = commonPrefix.replace(0, counter, chars);
                }

                if (!str.startsWith(commonPrefix.toString())) {
                    commonPrefix.deleteCharAt(commonPrefix.length() - 1);
                    return commonPrefix.toString();
                }

                if (iteration == strs.length - 1) {
                    iteration = 0;
                    continue;
                }

                iteration++;

            }
        }
        return commonPrefix.toString();
    }

}
