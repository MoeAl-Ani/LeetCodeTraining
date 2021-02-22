public class ValidateBinarySearchTree {

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

    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode node, Integer max, Integer min) {
        if (node == null) return true;
        if(max != null && node.val >= max || min != null && node.val <= min) return false;
        return isValid(node.left, node.val, min) && isValid(node.right, max, node.val);
    }

    public static void main(String[] args) {
        TreeNode valid = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode inValid = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        TreeNode inValid2 = new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        TreeNode inValid3 = new TreeNode(3, new TreeNode(1, new TreeNode(0), new TreeNode(2)), new TreeNode(5, new TreeNode(4), new TreeNode(6)));
        TreeNode inValid4 = new TreeNode(1, new TreeNode(1), null);
        TreeNode valid2 = new TreeNode(3, new TreeNode(1, new TreeNode(0), new TreeNode(2)), new TreeNode(5, new TreeNode(4), new TreeNode(6)));
        TreeNode invalid5 = new TreeNode(32, new TreeNode(26, new TreeNode(19, null, new TreeNode(27)), null), new TreeNode(47, null, new TreeNode(56)));


        System.out.println(new ValidateBinarySearchTree().isValidBST(valid));
        System.out.println(new ValidateBinarySearchTree().isValidBST(inValid));
        System.out.println(new ValidateBinarySearchTree().isValidBST(inValid2));
        System.out.println(new ValidateBinarySearchTree().isValidBST(inValid3));
        System.out.println(new ValidateBinarySearchTree().isValidBST(inValid4));
        System.out.println(new ValidateBinarySearchTree().isValidBST(valid2));
        System.out.println(new ValidateBinarySearchTree().isValidBST(invalid5));
    }
}
