package com.dsa.question.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class LinkedListCycle {

    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
        Map<ListNode, Integer> seeing = new HashMap<>();

        int counter = -1;
        ListNode tempHead = null;
        while (head != null) {
            if (seeing.get(head) == null) {
                seeing.put(head, ++counter);
                if (counter > 0) tempHead = head.next;
                head = head.next;
            } else {
                return tempHead;
            }
        }

        return null;
    }

    public boolean hasCycle(ListNode head) {
        Map<ListNode, Integer> seeing = new HashMap<>();

        int counter = -1;
        while (head != null) {
            if (seeing.get(head) == null) {
                seeing.put(head, ++counter);
                head = head.next;
            } else {
                return true;
            }
        }

        return false;
    }
}
