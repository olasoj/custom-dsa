package com.dsa.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

public class ReferenceUtil {

    public static String generateReference() {
        String shortCode = RandomStringUtils
                .randomAlphanumeric(6)
                .toUpperCase();

        Instant instant = Instant.now();

        return instant.toString()
                .replace("-", "")
                .replace(":", "")
                .replace("T", "_" + shortCode + "_")
                .replace(".", "");
    }
}
