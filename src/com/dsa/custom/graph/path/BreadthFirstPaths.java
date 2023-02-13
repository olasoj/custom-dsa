package com.dsa.custom.graph.path;

import com.dsa.custom.graph.Graph;
import com.dsa.custom.queue.LQueue;
import com.dsa.custom.queue.Queue;

import java.util.Iterator;

public class BreadthFirstPaths extends AbstractPaths {

    // source
    private final boolean[] marked; // Is a shortest path to this vertex known?
    private final int[] edgeTo;     // last vertex on known path to this vertex

    public BreadthFirstPaths(Graph graph, int s) {
        super(graph, s);
        marked = new boolean[graph.noOfVertices()];
        edgeTo = new int[graph.noOfEdges()];
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new LQueue<>();
        marked[s] = true;          // Mark the source
        queue.enqueue(s);          //   and put it on the queue.

        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // Remove next vertex from the queue.

            for (Iterator<Integer> it = graph.iterator(v); it.hasNext(); ) {
                int w = it.next();

                // For every unmarked adjacent vertex,
                if (!marked[w]) {
                    edgeTo[w] = v;     //    save last edge on the shortest path,
                    marked[w] = true;  //    mark it because path is known,
                    queue.enqueue(w);  //    and add it to the queue.
                }
            }
        }
    }

}
