package com.dsa.question.leetcode.medium;

import java.util.*;

public class CourseSchedule {

    private Map<Integer, Boolean> marked;
    private Map<Integer, Boolean> onStack;
    private Stack<Integer> stack;

    public static void main(String[] args) {

    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        marked = new HashMap<>();
        onStack = new HashMap<>();
        stack = new Stack<>();

        int[] result = new int[numCourses];

        boolean hasCycle = hasCycle(buildGraph(prerequisites, numCourses)
                , numCourses);

        if (hasCycle) {
            return new int[0];
        }

        while (!stack.isEmpty()) {
            result[--numCourses] = stack.pop();
        }
        return result;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> graph, int numCourses) {

        for (int i = 0; i < numCourses; i++) {
            if (!Boolean.TRUE.equals(marked.get(i))) {
                boolean dfs = dfs(graph, i);
                if (dfs) return true;

            }
        }

        return false;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] prerequisites, int numCourses) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++)
            graph.put(i, new ArrayList<>());


        for (int[] prerequisite : prerequisites) {
            int parent = prerequisite[0];

            List<Integer> edges = graph.get(parent);
            edges.add(prerequisite[1]);
        }

        return graph;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int s) {

        boolean hasCycle = false;

        marked.put(s, true);
        onStack.put(s, true);

        List<Integer> edges = graph.get(s);

        if (edges != null) {
            for (int edge : edges) {
                if (!Boolean.TRUE.equals(marked.get(edge)) && !hasCycle) {
                    hasCycle = dfs(graph, edge);
                } else if (Boolean.TRUE.equals(onStack.get(edge))) {
                    return true;
                }
            }
        }

        onStack.put(s, false);
        stack.push(s);
        return hasCycle;
    }
}
