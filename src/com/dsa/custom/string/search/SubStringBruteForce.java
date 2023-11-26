package com.dsa.custom.string.search;

import static java.lang.System.out;

public class SubStringBruteForce {

    public static void main(String[] args) {
        out.println(findBrute("String".toCharArray(), "ring".toCharArray()));
        out.println(findBrute("String".toCharArray(), "ding".toCharArray()));
    }

    public static int findBrute(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        for (int i = 0; i <= (n - m); i++) { // try every starting index within text
            int k = 0; // k is index into pattern
            while (k < m && text[i + k] == pattern[k]) // kth character of pattern matches
                k++;
            if (k == m) return i; // if we reach the end of the pattern, substring text[i..i+m-1] is a match
        }
        return -1;
    }
}
