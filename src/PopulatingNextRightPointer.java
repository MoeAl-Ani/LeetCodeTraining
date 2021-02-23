import java.util.ArrayDeque;
import java.util.Queue;

public class PopulatingNextRightPointer {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> q = new ArrayDeque<>();
        root.next = null;
        q.add(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = q.remove();
                if (i+1 < levelSize) node.next = q.peek();

                if (node.left != null)
                    q.add(node.left);

                if (node.right != null)
                    q.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node t1 = new Node(1, new Node(2, new Node(4), new Node(5), null), new Node(3, new Node(6), new Node(7), null), null);
        Node connect = new PopulatingNextRightPointer().connect(t1);
        System.out.println("s");
    }
}
