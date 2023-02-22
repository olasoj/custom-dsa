package com.dsa.question.hackerank.unnamed;

import java.util.*;

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
        Collections.sort(arr);

        Set<Object> set = new HashSet<>(arr);

        int size = arr.size() - 1;

        swap(arr, 0, 2);
        if (size % 2 == 0) {
            swap(arr, 1, size - 2);
        } else {
            swap(arr, 1, size - 1);
        }

        return arr;
    }

    public static void swap(List<Integer> a, int replacingElementPosition, int foundElementPosition) {
        Integer temp = a.get(replacingElementPosition);
        a.set(replacingElementPosition, a.get(foundElementPosition));
        a.set(foundElementPosition, temp);
    }

}
