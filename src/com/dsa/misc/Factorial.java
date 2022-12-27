package com.dsa.misc;

public class Factorial {

    // this is a recursive method
    //  fact(n - 1) * n
    int fact(int n) {
        return (n <= 1) ? 1 : fact(n - 1) * n;
    }

    // this is a Corecursion  method
    //  fact(n - 1) * n
    int factCorecursion(int n) {
        int temp = n;

        if (n < 1) return n;
        while (temp > 1) {
            temp--;
            n = n * temp;
        }
        return n;
    }
}

class Recursion {
    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println("Factorial of -1 is " + f.fact(-1));
        System.out.println("Factorial of -1 is " + f.factCorecursion(-1));
        System.out.println("Factorial of 3 is " + f.fact(3));
        System.out.println("Factorial of 4 is " + f.fact(4));
        System.out.println("Factorial of 5 is " + f.fact(5));
        System.out.println("Factorial of 5 is " + f.factCorecursion(5));
    }
}
