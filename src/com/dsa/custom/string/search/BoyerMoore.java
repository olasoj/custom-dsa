package com.dsa.custom.string.search;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class BoyerMoore {

    public static void main(String[] args) {
        out.println(findBoyerMoore("String".toCharArray(), "ring".toCharArray()));
        out.println(findBoyerMoore("String".toCharArray(), "ding".toCharArray()));
    }

    public static int findBoyerMoore(char[] text, char[] pattern) {

        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0;

        Map<Character, Integer> last = new HashMap<>(); // the 'last' map

        for (char c : text) last.put(c, -1); // set −1 as default for all text characters

        for (int k = 0; k < m; k++)
            last.put(pattern[k], k); // rightmost occurrence in pattern is last

        // start with the end of the pattern aligned at index m−1 of the text
        int i = m - 1;  // an index into the text
        int k = m - 1; // an index into the pattern

        while (i < n) {
            if (text[i] == pattern[k]) { // a matching character
                if (k == 0) return i;  // entire pattern has been found
                i--;  // otherwise, examine previous
                k--;  // characters of text/pattern
            } else {
                i = i + (m - Math.min(k, 1 + last.get(text[i])));
                k = m - 1;
            }
        }

        return -1;
    }
}
