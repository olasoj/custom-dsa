package com.dsa.misc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpr8 {

    public static void main(String[] args) {
        searchAndReplace();
        usingSplit();
    }

    private static void usingSplit() {
        // Match lowercase words.
        Pattern pat = Pattern.compile("[ ,.!]");
        String[] strs = pat.split("one two,alpha9 12!done.");
        for (int i = 0; i < strs.length; i++)
            System.out.println("Next token: " + strs[i]);

    }

    private static void searchAndReplace() {
        String str = "Jon Jonathan Frank Ken Todd";

        Pattern pat = Pattern.compile("Jon.*? ");
        Matcher mat = pat.matcher(str);

        System.out.println("Original sequence: " + str);
        str = mat.replaceAll("Eric ");
        System.out.println("Modified sequence: " + str);
    }

    //Using split( )
}
