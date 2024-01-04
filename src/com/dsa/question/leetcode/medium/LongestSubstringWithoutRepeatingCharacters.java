package com.dsa.question.leetcode.medium;

import com.dsa.util.PerformanceUtil;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        out.println(PerformanceUtil.measureOperationDuration(() -> lengthOfLongestSubstring("abcabcbb")));
    }

    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int max = 0;

        while (right < s.length()) {
            if (map.get(s.charAt(right)) == null) {
                map.put(s.charAt(right), right);
            } else {
                Integer m = map.get(s.charAt(right));
                max = Math.max(max, right - left);

                map.put(s.charAt(right), right);
                left = Math.max(left, m + 1);
            }

            right++;
        }


        return Math.max(max, right - left);
    }
}
