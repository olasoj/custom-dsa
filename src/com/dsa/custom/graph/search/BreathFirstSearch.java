package com.dsa.custom.graph.search;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.queue.LQueue;

import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;

public class BreathFirstSearch extends AbstractGraphSearch {

    public BreathFirstSearch(Graph graph) {
        super(graph);
    }

    public BreathFirstSearch(Graph graph, BiConsumer<Integer, Integer> visitAction, IntConsumer postVisitAction, IntConsumer preVisitAction) {
        super(graph, visitAction, postVisitAction, preVisitAction);
    }

    @Override
    public void search(int v) {
        bfs(v);
    }

    private void bfs(int v) {

        LQueue<Integer> items = new LQueue<>();
        items.enqueue(v);
        marked[v] = true;

        while (!items.isEmpty()) {
            v = items.dequeue();
            preVisit(v);

            Iterator<Integer> it = graph.iterator(v);
            while (it.hasNext()) {
                int first = it.next();

                if (!marked[first]) {
                    visit(v, first);
                    items.enqueue(first);
                    marked[first] = true;
                    count++;
                }
            }

            postVisit(v);
        }

    }

}
