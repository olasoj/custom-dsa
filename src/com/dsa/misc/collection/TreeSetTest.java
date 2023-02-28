package com.dsa.misc.collection;

import java.util.*;

public class TreeSetTest {

    public static void main(String[] args) {
        Set<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        Set<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);

        SortedMap<String, Item> treeMap = new TreeMap<>(Comparator.reverseOrder());
        parts.forEach(item -> treeMap.put(item.getDescription(), item));

        treeMap
                .values()
                .stream()
                .filter(item -> item.getDescription().startsWith("T"))
                .findFirst()
                .ifPresent(System.out::println);
    }

}

