package com.dsa.question.leetcode.hard;

import java.util.Arrays;

public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses validParentheses = new LongestValidParentheses();
        int valid = validParentheses.longestValidParentheses(")()())((()))");
        System.out.println(valid);
    }

    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int[] V = new int[chars.length];

        int open = 0;
        int max = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') open++;
            if (chars[i] == ')' && open > 0) {
                // matches found
                V[i] = 2 + V[i - 1];
                // add matches from previous
                if (i - V[i] > 0)
                    V[i] += V[i - V[i]];
                open--;
            }
            if (V[i] > max) max = V[i];
        }

        System.out.println(Arrays.toString(V));
        return max;
    }
}

