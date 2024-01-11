package com.dsa.question.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class PermutationInString {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        out.println(checkInclusion(s1, s2));
        out.println(checkInclusion(s1, "eidboaoo"));
        out.println(checkInclusion("adc", "dcda"));
        out.println(checkInclusion("abc", "bbbca"));
        out.println(checkInclusion("hello", "ooolleoooleh"));
    }

    public static boolean checkInclusion(String s1, String s2) {

        int s2L = s2.length();
        int s1L = s1.length();

        if (s2L < s1L) return false;

        Map<Character, Integer> map = initMap(s1);
        Map<Character, Integer> map2 = new HashMap<>();

        int numberChosen = 0;

        int i = 0;
        while (i < s1L) {
            numberChosen = getNumberChosen(s2, map, map2, numberChosen, i);
            i++;
        }

        int left = 0;

        while (i < s2L) {

            if (numberChosen == s1L) return true;

            char c2 = s2.charAt(left++);

            Integer listC = map.get(c2);
            Integer listC2 = map2.get(c2);

            if (listC != null && listC2 != null) {
                if (listC2 <= listC) {
                    numberChosen--;
                    map2.put(c2, listC2 - 1);
                } else {
                    map2.put(c2, listC2 - 1);
                }
            }

            numberChosen = getNumberChosen(s2, map, map2, numberChosen, i);

            i++;
        }

        return numberChosen == s1L;
    }

    private static int getNumberChosen(String s2, Map<Character, Integer> map, Map<Character, Integer> map2, int numberChosen, int i) {
        char c = s2.charAt(i);
        Integer list = map.get(c);
        Integer list2 = map2.getOrDefault(c, 0);

        if (list != null) {
            if (list2 < list) {
                numberChosen++;
                map2.put(c, list2 + 1);
            } else {
                map2.put(c, list2 + 1);
            }
        }
        return numberChosen;
    }

    //ooolleoooleh
    private static Map<Character, Integer> initMap(String s1) {

        int s1L = s1.length();

        Map<Character, Integer> multiMap = new HashMap<>();

        for (int i = 0; i < s1L; i++) {
            char c = s1.charAt(i);
            Integer charCount = multiMap.getOrDefault(c, 0);
            multiMap.put(c, charCount + 1);
        }

        return multiMap;
    }

}
