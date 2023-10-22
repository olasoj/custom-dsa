package com.dsa.question.leetcode.medium;

import java.util.StringJoiner;

public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(4);
        listNode.next = listNode1;

        listNode1.next = new ListNode(3);

        int anInt = getInt(listNode);
        System.out.println(anInt);

        ListNode listNode3 = new ListNode(5);
        ListNode listNode4 = new ListNode(6);
        listNode3.next = listNode4;

        listNode4.next = new ListNode(4);

        int anInt2 = getInt(listNode3);
        System.out.println(anInt2);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode listNode2 = addTwoNumbers.addTwoNumbers(listNode, listNode3);
        System.out.println(listNode2);
    }

    private static int getInt(ListNode l1) {
        StringBuilder value = new StringBuilder();
        do {
            value.append(l1.val);
            l1 = l1.next;
        } while (l1 != null);

        String s = value.toString();
        return Integer.parseInt(s);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int i = getInt(l1);
        int i2 = getInt(l2);

        int b = i + i2;
        char[] chars = ("" + b).toCharArray();

        int length = chars.length;

        ListNode listNode = new ListNode();
        ListNode tempListNode = listNode;
        for (int index = length - 1; index >= 0; index--) {
            int val = Integer.parseInt(String.valueOf(chars[index]));
            ListNode listNode1 = new ListNode(val);
            listNode.next = listNode1;
            listNode = listNode1;
        }

        return tempListNode.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ListNode.class.getSimpleName() + "[", "]")
                .add("val=" + val)
                .add("next=" + next)
                .toString();
    }
}
