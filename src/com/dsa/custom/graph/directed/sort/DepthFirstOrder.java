package com.dsa.custom.graph.directed.sort;

import com.dsa.custom.graph.directed.Digraph;
import com.dsa.custom.graph.lgraph.Edge;
import com.dsa.custom.queue.LQueue;
import com.dsa.custom.queue.Queue;
import com.dsa.custom.stack.LStack;
import com.dsa.custom.stack.Stack;

public class DepthFirstOrder {

    private final boolean[] marked;
    private final Queue<Integer> pre;
    private final Queue<Integer> post;
    private final Stack<Integer> reversePost;  // vertices in reverse postorder

    public DepthFirstOrder(Digraph digraph) {
        pre = new LQueue<>();
        post = new LQueue<>();
        reversePost = new LStack<>();
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++)
            if (!marked[v]) dfs(digraph, v);
    }

    private void dfs(Digraph digraph, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (Edge edge : digraph.adj(v))
            if (!marked[edge.vertex()])
                dfs(digraph, edge.vertex());
        post.enqueue(v);
        reversePost.push(v);
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
