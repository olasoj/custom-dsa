package com.dsa.question.hackerank.unnamed;

import java.util.ArrayList;
import java.util.List;

import static com.dsa.misc.Factorial.fact;

public class Question1 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(34);
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(9);

        int i = countTeams(list, 3, 5, 9);

        System.out.println(i);

    }

    public static int countTeams(List<Integer> skills, int minPlayers, int minLevel, int maxLevel) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Integer skill : skills)
            if (skill >= minLevel && skill <= maxLevel)
                stringBuilder.append(skill);

        String toString = stringBuilder.toString();
        return combinations(toString, minPlayers);
    }

    private static int combinations(String toString, int minPlayers) {
        int length = toString.length();
        return fact(length) / (fact(minPlayers) * fact(length - minPlayers));
    }


}
