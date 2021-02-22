import java.util.*;

public class RotateList {

    static public class ListNode {
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

    public ListNode rotateRight(ListNode head, int k) {

        ListNode tail = null;
        ListNode ass = head;
        int size = 0;
        while (ass != null) {
            ass = ass.next;
            size++;
        }
        if (size == 0) return head;
        int newK = k % size;
        if (newK == 0) return head;
        while (newK > 0) {
            ListNode headTemp = head;
            ListNode prev = null;
            while (headTemp != null) {

                if (headTemp.next == null) {
                    tail = headTemp;
                } else {
                    prev = headTemp;
                }
                headTemp = headTemp.next;
            }
            if (tail != null && prev != null) {
                tail.next = head;
                prev.next = null;
            }
            head = tail;
            newK--;
        }
        return tail == null ? head: tail;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode head3 = new ListNode(1, new ListNode(2));
        new RotateList().rotateRight(head, 2);
        new RotateList().rotateRight(head2, 2000000000);
        //new RotateList().rotateRight(head3, 1);
    }
}
