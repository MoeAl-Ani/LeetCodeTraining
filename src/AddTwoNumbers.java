import java.util.*;

/**
 * @author Mohammed Al-Ani
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;

        ListNode head = null;
        ListNode tail = null;
        while (l1 != null || l2 != null) {
            Integer n1 = null;
            Integer n2 = null;
            try {
                n1 = l1.val;
                l1 = l1.next;
            } catch (Exception e) {
            }
            try {
                n2 = l2.val;
                l2 = l2.next;
            } catch (Exception e) {
            }

            int sum = 0;
            if (n1 != null && n2 != null) {
                // cal n1 & n2
                sum = n1 + n2 + carry;
            } else if (n1 != null) {
                // cal n1
                sum = n1 + carry;
            } else {
                // cal n2
                sum = n2 + carry;
            }
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            if (head == null) {
                head = new ListNode(sum);
                tail = head;
            } else {
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }


      //Definition for singly-linked list.
      private static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public static void main(String[] args) {
        int[] num1Arr = {2,4, 3};
        int[] num2Arr = {5,6,4};
        ListNode h1 = null;
        for (int i = num1Arr.length -1; i >= 0; i--) {
            if (h1 == null) {
                h1 = new ListNode();
                h1.val = num1Arr[i];
            } else {
                ListNode temp = h1;
                h1 = new ListNode(num1Arr[i]);
                h1.next = temp;
            }
        }

        ListNode h2 = null;
        for (int i = num2Arr.length -1; i >= 0; i--) {
            if (h2 == null) {
                h2 = new ListNode();
                h2.val = num2Arr[i];
            } else {
                ListNode temp = h2;
                h2 = new ListNode(num2Arr[i]);
                h2.next = temp;
            }
        }
        ListNode current = new AddTwoNumbers().addTwoNumbers(h1, h2);
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }
}
