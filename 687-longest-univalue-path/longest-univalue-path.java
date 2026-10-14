class Solution {
    int maxLength = 0;

    public int longestUnivaluePath(TreeNode root) {
        maxLength = 0;
        dfs(root);
        return maxLength;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int leftPath = dfs(node.left);
        int rightPath = dfs(node.right);
        int arrowLeft = 0;
        int arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft = leftPath + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight = rightPath + 1;
        }
        maxLength = Math.max(maxLength, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
