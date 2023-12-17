package com.dsa.question.leetcode.medium;

import java.util.*;

import static java.lang.System.out;

public class LetterCombinationsOfAPhoneNumber {

    static Map<Character, Set<Character>> dC = new HashMap<>(26);

    static {
        dC.put('2', Set.of('a', 'b', 'c'));
        dC.put('3', Set.of('d', 'e', 'f'));
        dC.put('4', Set.of('g', 'h', 'i'));
        dC.put('5', Set.of('j', 'k', 'l'));
        dC.put('6', Set.of('m', 'n', 'o'));
        dC.put('7', Set.of('p', 'q', 'r', 's'));
        dC.put('8', Set.of('t', 'u', 'v'));
        dC.put('9', Set.of('w', 'x', 'y', 'z'));
    }

    public static void main(String[] args) {
        out.println(algorithm("23"));
        out.println(algorithm(""));
        out.println(algorithm("2"));
        out.println(algorithm("5678"));
    }

    public static List<String> algorithm(String digits) {

        List<String> cb = new ArrayList<>();
        if (digits.length() == 0) return cb;

        StringBuilder sb = new StringBuilder(digits.length());
        solve(digits, 0, sb, cb);
        return cb;
    }

    private static void solve(String digits, int currentIndex, StringBuilder sb, List<String> cb) {
        if (digits.length() == sb.length()) {
            cb.add(sb.toString());
        } else {
            char c = digits.charAt(currentIndex);
            Set<Character> characters = dC.get(c);

            for (Character cP : characters) {
                if (sb.length() <= digits.length()) {
                    sb.append(cP);
                    solve(digits, currentIndex + 1, sb, cb);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}
