package com.dsa.ops.design.rangequeries;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Using this representation,
 * the parent of tree[k] is tree[⌊k/2⌋], and its children are tree[2k] and tree[2k + 1].
 * tree[1] is the top node, tree[2] and tree[3] are its children, and so on.
 * Finally, the values from tree[n] to tree[2n−1]
 */
public class MaxSegmentTree {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 6, 3, 2, 7, 2};

        int[] segmentTree = buildSegmentTree(arr);
        int sum = sum(2, 4, segmentTree);
        out.println(sum);
    }

    private static int[] buildSegmentTree(int[] arr) {
        int n = arr.length;

        boolean isOdd = (n % 2 == 1);
        int[] segTree = new int[isOdd ? ((n + 1) * 2) : (n * 2)];

        if (isOdd) segTree[segTree.length - 1] = Integer.MIN_VALUE;

        //Populate child node
        int counter = n;
        int i1 = isOdd ? segTree.length - 2 : segTree.length - 1;
        for (int i = i1; i >= n; i--) { //O(n)
            --counter;
            if (counter >= 0)
                segTree[i] = arr[counter];
        }

        for (int i = n - 1; i >= 0; i--) { //O(n)
            // populate parent = LC + RC
            segTree[i] = Math.max(segTree[2 * i], segTree[2 * i + 1]);
        }

        out.println(Arrays.toString(segTree));
        return segTree;
    }

    private static int sum(int a, int b, int[] tree) {

        int n = tree.length / 2;

        a += n;  //Search in the original array.
        b += n;  //Search in the original array.
        int s = Integer.MIN_VALUE;

        while (a <= b) {
            if (a % 2 == 1) s = Math.max(s, tree[a++]);
            if (b % 2 == 0) s = Math.max(s, tree[b--]);
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
            tree[k] = Math.max(tree[2 * k], tree[2 * k + 1]);
        }
    }
}

//1, 1,
// 1, -1