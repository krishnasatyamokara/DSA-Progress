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
    private int sum = 0;
    public void helper(TreeNode root,String path){
        if(root == null) return;
        path += root.val;
        if(root.left == null && root.right == null){
            sum += Integer.parseInt(path);
            return;
        }
        helper(root.left,path);
        helper(root.right,path);
    }
    public int sumNumbers(TreeNode root) {
        sum = 0;
        helper(root,"");
        return sum;
    }
}