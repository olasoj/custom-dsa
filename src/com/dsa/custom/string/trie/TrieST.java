package com.dsa.custom.string.trie;

import com.dsa.custom.queue.LQueue;
import com.dsa.custom.queue.Queue;

public class TrieST<V> {
    private static final int RADIX = 256; // radix
    private Node root;          // root of trie

    public V get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (V) x.val;
    }

    private Node get(Node x, String key, int d) {  // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, V val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, V val, int d) {  // Change value associated with key if in subtrie rooted at x.
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LQueue<>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < RADIX; c++)
            collect(x.next[c], pre + c, q);
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LQueue<>();
        collect(root, "", pat, q);
        return q;
    }

    public void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.val != null) q.enqueue(pre);
        if (d == pat.length()) return;
        char next = pat.charAt(d);
        for (char c = 0; c < RADIX; c++)
            if (next == '.' || next == c)
                collect(x.next[c], pre + c, pat, q);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.val != null) return x;
        for (char c = 0; c < RADIX; c++)
            if (x.next[c] != null) return x;
        return null;
    }


    private static class Node {
        private final Node[] next = new Node[RADIX];
        private Object val;
    }
}
