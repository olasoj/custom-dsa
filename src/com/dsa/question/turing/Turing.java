package com.dsa.question.turing;

import static java.lang.System.out;

public class Turing {

    public static void main(String[] args) {
        out.println(solution(49));
    }

    public static int solution(int n) {

        int a = 1;
        int b = n;

        int sum = a;
        int sum2 = b;

        while (b >= a) {
            if (sum == sum2 && b == a) return b;
            if (sum < sum2) sum = sum + ++a;
            else sum2 = sum2 + --b;
        }

        return -1;
    }
}
