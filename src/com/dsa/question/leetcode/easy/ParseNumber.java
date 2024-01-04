package com.dsa.question.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class ParseNumber {

    public static void main(String[] args) {
        List<String> input = List.of(
                "abdcg43asdhkjahas56757.6987",
                "asdn,mnh435-564asdlkj==-99cc",
                "asdasdasd", "33", ""
        );

        input.forEach(x -> out.println(parseNumber(x)));
    }


    public static List<Double> parseNumber(String str) {

        int strL = str.length();
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) { //O(n) //log(n) && O(1)

            int j = i;
            while (j < strL && (Character.isDigit(str.charAt(j)) || str.charAt(j) == '.')) {
                j++;
            }

            if (j - i > 0 || Character.isDigit(str.charAt(i))) {
                Double val = Double.parseDouble(str.substring(i, j));
                i = j;
                result.add(val);
            }

        }

        return result;
    }
}
