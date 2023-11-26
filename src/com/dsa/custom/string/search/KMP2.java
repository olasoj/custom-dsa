package com.dsa.custom.string.search;

public class KMP2 {

    public static int findKMP(char[] text, char[] pattern) {

        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0; // trivial search for empty string

        int[] fail = computeFailKMP(pattern); // computed by private utility

        int j = 0;  // index into text
        int k = 0; // index into pattern

        while (j < n) {
            if (text[j] == pattern[k]) {  // pattern[0..k] matched thus far
                if (k == m - 1) return j - m + 1;  // match is complete

                // otherwise, try to extend match
                j++;
                k++;
            } else if (k > 0) k = fail[k - 1]; // reuse suffix of P[0..k-1]
            else j++;
        }

        return -1;
    }

    private static int[] computeFailKMP(char[] pattern) {
        int m = pattern.length;
        int[] fail = new int[m]; // by default, all overlaps are zero

        int j = 1;
        int k = 0;
        while (j < m) { // compute fail[j] during this pass, if nonzero
            if (pattern[j] == pattern[k]) { // k + 1 characters match thus far
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) k = fail[k - 1]; // k follows a matching prefix
            else j++; // no match found starting at j
        }
        return fail;
    }
}
