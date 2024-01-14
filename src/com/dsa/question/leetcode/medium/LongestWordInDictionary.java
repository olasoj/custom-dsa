package com.dsa.question.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class LongestWordInDictionary {

    public static void main(String[] args) {
        String[] words = new String[]{
                "a", "banana", "app", "appl", "ap", "apply", "apple"
        };
        String longestWord = longestWord(words);

        out.println(longestWord);

        String[] words2 = new String[]{
                "yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod", "ewqz", "y"
        };
        String longestWord2 = longestWord(words2);

        out.println(longestWord2);


        String[] words3 = new String[]{
                "ogz", "eyj", "e", "ey", "hmn", "v", "hm", "ogznkb", "ogzn", "hmnm", "eyjuo", "vuq", "ogznk", "og", "eyjuoi", "d"
        };
        String longestWord3 = longestWord(words3);

        //ogz eyj e hmn v ogznkb
        //ey

        out.println(longestWord3);
    }

    public static String longestWord(String[] words) {

        Arrays.sort(words);

        int depth = 0;
        Node root = new Node();
        String result = "";


        for (String s : words) {
            Node node = root;
            int d = 0;

            while (node != null) {

                Map<String, Node> st = node.st;
                String found = null;
                
                for (Map.Entry<String, Node> entrySet : st.entrySet()) {
                    String key = entrySet.getKey();

                    if (s.startsWith(key) && (found == null || found.length() < key.length())) {
                        node = entrySet.getValue();
                        found = key;
                    }
                }

                if (found == null) {
                    node.st.put(s, new Node());
                    node = null;
                }

                d++;
            }

            if (d >= depth) {
                if (result.isEmpty() || (d == depth && s.compareTo(result) < 0) || d > depth) result = s;
                depth = d;
            }

        }

        return result;
    }

    static class Node {
        Map<String, Node> st = new HashMap<>();
    }
}
