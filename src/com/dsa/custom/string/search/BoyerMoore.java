package com.dsa.custom.string.search;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class BoyerMoore {

    public static void main(String[] args) {
        out.println(findBoyerMoore("Stringring", "ringr"));
        out.println(findBoyerMoore("StringAling", "ding"));
    }

    public static int findBoyerMoore(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();
        if (m == 0) return 0;

        Map<Character, Integer> last = new HashMap<>(); // the 'last' map

        for (int i = 0; i < n; i++)
            last.put(text.charAt(i), -1); // set −1 as default for all text characters

        for (int k = 0; k < m; k++)
            last.put(pattern.charAt(k), k); // rightmost occurrence in pattern is last

        // start with the end of the pattern aligned at index m−1 of the text
        int i = m - 1;  // an index into the text
        int k = m - 1; // an index into the pattern

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(k)) { // a matching character
                if (k == 0) return i;  // entire pattern has been found
                i--;  // otherwise, examine previous
                k--;  // characters of text/pattern
            } else {
                i = i + (m - Math.min(k, 1 + last.get(text.charAt(i))));
                k = m - 1;
            }
        }

        return -1;
    }
}
