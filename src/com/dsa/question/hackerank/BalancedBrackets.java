package com.dsa.question.hackerank;

import java.util.Stack;

import static java.lang.System.out;

public class BalancedBrackets {

    public static void main(String[] args) {
        out.println(isBalanced("{[()]}"));
        out.println(isBalanced("{[(])}"));
        out.println(isBalanced("{{[[(())]]}}"));
    }

    public static String isBalanced(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();

        int i = 0;
        String no = "NO";
        String yes = "YES";

        while (i < s.length()) {
            char c = s.charAt(i);
            if (isBracketOpen(s.charAt(i))) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return no;
                Character openBracket = stack.pop();
                if (openBracket == '{' && c != '}' || (openBracket == '[' && c != ']') || (openBracket == '(' && c != ')')) return no;
            }

            i++;
        }

        return stack.isEmpty() ? yes : no;
    }

    private static boolean isBracketOpen(char c) {
        return c == '{' || c == '[' || c == '(';
    }
}
