class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        TreeNode originalRoot = root;
        TreeNode parent = null;

        // Search for the node
        while (root != null && root.val != key) {
            parent = root;

            if (root.val < key) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        // Key not found
        if (root == null) return originalRoot;

        TreeNode replacement;

        // Case 1: No children
        if (root.left == null && root.right == null) {
            replacement = null;
        }
        // Case 2: Only one child
        else if (root.left == null || root.right == null) {
            replacement = (root.left == null) ? root.right : root.left;
        }
        // Case 3: Both children exist
        else {
            TreeNode temp = root.left;
            replacement = root.right;

            TreeNode leftmost = replacement;

            while (leftmost.left != null) {
                leftmost = leftmost.left;
            }

            leftmost.left = temp;
        }

        // Connect replacement to the parent
        if (parent == null) {
            return replacement;
        } else if (parent.left == root) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }

        return originalRoot;
    }
}