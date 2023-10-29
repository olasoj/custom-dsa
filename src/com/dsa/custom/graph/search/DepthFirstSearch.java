package com.dsa.custom.graph.search;

import com.dsa.custom.graph.Graph;

import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;

public class DepthFirstSearch extends AbstractGraphSearch {

    public DepthFirstSearch(Graph graph) {
        super(graph);
    }

    public DepthFirstSearch(Graph graph, BiConsumer<Integer, Integer> visitAction, IntConsumer postVisitAction, IntConsumer preVisitAction) {
        super(graph, visitAction, postVisitAction, preVisitAction);
    }

    private void dfs(int v) {
        preVisit(v);
        marked[v] = true;
        count++;

        Iterator<Integer> it = graph.iterator(v);
        while (it.hasNext()) {
            int w = it.next();
            if (!marked[w]) {
                visit(v, w);
                dfs(w);
            }
        }

        postVisit(v);
    }

    @Override
    public void search(int v) {
        dfs(v);
    }

}
