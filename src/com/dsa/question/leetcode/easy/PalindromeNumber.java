package com.dsa.question.leetcode.easy;

import com.dsa.question.PalindromeUtil;
import com.dsa.util.CharUtil;

import static java.lang.System.out;

public class PalindromeNumber {

    public static void main(String[] args) {

        out.println(isPalindrome(3113));
        out.println(isPalindrome2(3113));
        out.println(isPalindrome3(3113));
        out.println(isPalindrome5(3113));

    }

    //Leading zeros wont problem
    public static boolean isPalindrome4(int x) {
        int numberOfDigits = (int) (Math.log10(x) + 1);

        for (int i = 0; i < numberOfDigits / 2; i++) {
            int firstDigit = (x % 10);
            int lastDigit = (x % 10);

            if (firstDigit != lastDigit) return false;

            //Truncate first digit
            //Truncate last digit
            x /= 10;
        }
        return true;
    }

    public static boolean isPalindrome3(int x) {
        //Hold the original number.
        int originalNumber = x;
        //Variable used to store the number as it is being reversed
        int reverseNumberStore = 0;

        //Reverse the number O(n)
        while (x > 0) {
            //Get the last element of the number e.g. if number 131 outputs 1
            int lastDigit = x % 10;
            //Add the number
            reverseNumberStore = (reverseNumberStore * 10) + lastDigit;
            //reduce the e.g. if number 131 to 13
            x = x / 10;
        }

        //compare original number
        return originalNumber == reverseNumberStore;
    }

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        String s1 = new StringBuilder(s).reverse().toString();
        return s.equals(s1);
    }

    public static boolean isPalindrome5(int x) {
        String s = String.valueOf(x);

        int length = s.length();
        for (int i = 0; i < (length / 2); i++) {
            if (s.charAt(i) != s.charAt(length - (i + 1))) return false;
        }
        return true;
    }


    public static boolean isPalindrome2(int x) {

        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        } else {
            //Convert to char array O(n)
            char[] chars = CharUtil.toCharArray(x);
            //Compare first and last characters O(n/2)
            return !PalindromeUtil.isNotPalindrome(chars);
        }
    }

}

