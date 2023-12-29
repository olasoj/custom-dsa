package com.dsa.ops.design.rangequeries;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Using this representation,
 * the parent of tree[k] is tree[⌊k/2⌋], and its children are tree[2k] and tree[2k + 1].
 * tree[1] is the top node, tree[2] and tree[3] are its children, and so on.
 * Finally, the values from tree[n] to tree[2n−1]
 */
public class SegmentTree {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 6, 3, 2, 7, 2, 6};

        //33,
        // 33, 19,
        // 14, 14, 5, 9,
        // 5, 8, 6, 3, 2, 7, 2

        int[] segmentTree = buildSegmentTree(arr);
        int sum = sum(2, 7, segmentTree);
        out.println(sum);
    }

    private static int[] buildSegmentTree(int[] arr) {
        int n = arr.length;
        int[] segTree = new int[n * 2];

        //Populate child node
        int counter = 0;
        for (int i = n; i < segTree.length; i++) { //O(n)
            segTree[i] = arr[counter++];
        }

        for (int i = n - 1; i >= 0; i--) { //O(n)
            // populate parent = LC + RC
            segTree[i] = segTree[2 * i] + segTree[2 * i + 1];
        }

        out.println(Arrays.toString(segTree));
        return segTree;
    }

    private static int sum(int a, int b, int[] tree) {

        int n = tree.length / 2;

        a += n;  //Search in the original array.
        b += n;  //Search in the original array.
        int s = 0;

        while (a <= b) {
            if (a % 2 == 1) s += tree[a++];
            if (b % 2 == 0) s += tree[b--];
            a /= 2;
            b /= 2;
        }
        return s;
    }


    private static void add(int k, int x, int[] tree) {

        int n = tree.length / 2;

        k += n;
        tree[k] += x;
        for (k /= 2; k >= 1; k /= 2) {
            tree[k] = tree[2 * k] + tree[2 * k + 1];
        }
    }
}
