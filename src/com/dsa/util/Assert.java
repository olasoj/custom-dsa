package com.dsa.util;

class Assert {

    private Assert() {
    }

    public static <T> void notNull(T value, String message) {
        if (value == null) throw new IllegalStateException(message);
    }
}
