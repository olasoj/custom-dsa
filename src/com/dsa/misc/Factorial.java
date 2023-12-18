package com.dsa.misc;

import static java.lang.System.out;

public class Factorial {

    private Factorial() {
    }

    public static int simpleFact(int n) {
        return (n <= 1) ? 1 : simpleFact(n - 1) * n;
    }

    // this is a recursive method
    //  fact(n - 1) * n
    public static int fact(int n) {
        return fact(n, 1);
    }

    public static int fact(int n, int accumulator) {
        if (n <= 1) return 1;
        return fact(n - 1, accumulator * n);
    }

    // this is a Core-cur-sion  method
    //  fact(n - 1) * n
    public static int factIterative(int n) {
        int temp = n;

        while (temp-- > 1) {
            n = n * temp;
        }
        return n;
    }
}

class Recursion {
    public static void main(String[] args) {
        out.println("Factorial of -1 is " + Factorial.fact(-1));
        out.println("Factorial of -1 is " + Factorial.factIterative(-1));
        out.println("Factorial of 3 is " + Factorial.fact(3));
        out.println("Factorial of 4 is " + Factorial.fact(4));
        out.println("Factorial of 5 is " + Factorial.fact(5));
        out.println("Factorial of 5 is " + Factorial.factIterative(5));
    }
}
