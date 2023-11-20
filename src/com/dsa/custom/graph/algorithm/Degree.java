package com.dsa.custom.graph.algorithm;

import com.dsa.custom.graph.Graph;

import java.util.Iterator;

public class Degree {

    public static int degree(Graph graph, int v) {
        int degree = 0;
        Iterator<Integer> iterator = graph.iterator(v);
        while (iterator.hasNext()) {
            iterator.next();
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph graph) {
        int max = 0;
        for (int v = 0; v < graph.noOfVertices(); v++)
            if (degree(graph, v) > max)
                max = degree(graph, v);
        return max;
    }

    public static int avgDegree(Graph graph) {
        return 2 * graph.noOfEdges() / graph.noOfVertices();
    }

    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;

        for (int v = 0; v < graph.noOfVertices(); v++) {
            Iterator<Integer> iterator = graph.iterator(1);
            while (iterator.hasNext()) {
                Integer w = iterator.next();
                if (v == w) count++;
            }
        }

        return count / 2;   // each edge counted twice
    }


}
