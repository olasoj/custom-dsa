package com.dsa.question.leetcode.easy;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String s = ((Integer) x).toString();
        String s1 = new StringBuilder(s).reverse().toString();
        return s.equals(s1);
    }
}
