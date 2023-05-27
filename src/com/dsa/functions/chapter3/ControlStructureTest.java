package com.dsa.functions.chapter3;

import com.dsa.functions.chapter2.Function;

import java.util.regex.Pattern;

public class ControlStructureTest {
    static final Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    static final Function<String, Result> emailChecker = s ->
    {
        if (s == null) {
            return new Result.Failure("email must not be null");
        } else {
            if (s.length() == 0) return new Result.Failure("email must not be empty");
            return emailPattern.matcher(s).matches()
                    ? new Result.Success() : new Result.Failure("email " + s + " is invalid.");
        }
    };

    public static void main(String... args) {
        validate("this.is@my.email").exec();
        validate(null).exec();
        validate("").exec();
        validate("john.doe@acme.com").exec();
    }


    private static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }

    private static void sendVerificationMail(String s) {
        System.out.println("Mail sent to " + s);
    }

    static Executable validate(String s) {
        Result result = emailChecker.apply(s);
        return (result instanceof Result.Success)
                ? () -> sendVerificationMail(s)
                : () -> logError(((Result.Failure) result).getMessage());
    }
}
