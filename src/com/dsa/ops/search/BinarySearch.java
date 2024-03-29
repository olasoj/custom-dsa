package com.dsa.ops.search;

public class BinarySearch implements Search {

    @Override
    public int search(Integer[] items, int searchValue) {
        int l = 0;
        int r = items.length; // l and r are beyond array bounds while(l+1!=r){ //Stop when l and r meet
        while (l + 1 != r) {
            int i = (l + r) / 2;  // Check middle of remaining subarray
            if (searchValue < items[i]) r = i;     // In left half
            if (searchValue == items[i]) return i; // Found it
            if (searchValue > items[i]) l = i;     // In right half
        }
        return items.length; // Search value not in items
    }
}
