package com.dsa.misc.regex;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Test {

    private static final String CARD_PREFERRED_NAME_REGEX = "^[a-zA-Z0-9\\s]{5,19}$";
    private static final String CARD_ILLEGAL_NAME_REGEX = "[^a-zA-Z0-9]";
    private static final int PREFERRED_NAME_MAX_LENGTH = 19;
    private static final int PREFERRED_NAME_MIN_LENGTH = 5;

    public static void main(String[] args) {
        String formalName0 = "BABAGINDA ALIYU, JAMILA HARUNA ";

        logFormalNameTransformation(formalName0, getPreferredName(formalName0));
    }

    private static String getPreferredName(String formalName) {

        String result = getPreferredNameInternal(formalName);
        if (StringUtils.isNotBlank(result)) return result;

        String tamperedFormalName = formalName.replaceAll(CARD_ILLEGAL_NAME_REGEX, " ").trim();

        result = getPreferredNameInternal(tamperedFormalName);
        if (StringUtils.isNotBlank(result)) return result;

        throw new IllegalStateException("Unable to get preferred name for " + formalName);
    }

    private static String getPreferredNameInternal(String name) {

        if (StringUtils.isBlank(name) || name.length() < PREFERRED_NAME_MIN_LENGTH) {
            return null;
        }

        if (name.matches(CARD_PREFERRED_NAME_REGEX)) {
            return name;
        }

        if (name.length() > PREFERRED_NAME_MAX_LENGTH) {
            return removeInvalidTokens(name);
        }

        return null;
    }

    private static String removeInvalidTokens(String tamperedFormalName) {

        final String STRING_SPACE_DELIMITER = "[\\s,.]";
        String[] split = tamperedFormalName.split(STRING_SPACE_DELIMITER);


        String collect = Arrays.stream(split)
                .filter(s -> s.matches(CARD_PREFERRED_NAME_REGEX))
                .collect(Collectors.joining(" "));

        out.println(collect);

        String result = "";
        for (String token : split) {
            String tempResult = result;
            if (!token.matches(CARD_ILLEGAL_NAME_REGEX)) {
                tempResult = tempResult + " " + token;
                if (tempResult.matches(CARD_PREFERRED_NAME_REGEX)) {
                    result = tempResult.strip();
                }
            }
        }

        return StringUtils.isBlank(result) ? null : result;
    }

    private static void logFormalNameTransformation(String formalName, String preferredName) {
        out.println("Formal name: " + formalName + "  was transformed to " + preferredName);
    }

}
