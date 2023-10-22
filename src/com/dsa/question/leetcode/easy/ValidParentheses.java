package com.dsa.question.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        boolean valid = validParentheses.isValid("()[]{}");
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }

        System.out.println(stack);
        return stack.isEmpty();
    }
}
