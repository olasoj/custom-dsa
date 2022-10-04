package com.dsa.question.hackerank;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreePalindromicSubstrings {

    public static List<String> threePalindromicSubstrings(String word) {
        int wordLength = word.length();
        int[] dp = new int[wordLength];

        boolean[][] isPalindrome = new boolean[wordLength][wordLength];

        for (int i = 0; i < wordLength; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (word.charAt(j) == word.charAt(i) && (i - j < 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                    min = Math.min(min, j == 0 ? 0 : dp[j - 1] + 1);
                }

            }
            dp[i] = min;

        }
        List<Integer> last = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            if (isPalindrome[i][wordLength - 1])
                last.add(i);
        }

        List<String> threePalindromicSubstrings = new LinkedList<>();

        for (int l : last) {
            for (int i = 0; i < l; i++) {
                if (isPalindrome[0][i] && isPalindrome[i + 1][l - 1]) {
                    threePalindromicSubstrings.add(word.substring(0, i + 1));
                    threePalindromicSubstrings.add(word.substring(i + 1, l));
                    threePalindromicSubstrings.add(word.substring(l));
                    return threePalindromicSubstrings;
                }
            }
        }

        threePalindromicSubstrings.add("Impossible");
        return threePalindromicSubstrings;
    }

    public static void main(String[] args) {

        Instant startTime = Instant.now();

        String random = RandomStringUtils.random(59000);
//        List<String> threePalindromicSubstrings = threePalindromicSubstrings("madamciviclevel");
        List<String> threePalindromicSubstrings2 = threePalindromicSubstrings(random);
//        System.out.println(threePalindromicSubstrings);
        System.out.println(threePalindromicSubstrings2);

        Instant endTime = Instant.now();
        Duration timeElapsed = Duration.between(startTime, endTime);
        System.out.println("Operation took: " + timeElapsed.toMillis() + " milliseconds");
    }
}
