package com.dsa.question.codility.proximiyt;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Q1 {

    public static void main(String[] args) {
        int[] integers = {1, 3, 6, 4, 1, 2};


        Instant start = Instant.now();
        solution(integers);
        Instant end = Instant.now();

        Duration between = Duration.between(start, end);
        System.out.println(between);
    }

    public static int solution(int[] A) {
        // Implement your solution here

        for (int i = 1; ; i++) {
            int finalI = i;
            boolean anyMatch = Arrays.stream(A).anyMatch(a -> a != finalI);
            if (anyMatch) return i;
            if (i > 100000) throw new UnsupportedOperationException();
        }
    }

    public static boolean isParceableToLong(String s) {

        int radix = 10;
        if (s == null) {
            return false;
        }

        boolean negative = false;
        int i = 0, len = s.length();
        long limit = -Long.MAX_VALUE;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Long.MIN_VALUE;
                } else if (firstChar != '+') {
                    return false;
                }

                if (len == 1) { // Cannot have lone "+" or "-"
                    return false;
                }
                i++;
            }
            long multmin = limit / radix;
            long result = 0;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int digit = Character.digit(s.charAt(i++), radix);
                if (digit < 0 || result < multmin) {
                    return false;
                }
                result *= radix;
                if (result < limit + digit) {
                    return false;
                }
                result -= digit;
            }
            return true;
        } else {
            return false;
        }
    }

    Map<Long, Long> count(Map<String, UserStats>... visits) {

        Map<Long, Long> count = new HashMap<>();

        if (Objects.isNull(visits)) return count;


        for (Map<String, UserStats> visit : visits) {

            if (Objects.isNull(visit)) continue;

            visit.forEach((key, value) -> {
                if (!isParceableToLong(key) || Objects.isNull(value) || !value.getVisitCount().isPresent()) return;
                long keyLong = Long.parseLong(key);
                Long previousCount = Optional
                        .ofNullable(count.get(keyLong))
                        .orElse(0L);
                count.put(keyLong, value.getVisitCount().get() + previousCount);
            });

        }
        return count;
    }

    class UserStats {
        private Long visitCount;

        public Optional<Long> getVisitCount() {
            return Optional.ofNullable(visitCount);
        }
    }
}
