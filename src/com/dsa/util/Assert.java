package com.dsa.util;

public class Assert {

    private Assert() {
    }

    public static <T> void notNull(T value, String message) {
        if (value == null) throw new IllegalStateException(message);
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
