package com.dsa.misc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Working with Classes of Characters
public class RegExpr7 {

    public static void main(String[] args) {
        usingQuantifiers();
    }

    // Match lowercase words.
    static void usingQuantifiers() {

        // Match lowercase words.
        Pattern pat = Pattern.compile("[a-z]+");
        Matcher mat = pat.matcher("this is a test.");
        while (mat.find())
            System.out.println("Match: " + mat.group());
    }


}
