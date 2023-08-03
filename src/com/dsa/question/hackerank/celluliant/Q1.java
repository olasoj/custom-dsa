package com.dsa.question.hackerank.celluliant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Q1 {

    public static void main(String[] args) {

        String msg = "afeb";

        List<Integer> securityValues = Arrays.asList(1, 2, 3, 4, 4, 5);

        char[] chars = msg.toCharArray();
        List<Integer> ints = new ArrayList<>();

        for (char c : chars) {
            int positionInAlphabet = findPositionInAlphabet(c);

            if (positionInAlphabet > securityValues.size() - 1) continue;
            Integer integer = securityValues.get(positionInAlphabet);
            ints.add(integer);
        }

        ints.sort(Comparator.naturalOrder());

        int computationValue = 0;

        for (int i = 0; i < ints.size(); i++) {
            if (i == ints.size() - 1) break;

            computationValue = computationValue + Math.abs(ints.get(i) - ints.get(i + 1));
        }

        System.out.println(computationValue);
    }

    public static int findPositionInAlphabet(char c) {
        int tempInteger = 96; //for lower case
        if (c <= 122 && c >= 97)
            return (c - tempInteger);

        return 0;
    }

    public static void ttt() {
        String str = "abcdef";
        char[] ch = str.toCharArray();
        for (char c : ch) {
            toInt(c);
        }
    }

    private static void toInt(int c) {
        int temp = c;
        int temp_integer = 96; //for lower case
        if (temp <= 122 && temp >= 97)
            System.out.print(temp - temp_integer);
    }

    public static void fizzBuzz(int n) {

        for (int i = 1; i <= n; i++) {

            boolean isIAMultiplyOfThree = i % 3 == 0;
            boolean isIAMultiplyOfFive = i % 5 == 0;

            if (isIAMultiplyOfThree && isIAMultiplyOfFive)
                System.out.println("FizzBuzz");

            if (isIAMultiplyOfThree && !isIAMultiplyOfFive)
                System.out.println("Fizz");

            if (!isIAMultiplyOfThree && isIAMultiplyOfFive)
                System.out.println("Buzz");

            if (!isIAMultiplyOfThree && !isIAMultiplyOfFive)
                System.out.println(i);
        }
    }
}


//    convert(decimal(10, 2), parse(T.amount as money using 'en-US'))

