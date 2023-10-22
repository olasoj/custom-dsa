package com.dsa.question.leetcode.easy;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null && list2 == null) return null;
        if (list1 != null && list2 == null) return list1;
        if (list1 == null) return list2;

        ListNode head = new ListNode();
        ListNode tempHead = head;

        do {

            //What if val1 > val2
            if (
                    (list1 != null && list2 != null)
                            && (list1.val < list2.val)
                            || (list1 != null && list2 == null)
            ) {
                head.next = new ListNode(list1.val);
                head = head.next;
                list1 = list1.next;
            } else if (
                //What if val2 > val1
                    list1 == null || list2.val < list1.val
            ) {
                head.next = new ListNode(list2.val);
                head = head.next;
                list2 = list2.next;
            } else {
                //What if val2 == val1

                head.next = new ListNode(list1.val);
                head = head.next;
                list1 = list1.next;

                head.next = new ListNode(list2.val);
                head = head.next;
                list2 = list2.next;
            }


        } while (list1 != null || list2 != null);

        return tempHead.next;

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
}