package com.dsa.question;

import com.dsa.misc.Factorial;

public class QQ {

    public static void main(String[] args) {
        int i = bracketCombinations(3);
        int j = bracketCombinations(5);

        System.out.println(i);
        System.out.println(j);
    }

    private static int bracketCombinations(int num) {
        int n = num * 2;
        int numberFactorial = Factorial.simpleFact(n);
        int rFactorial = Factorial.simpleFact(num + 1);
        int nrFactorial = Factorial.simpleFact((num));
        return numberFactorial / (nrFactorial * rFactorial);
    }

//    Have the function BracketCombinations(num) read num which will be an integer greater than or equal to zero, and return the number of valid combinations
//    that can be formed with num pairs of parentheses. For example, if the input is 3, then the possible combinations of 3 pairs of parenthesis,
//    namely: ()()(), are ()()(), ()(()), (())(), ((())), and (()()). There are 5 total combinations when the input is 3, so your program should return 5.
}
