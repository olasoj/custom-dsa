package com.dsa.question;

public class PalindromeUtil {

    private PalindromeUtil() {
    }

    public static boolean isNotPalindrome(char[] chars) {

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[(chars.length) - (i + 1)]) {
                return true;
            }
        }
        return false;
    }
}
