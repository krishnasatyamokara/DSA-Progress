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
    TreeNode prev = null, first = null, sec = null;
    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        if(prev != null && root.val < prev.val){
            if(first == null) first = prev;
            sec = root;
        }
        prev = root;
        inorder(root.right);
    }
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = sec.val;
        sec.val = temp;
    }
}