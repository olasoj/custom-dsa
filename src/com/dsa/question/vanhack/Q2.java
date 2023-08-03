package com.dsa.question.vanhack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Q2 {

    public static void main(String[] args) {

        String test = "Gaius Julius Cæsar (12 or 13 July 100 BC - 15 March 44 BC),";
        String test2 = "Up ↑ is the direction";

        String badCharacter = isBadCharacter((test2))
                .map(c -> Character.toString(c))
                .orElse("");

        List<String> lines = new ArrayList<>();

        for (String line : lines) {
            isBadCharacter((test2))
                    .map(c -> Character.toString(c))
                    .orElse("");
        }

        System.out.println(badCharacter);
    }

    private static Optional<Character> isBadCharacter(String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            boolean unicodeIdentifierPart = isBadCharacter(c);
            if (unicodeIdentifierPart) return Optional.of(c);
        }
        return Optional.empty();
    }

    private static boolean isBadCharacter(char c) {
        return c > 128;
    }


}
