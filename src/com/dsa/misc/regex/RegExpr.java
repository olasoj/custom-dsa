package com.dsa.misc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class RegExpr {

    public static void main(String[] args) {
        Pattern pat;
        Matcher mat;
        boolean found;

        pat = Pattern.compile("Java");
        mat = pat.matcher("Java");
        boolean matches = "Java".matches("J.*");
        out.println(matches);
        found = mat.matches(); // check for a match
        out.println("Testing Java against Java.");

        isMatch(found);
        out.println();

        out.println("Testing Java against Java SE 6.");
        mat = pat.matcher("Java SE 6"); // create a new matcher
        found = mat.matches(); // check for a match
        isMatch(found);
    }

    static void isMatch(boolean found) {
        if (found) out.println("Matches");
        else out.println("No Match");
    }
}
