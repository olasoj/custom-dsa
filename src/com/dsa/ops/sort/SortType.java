package com.dsa.ops.sort;

public enum SortType {
    INSERTION("INSERTION"),
    BUBBLE("BUBBLE"),
    SELECTION("SELECTION"),

    SHELL("SHELL"),
    MERGE("MERGE"),
    HEAP("HEAP"),
    QUICK("QUICK"),
    BIN("BIN"),
    RADIX("RADIX");

    private final String sortTypeName;

    SortType(String sortTypeName) {
        this.sortTypeName = sortTypeName;
    }

    public String sortTypeName() {
        return sortTypeName;
    }
}
