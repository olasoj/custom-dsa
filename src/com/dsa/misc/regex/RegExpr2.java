package com.dsa.misc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;


public class RegExpr2 {

    public static void main(String[] args) {
        findEX();
        findEX2();
    }

    // Use find() to find a subsequence.
    private static void findEX() {
        Pattern pat = Pattern.compile("Java");
        Matcher mat = pat.matcher("Java SE 6");
        out.println("Looking for Java in Java SE 6.");

        if (mat.find()) out.println("subsequence found");
        else out.println("No Match");
    }

    // Use find() to find multiple subsequences.
    private static void findEX2() {
        Pattern pat = Pattern.compile("test");
        Matcher mat = pat.matcher("test 1 2 3 test");

        while (mat.find()) {
            out.println("test found at index " + mat.start());
        }
    }
}
