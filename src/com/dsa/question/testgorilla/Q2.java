package com.dsa.question.testgorilla;

public class Q2 {

    public static void main(String[] args) {

        String complicatedPassword = "complicatedPassword";
        String complicatedPassword2 = "Pass#1%!";
        String label = "123@PaSS";
        Boolean validPassword = isValidPassword(complicatedPassword2);
        System.out.println(validPassword);
    }

    public static Boolean lucky_88(Integer a, Integer b) {
        //Insert your code here
        final Integer ss = 88;
        if (ss.equals(a) || ss.equals(b)) {
            return true;
        }
        if (ss.equals(a + b)) return true;
        return ss.equals(a - b);
    }

    public static Boolean isValidPassword(String password) {
        if (password.length() < 8)
            return false;
        for (char ch : password.toCharArray()) {
            if ((ch == ' ') || (ch == '.') || (ch == ',')) return false;
        }
        boolean hasUppercases = false;
        boolean hasLowercases = false;
        boolean hasDigits = false;
        int specialChars = 0;
        for (int i = 0, n = password.length(); i < n; i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasDigits = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercases = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercases = true;
            } else {
                specialChars++;
            }
        }

        if (specialChars > password.length() * (0.2)) {
            return false;
        }

        for (int i = 0; i < password.length() - 3; i++) {
            if (((int) password.charAt(i) + 1 == (int) password.charAt(i + 1)) &&
                    ((int) password.charAt(i + 1) + 1 == (int) password.charAt(i + 2))) {
                return false;
            }
        }

        return hasUppercases && hasLowercases && hasDigits && (specialChars > 0);
    }
}
