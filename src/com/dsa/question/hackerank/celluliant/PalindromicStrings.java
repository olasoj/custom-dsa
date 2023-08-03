package com.dsa.question.hackerank.celluliant;

import com.dsa.question.PalindromeUtil;

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
            boolean notPan = PalindromeUtil.isNotPalindrome(chars);

            if (notPan) continue;
            stringsThatArePan.add(str);
        }

        return stringsThatArePan;

    }

}
