package com.dsa.ops.search;

public interface Search {
    /**
     * @param items
     * @returns Return the position of an element in sorted array "items"  with value "searchValue".  If "searchValue" is not in "items", return items.length.
     */
    int search(int[] items, int searchValue);
}
