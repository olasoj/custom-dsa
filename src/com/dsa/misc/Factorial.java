package com.dsa.misc;

public class Factorial {

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

    // this is a Core-cursion  method
    //  fact(n - 1) * n
    int factCorecursion(int n) {
        int temp = n;

        while (temp-- > 1) {
            n = n * temp;
        }
        return n;
    }
}

class Recursion {
    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println("Factorial of -1 is " + Factorial.fact(-1));
        System.out.println("Factorial of -1 is " + f.factCorecursion(-1));
        System.out.println("Factorial of 3 is " + Factorial.fact(3));
        System.out.println("Factorial of 4 is " + Factorial.fact(4));
        System.out.println("Factorial of 5 is " + Factorial.fact(5));
        System.out.println("Factorial of 5 is " + f.factCorecursion(5));
    }
}
