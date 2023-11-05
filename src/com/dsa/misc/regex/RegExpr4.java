package com.dsa.misc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Using Wildcards and Quantifiers
public class RegExpr4 {

    public static void main(String[] args) {
        usingQuantifiers();
        usingQuantifiersAndWildcard();
        usingQuantifiersAndWildcard2();
    }

    static void usingQuantifiers() {

        Pattern pat = Pattern.compile("W+");
        Matcher mat = pat.matcher("W WW WWW");
        while (mat.find())
            System.out.println("Match: " + mat.group());
    }

    static void usingQuantifiersAndWildcard() {

        Pattern pat = Pattern.compile("e.+d");
        Matcher mat = pat.matcher("extend cup end table");
        while (mat.find())
            System.out.println("Match: " + mat.group());
    }

    static void usingQuantifiersAndWildcard2() {

        // Use reluctant matching behavior.
        Pattern pat = Pattern.compile("e.+?d");
        Matcher mat = pat.matcher("extend cup end table");
        while (mat.find())
            System.out.println("Match: " + mat.group());

    }

}
