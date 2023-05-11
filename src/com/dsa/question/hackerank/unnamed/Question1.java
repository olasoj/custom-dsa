package com.dsa.question.hackerank.unnamed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        int i1 = countPairs(list, 2);
        System.out.println(i1);

    }

    static int countPairs(List<Integer> projectCosts, int target) {
        if (projectCosts == null) return 0;
        if (projectCosts.size() > 10000) return 0;

        int count = 0;
        int lenght = projectCosts.size();

        Map<Integer, Integer> calues = new HashMap<>();

        for (Integer projectCost : projectCosts) {
            for (int i = 1; i < lenght; i++) {
                Integer otherValue = projectCosts.get(i);
                int abs = Math.abs(projectCost - otherValue);

                if (abs == target) {
                    int min = Math.min(projectCost, otherValue);
                    int max = Math.max(projectCost, otherValue);
                    calues.putIfAbsent(min, max);
                }

            }
        }

        return calues.size();
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
