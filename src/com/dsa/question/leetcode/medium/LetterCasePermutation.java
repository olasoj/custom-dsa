package com.dsa.question.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class LetterCasePermutation {

    public static void main(String[] args) {

        String input = "a1b2";
        String input2 = "3z4";
        algorithm(input);
        algorithm(input2);
    }

    private static void algorithm(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        List<String> permutations = new ArrayList<>();

        solve(input, 0, permutations, sb);

        out.println(permutations);
    }

    private static void solve(String input, int currentIndex, List<String> permutations, StringBuilder stringBuilder) {

        if (input.length() == currentIndex) {
            permutations.add(stringBuilder.toString());
        } else {
            char c = input.charAt(currentIndex);

            if (Character.isLetter(c)) {

                stringBuilder.append(c);
                solve(input, currentIndex + 1, permutations, stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);

                char c1 = (Character.isUpperCase(c)) ? Character.toLowerCase(c) : Character.toUpperCase(c);

                stringBuilder.append(c1);
                solve(input, currentIndex + 1, permutations, stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);


            } else {
                stringBuilder.append(c);
                solve(input, currentIndex + 1, permutations, stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }

    }


}
