package com.dsa.custom.graph.algorithm.sort;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.graph.search.DepthFirstSearch;
import com.dsa.custom.queue.LQueue;
import com.dsa.custom.queue.Queue;
import com.dsa.custom.stack.LStack;
import com.dsa.custom.stack.Stack;

public class DepthFirstOrder {

    private final Queue<Integer> pre;
    private final Queue<Integer> post;
    private final Stack<Integer> reversePost;  // vertices in reverse postorder

    public DepthFirstOrder(Graph digraph) {

        pre = new LQueue<>();
        post = new LQueue<>();
        reversePost = new LStack<>();

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(
                digraph,
                null,
                v -> {
                    post.enqueue(v);
                    reversePost.push(v);
                },
                pre::enqueue
        );

        for (int v = 0; v < digraph.noOfVertices(); v++)
            if (!depthFirstSearch.marked(v))
                depthFirstSearch.search(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
