import java.util.*;

public class ConvertSortedLinkedListToBST {

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


    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null)
            return new TreeNode(head.val);
        ListNode temp = head;
        List<Integer> nodesValues = new ArrayList<>();
        while (temp != null) {
            nodesValues.add(temp.val);
            temp = temp.next;
        }

        return buildBST(nodesValues, 0, nodesValues.size()-1);
    }

    TreeNode buildBST(List<Integer> nodesValues, int begIndex, int endIndex) {
        if (begIndex > endIndex) {
            return null;
        }

        int mid = begIndex + (endIndex - begIndex) / 2;
        TreeNode node = new TreeNode(nodesValues.get(mid));
        node.left = buildBST(nodesValues, begIndex, mid-1);
        node.right = buildBST(nodesValues, mid+1, endIndex);
        return node;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-10, new ListNode(-3, new ListNode(0, new ListNode(5, new ListNode(9)))));
        ListNode head2 = new ListNode(-10, new ListNode(-3));
        TreeNode treeNode = new ConvertSortedLinkedListToBST().sortedListToBST(head);
        System.out.println("s");
        //TreeNode treeNode = new ConvertSortedLinkedListToBST().sortedListToBST(head2);


    }
}
