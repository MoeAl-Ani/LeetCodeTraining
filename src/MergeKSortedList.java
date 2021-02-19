import java.util.PriorityQueue;

public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compareTo);
        if (lists.length == 0) return null;

        for (ListNode list : lists) {
            ListNode temp = list;
            while (temp != null) {
                pq.add(temp.val);
                temp = temp.next;
            }
        }

        ListNode head = null;
        ListNode tail = null;
        while (!pq.isEmpty()) {
            Integer data = pq.poll();
            if (head == null) {
                ListNode node = new ListNode(data);
                head = node;
                tail = node;
            } else {
                ListNode temp = tail;
                ListNode listNode = new ListNode(data);
                tail.next = listNode;
                tail = listNode;
            }
        }
        return head;
    }
    static class ListNode {
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

    public static void main(String[] args) {
        ListNode[] listNodes = {
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        };

        new MergeKSortedList().mergeKLists(listNodes);

    }
}

