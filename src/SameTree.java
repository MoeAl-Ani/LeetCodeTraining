import java.util.Stack;

public class SameTree {
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<Integer> one = new Stack<>();
        Stack<Integer> two = new Stack<>();
        travers(p, one);
        travers(q, two);
        return one.equals(two);
    }

    private void travers(TreeNode node, Stack<Integer> stack) {
        if (node == null) {
            stack.push(null);
            return;
        }

        stack.push(node.val);
        travers(node.left, stack);
        travers(node.right, stack);
    }

    public static void main(String[] args) {
        TreeNode sameTree = new TreeNode(1, new TreeNode(0), new TreeNode(2));
        TreeNode sameTree2 = new TreeNode(1, new TreeNode(0), new TreeNode(3));
        TreeNode sameTree3 = new TreeNode(1, new TreeNode(2), null);
        TreeNode sameTree4 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("expected true : " + new SameTree().isSameTree(sameTree, sameTree));
        System.out.println("expected false : " + new SameTree().isSameTree(sameTree, sameTree2));
        System.out.println("expected false : " + new SameTree().isSameTree(sameTree3, sameTree4));
    }

}
