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
    public boolean dfs(TreeNode root, int target){
        if(root == null) return true;
        if(root.val != target) return false;
        return dfs(root.left,target) && dfs(root.right,target);
    }
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root,root.val);
    }
}