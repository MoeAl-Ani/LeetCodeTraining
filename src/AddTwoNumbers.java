import java.util.*;

/**
 * @author Mohammed Al-Ani
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> n1Stack = new Stack<>();
        Stack<Integer> n2Stack = new Stack<>();
        while (l1 != null) {
            n1Stack.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            n2Stack.push(l2.val);
            l2 = l2.next;
        }

        Deque<Integer> rs = new ArrayDeque<>();
        int carry = 0;
        Iterator<Integer> n1Iterator = n1Stack.iterator();
        Iterator<Integer> n2Iterator = n2Stack.iterator();
        while (n1Iterator.hasNext() || n2Iterator.hasNext()) {
            Integer n1 = null;
            Integer n2 = null;
            try {
                n1 = n1Iterator.next();
            } catch (Exception e) {

            }
            try {
                n2 = n2Iterator.next();
            } catch (Exception e) {
            }

            if (n1 != null && n2 != null) {
                // cal n1 & n2
                int sum = n1 + n2 + carry;
                if (sum >= 10) {
                    carry = sum / 10;
                    rs.push(sum % 10);
                } else {
                    carry = 0;
                    rs.push(sum);
                }
            } else if (n1 != null) {
                // cal n1
                int sum = n1 + carry;
                if (sum >= 10) {
                    carry = sum / 10;
                    rs.push(sum % 10);
                } else {
                    carry = 0;
                    rs.push(sum);
                }
            } else {
                // cal n2
                int sum = n2 + carry;
                if (sum >= 10) {
                    carry = sum / 10;
                    rs.push(sum % 10);
                } else {
                    carry = 0;
                    rs.push(sum);
                }
            }

        }
        if (carry > 0) {
            rs.push(carry);
        }

        ListNode head = null;

        while (!rs.isEmpty()) {
            if (head == null) {
                head = new ListNode();
                head.val = rs.poll();
            } else {
                ListNode temp = head;
                head = new ListNode(rs.poll());
                head.next = temp;
            }
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
        int[] num1Arr = {1,8};
        int[] num2Arr = {0};
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
