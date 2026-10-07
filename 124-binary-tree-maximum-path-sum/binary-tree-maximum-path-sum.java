/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int max = Integer.MIN_VALUE;
    private int Sum(TreeNode root){
        if(root == null) return 0;
        int leftPath = Math.max(0,Sum(root.left));
        int rightPath = Math.max(0,Sum(root.right));
        max = Math.max(max,leftPath+rightPath+root.val);
        return root.val+Math.max(leftPath,rightPath);
    }
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        Sum(root);
        return max;
        
    }
}