package com.dsa.question.leetcode.medium;

import static java.lang.System.out;

public class MaximumNumberOfVowelsInASubstringOfGivenLengthMaximumNumberOfVowelsInASubstringOfGivenLength {

    public static void main(String[] args) {
        out.println(maxVowels("abciiidef", 3));
    }

    private static boolean isVowel(char c) {
        return c == 'a'
                || c == 'e'
                || c == 'i'
                || c == 'o'
                || c == 'u';
    }

    public static int maxVowels(String s, int k) {

        int right = 0;
        int max = 0;

        while (right < k) {
            if (isVowel(s.charAt(right++))) max++;
        }

        int left = 0;
        int result = max;

        int sLength = s.length();
        while (right < sLength) {
            if (isVowel(s.charAt(left++))) max--;
            if (isVowel(s.charAt(right++))) max++;
            result = Math.max(result, max);
        }

        return result;
    }
}
