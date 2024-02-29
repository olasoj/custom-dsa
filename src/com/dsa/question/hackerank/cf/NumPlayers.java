package com.dsa.question.hackerank.cf;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class NumPlayers {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(5, 20, 40, 60, 80, 100));
        int i = numPlayers(4, list);
        out.println(i);
    }

    public static int numPlayers(int k, List<Integer> scores) {
        // Write your code here

        if (scores.isEmpty()) return 0;
//        scores.sort(Comparator.reverseOrder());

        int playersFound = 1;
        int currentRank = 1;
        int playerAdd = 1;
        int currentScore = scores.get(0);

        for (int i = 1; i < scores.size(); i++) {
            int curEle = scores.get(i);
            if (curEle == 0) continue;

            playersFound++;
            if (curEle != currentScore) {
                currentRank = playersFound;
                currentScore = curEle;
            }

            if (currentRank > k) return playerAdd;
            playerAdd++;
        }

        return playerAdd;
    }
}
