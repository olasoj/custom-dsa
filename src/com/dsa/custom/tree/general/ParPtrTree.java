package com.dsa.custom.tree.general;

import java.util.Objects;

/**
 * General Tree class implementation for UNION/FIND
 */
public class ParPtrTree {
    private final Integer[] array; // Node array

    // Create node array
    public ParPtrTree(int size) {
        array = new Integer[size];
    }

    /**
     * Determine if nodes are in different trees
     */
    public boolean differ(int a, int b) {
        Integer root1 = FIND(a);  // Find root of node a
        Integer root2 = FIND(b);  // Find root of node b
        return !Objects.equals(root1, root2); // Compare roots
    }

    /**
     * Merge two subtrees
     */
    public void UNION(int a, int b) {

        Integer root1 = FIND(a);  // Find root of node a
        Integer root2 = FIND(b); // Find root of node b
        if (!Objects.equals(root1, root2)) array[root2] = root1; // Merge
    }


    String print() {
        StringBuffer out = new StringBuffer(100);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) out.append("-1 ");
            else {
                Integer temp = array[i];
                for (int j = 0; j < array.length; j++)
                    if (Objects.equals(temp, array[j])) {
                        out.append(j).append(" ");
                        break;
                    }
            }
        }
        return out.toString();
    }

    public Integer FIND(Integer curr) {
        if (array[curr] == null) return curr;  // At root
        while (array[curr] != null) curr = array[curr];
        return curr;
    }
}
