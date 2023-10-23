package com.dsa.custom.bag;

public class BagTest {

    public static void main(String[] args) {

        Bag<Integer> integers = new Bag<>();

        integers.add(3);
        integers.add(2342);
        integers.add(65);
        integers.add(4);
        integers.add(243);

        System.out.println(integers);

        for (Integer i : integers) {
            System.out.println(i);
        }
    }
}
