package com.dsa.ops.search;

import com.dsa.ops.sort.quick.QuickUtil;
import com.dsa.util.DSUtil;

public class QuickSelect implements Search {

    // finds the kth position (of the sorted array)
    // in a given unsorted array i.e. this function
    // can be used to find both kth largest and
    // kth the smallest element in the array.
    // ASSUMPTION: all elements in arr[] are distinct
    private static int quickSelect(Integer[] arr, int low, int high, int k) {

        int pivot = QuickUtil.findPivot(arr, low, high);
        // find the partition

        DSUtil.swap(arr, pivot, high);       // Stick pivot at end
        int partition = QuickUtil.partitionMax(arr, low - 1, high, arr[high]);
        DSUtil.swap(arr, partition, high);

        // if partition value is equal to the kth position,
        // return value at k.
        if (partition == k)
            return partition;

            // if partition value is less than kth position,
            // search right side of the array.
        else if (partition < k)
            return quickSelect(arr, partition + 1, high, k);

            // if partition value is more than kth position,
            // search left side of the array.
        else return quickSelect(arr, low, partition - 1, k);
    }

    @Override
    public int search(Integer[] items, int searchValue) {

        int k = searchValue - 1;
        if (k < 0 || k > items.length) {
            return -1;
        }
        int i = quickSelect(items, 0, items.length - 1, k);

        return items[i];
    }
}
//&& (partition - low) > 1
// && (high - partition) > 1