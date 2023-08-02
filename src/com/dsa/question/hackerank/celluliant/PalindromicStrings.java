package com.dsa.question.hackerank.celluliant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PalindromicStrings {

    public static void main(String[] args) {
        //ADA
        List<String> pal = palindromic(List.of("civic", "mom", "madam", "mad", "an", "anna", "Deified"));
        System.out.println(pal);
    }

    public static List<String> palindromic(List<String> input) {

        if (input == null) return Collections.emptyList();
        if (input.size() == 1) return input;

        List<String> stringsThatArePan = new ArrayList<>();

        for (String str : input) {

            //Civic
            char[] chars = str.toCharArray();
            boolean notPan = false;

            for (int i = 0; i < str.length() / 2; i++) {
                if (chars[i] != chars[(chars.length) - (i + 1)]) {
                    notPan = true;
                    break;
                }
            }

            if (notPan) continue;
            stringsThatArePan.add(str);
        }

        return stringsThatArePan;

    }
}
