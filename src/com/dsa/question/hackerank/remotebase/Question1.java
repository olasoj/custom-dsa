package com.dsa.question.hackerank.remotebase;

import java.util.*;

public class Question1 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(34);
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(9);

        List<Integer> integers = List.of(4, 8, 5, 6);

        int i = countTeams(integers, 1, 5, 7);

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

        Set<Integer> skillsSet = new HashSet<>();

        for (Integer skill : skills)
            if (skill >= minLevel && skill <= maxLevel)
                skillsSet.add(skill);


        int counter = 0;
        for (int i = minPlayers; i <= skillsSet.size(); i++) {
            counter = counter + combinations(skillsSet.size(), i);
        }
        return counter;
    }

    private static int combinations(Integer length, int minPlayers) {
        return fact(length) / (fact(minPlayers) * fact(length - minPlayers));
    }

    public static int fact(int n) {
        return (n <= 1) ? 1 : fact(n - 1) * n;
    }


}
