package com.dsa.misc;

public class Factorial {

    // this is a recursive method
    //  fact(n - 1) * n
    int fact(int n) {
        return (n <= 1) ? 1 : fact(n - 1) * n;
    }
}

class Recursion {
    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println("Factorial of -1 is " + f.fact(-1));
        System.out.println("Factorial of 3 is " + f.fact(3));
        System.out.println("Factorial of 4 is " + f.fact(4));
        System.out.println("Factorial of 5 is " + f.fact(5));
    }
}
