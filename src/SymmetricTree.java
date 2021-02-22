import java.util.ArrayList;
import java.util.List;

public class SymmetricTree {

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

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        if (root.left.val != root.right.val) return false;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        traversLeftOriented(root.left, left);
        traversRightOriented(root.right, right);

        if (left.size() != right.size()) return false;
        if (!left.get(0).equals(right.get(0))) return false;

        if (!checkEquality(left, right)) return false;
        return true;
    }

    private boolean checkEquality(List<Integer> left, List<Integer> right) {
        return left.equals(right);
    }

    private void traversLeftOriented(TreeNode node, List<Integer> stack) {
        if (node == null) {
            stack.add(null);
            return;
        }
        stack.add(node.val);
        traversLeftOriented(node.left, stack);
        traversLeftOriented(node.right, stack);
    }

    private void traversRightOriented(TreeNode node, List<Integer> stack) {
        if (node == null) {
            stack.add(null);
            return;
        }
        stack.add(node.val);
        traversRightOriented(node.right, stack);
        traversRightOriented(node.left, stack);
    }

    public static void main(String[] args) {
        TreeNode symmetricTree = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        TreeNode symmetricTree2 = new TreeNode(1);
        TreeNode symmetricTree3 = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, new TreeNode(3), null));
        TreeNode nonSymmetricTree = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(3), new TreeNode(4)));
        TreeNode nonSymmetricTree2 = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)));
        SymmetricTree service = new SymmetricTree();
        System.out.println("expected true : " + service.isSymmetric(symmetricTree));
        System.out.println("expected false : " + service.isSymmetric(nonSymmetricTree));
        System.out.println("expected false : " + service.isSymmetric(nonSymmetricTree2));
        System.out.println("expected true : " + service.isSymmetric(symmetricTree2));
        System.out.println("expected true : " + service.isSymmetric(symmetricTree3));

    }
}
