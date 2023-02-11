package com.dsa.misc.collection;

import java.util.*;

enum NatureEnum {

    UNKNOWN,
    PAGA,
    AGENT,
    MERCHANT,
    CASH_COLLECTOR,
    CUSTOMER,
    AFFILIATE,
    BUSINESS,
    CARD_PROCESSOR,
    POS_PROCESSOR,
    CARD_ACQUIRER,
    CARD_ISSUER,
    REMITTANCE_PROCESSOR,
    MOBILE_OPERATOR,
    BANK,
    QR_PAYMENT_PROCESSOR,
    SERVICE_AGGREGATOR,
    MERCHANT_AGGREGATOR,
    LENDER,
    TRADER,
    AFFILIATE_AGENT,
    AGENT_AGGREGATOR,
    SUBSIDIARY,
    CASHLESS_COLLECTOR,
    SUBSIDIARY_PARENT,
    PAGA_TERMINAL_MANAGER,
    DOROKI_MERCHANT,
    OPEN_WALLET
}

public class TreeSetTest {
    private static final Set<NatureEnum> ALLOWED_NATURE_FOR_OVERDRAFT;
    private static final Set<NatureEnum> NATURE_ENUM_LIST = Set.of(NatureEnum.DOROKI_MERCHANT, NatureEnum.AGENT, NatureEnum.AFFILIATE, NatureEnum.BANK,
            NatureEnum.BUSINESS, NatureEnum.MERCHANT, NatureEnum.MERCHANT_AGGREGATOR
    );

    static {
        ALLOWED_NATURE_FOR_OVERDRAFT = Set.of(NatureEnum.AGENT, NatureEnum.BUSINESS);
    }

    public static void main(String[] args) {
        Set<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        Set<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);

        Set<NatureEnum> natureEnums = new TreeSet<>(Comparator.naturalOrder());

        natureEnums.addAll(NATURE_ENUM_LIST);
        for (NatureEnum natureEnum : NATURE_ENUM_LIST)
            System.out.print(natureEnum + " ");

        System.out.println();
        for (NatureEnum natureEnum : natureEnums) {
            if (ALLOWED_NATURE_FOR_OVERDRAFT.contains(natureEnum)) {
                System.out.print(natureEnum + " ");
                break;
            }
        }
    }

    public static String findNumber(List<Integer> arr, int k) {
        if (arr == null || arr.isEmpty()) return "NO";

        for (Integer a : arr)
            if (a.equals(k)) return "YES";

        return "NO";
    }

    public static List<Integer> oddNumbers(int l, int r) {

        int lessNumber = Math.min(l, r);
        int greaterNumber = Math.max(l, r);

        List<Integer> integers = new ArrayList<>();

        if (l == r) {
            if (isOdd(l))
                integers.add(l);
            return integers;
        }

        for (int i = lessNumber; i <= greaterNumber; i++) {
            if (isOdd(i)) {
                integers.add(i);
            }
        }


        return integers;
    }

    private static boolean isOdd(int i) {
        return (i % 2) != 0;
    }
}

