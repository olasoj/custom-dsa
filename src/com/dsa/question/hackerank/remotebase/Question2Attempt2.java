package com.dsa.question.hackerank.remotebase;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Question2Attempt2 {

    public static void main(String[] args) {

        String s = "hcolalpeyffeeelhlohp";
        List<String> dic = List.of("hello", "coffee", "happy");
        System.out.println(getValidWord(s, dic));

    }

    public static String getValidWord(String s, List<String> dictionary) {
        // Write your code here

        Queue<String> pq = new PriorityQueue<>();

        for (String word : dictionary) {
            if (isWordValid(s, word)) pq.add(word);
        }

        return pq.isEmpty() ? "-1" : pq.poll();
    }

    private static boolean isWordValid(String s, String word) {

        int length = s.length();

        int wordLength = word.length();
        int wordCounter = 0;
        int counter = 0;

        if (wordLength > length) return false;

        for (int j = 0; j < wordLength; j++) {

            for (int i = counter; i < length; i++) {

                if (s.charAt(i) == word.charAt(j)) {
                    counter++;
                    if (wordLength == ++wordCounter) return true;
                    break;
                } else {
                    counter++;
                }

            }
        }
        return false;
    }
}
