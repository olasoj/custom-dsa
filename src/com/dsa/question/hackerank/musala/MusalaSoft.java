package com.dsa.question.hackerank.musala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MusalaSoft {

    public static void main(String[] args) {
        System.out.println(compressedString("abc"));

        ArrayList<String> objects = new ArrayList<>();
        objects.add("code");
        objects.add("aaagmnrs");
        objects.add("anagrams");
        objects.add("doce");

        List<Integer> integers = List.of(2, 1, 3, 5, 2);

        System.out.println(maxSubsequenceLength(2, integers));

        System.out.println(funWithAnagrams(objects));
    }

    public static int maxSubsequenceLength(int k, List<Integer> arr) {

        if (arr.size() == 1) {
            return 1;
        }

        int counter = 0;

        int[] dp = new int[arr.size()];
        dp[0] = 1;

        for (int i = 1; i < arr.size(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if ((arr.get(i) ^ arr.get(j)) == k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            counter = Math.max(counter, dp[i]);
            dp[i] = Math.max(1, dp[i]);
        }


        return counter < 2 ? 0 : counter;

    }

    public static List<String> funWithAnagrams(List<String> text) {

        int size = text.size() - 1;
        for (int i = 0; i < size; i++) {

            int j = i + 1;
            while (j <= size) {
                String textItem = text.get(i);
                String textItem2 = text.get(j);

                if (areTextAnagrams(textItem, textItem2)) {
                    text.remove(j);
                    size = size - 1;
                } else {
                    j++;
                }
            }
        }

        Collections.sort(text);
        return (text);
    }

    private static boolean areTextAnagrams(String textItem, String textItem2) {

        if (textItem.length() != textItem2.length()) return false;

        char[] item = textItem.toCharArray();
        char[] item2 = textItem2.toCharArray();

        Arrays.sort(item);
        Arrays.sort(item2);
        return Arrays.equals(item, item2);
    }

    public static String compressedString(String message) {

        int counter = 0;
        StringBuilder strBuilder = new StringBuilder();

        int i = 0;
        while (i < message.length()) {

            while ((i + 1 < message.length()) && message.charAt(i) == message.charAt(i + 1)) {
                counter++;
                i++;
            }

            if (counter > 0) strBuilder.append(message.charAt(i)).append(counter + 1);
            else strBuilder.append(message.charAt(i));

            counter = 0;
            i++;

        }

        return strBuilder.toString();
    }
}
