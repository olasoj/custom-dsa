package com.dsa.question.hackerank.remotebase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question2 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(34);
        list.add(5);
        list.add(7);
        list.add(9);

        List<Integer> rearrange = rearrange(list);

        System.out.println(rearrange);

    }

    public static List<Integer> rearrange(List<Integer> arr) {

        if (arr.size() < 3) return arr;
        List<Integer> dest = new ArrayList<>(arr);
        Collections.copy(dest, arr);
        Collections.sort(dest);

        int indexOfMin = arr.indexOf(dest.get(0));
        int indexOfNextMin = arr.indexOf(dest.get(1));

        int size = arr.size();

        swap(arr, indexOfMin, 2);
        if (size % 2 == 0) {
            swap(arr, indexOfNextMin, size - 2);
        } else {
            swap(arr, indexOfNextMin, size - 1);
        }

        return arr;
    }

    public static void swap(List<Integer> a, int replacingElementPosition, int foundElementPosition) {
        Integer temp = a.get(replacingElementPosition);
        a.set(replacingElementPosition, a.get(foundElementPosition));
        a.set(foundElementPosition, temp);
    }

}
