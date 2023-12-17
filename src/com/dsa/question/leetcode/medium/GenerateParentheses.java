package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class GenerateParentheses {

    public static void main(String[] args) {
        out.println(algorithm(1));
        out.println(algorithm(3));
    }

    private static List<String> algorithm(int n) {
        List<String> cb = new ArrayList<>(n * 2);
        StringBuilder sb = new StringBuilder(n * 2);
        sb.append('(');
        solve(n, 1, 0, sb, cb);
        return cb;
    }

    public static void solve(int n, int numberOfOpen, int numberOfClosed, StringBuilder sb, List<String> cb) {
        if (sb.length() == (n * 2)) {
            cb.add(sb.toString());
        } else {
            if (numberOfOpen < n) {
                sb.append('(');
                solve(n, numberOfOpen + 1, numberOfClosed, sb, cb);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (numberOfClosed < n && numberOfClosed < numberOfOpen) {
                sb.append(')');
                solve(n, numberOfOpen, numberOfClosed + 1, sb, cb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
