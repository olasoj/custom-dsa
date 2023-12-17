package com.dsa.question.leetcode.medium;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

public class CloneGraph {

    public static void main(String[] args) {

    }

    public Node cloneGraph2(Node node) {
        if (node == null) return null;

        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> oNode = new HashMap<>();
        oNode.put(node.getVal(), new Node(node.getVal()));
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            Node cNode = oNode.get(poll.getVal());
            List<Node> neighbors = cNode.getNeighbors();

            for (Node n : poll.getNeighbors()) {
                if (oNode.get(n.getVal()) == null) {
                    queue.offer(n);
                    Node cN = new Node(n.getVal());
                    neighbors.add(cN);
                    oNode.put(n.getVal(), cN);
                } else {
                    Node cN = oNode.get(n.getVal());
                    neighbors.add(cN);
                }
            }
        }

        return oNode.get(node.getVal());
    }

}

// Definition for a Node.
class Node {
    private int val;
    private List<Node> neighbors;

    public Node() {
        setVal(0);
        setNeighbors(new ArrayList<>());
    }

    public Node(int val) {
        this.setVal(val);
        setNeighbors(new ArrayList<>());
    }

    public Node(int val, List<Node> neighbors) {
        this.setVal(val);
        this.setNeighbors(neighbors);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node node)) return false;
        return getVal() == node.getVal() && Objects.equals(getNeighbors(), node.getNeighbors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVal(), getNeighbors());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("val", getVal())
                .append("neighbors", getNeighbors())
                .toString();
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }
}
