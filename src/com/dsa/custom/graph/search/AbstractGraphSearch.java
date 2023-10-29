package com.dsa.custom.graph.search;

import com.dsa.custom.graph.Graph;
import com.dsa.util.Assert;

import java.util.function.BiConsumer;
import java.util.function.IntConsumer;

public abstract class AbstractGraphSearch implements GraphSearch {

    protected final Graph graph;
    protected final boolean[] marked;
    protected int count;
    private IntConsumer postVisitAction;
    private IntConsumer preVisitAction;
    private BiConsumer<Integer, Integer> visitAction;

    protected AbstractGraphSearch(Graph graph) {
        Assert.notNull(graph, "Graph cannot be null");

        this.graph = graph;
        marked = new boolean[graph.noOfVertices()];
    }

    protected AbstractGraphSearch(Graph graph, BiConsumer<Integer, Integer> visitAction, IntConsumer postVisitAction, IntConsumer preVisitAction) {
        this(graph);
        this.postVisitAction = postVisitAction;
        this.preVisitAction = preVisitAction;
        this.visitAction = visitAction;
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void visit(int v, int e) {
        if (visitAction != null && !marked(v)) visitAction.accept(v, e);
    }

    public void preVisit(int v) {
        if (preVisitAction != null && !marked(v)) preVisitAction.accept(v);
    }

    public void postVisit(int v) {
        if (postVisitAction != null && !marked(v)) postVisitAction.accept(v);
    }

}
