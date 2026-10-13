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
    // private int sum = 0;
    public int helper(TreeNode root,int currNum){
        // if(root == null) return;
        // path += root.val;
        // if(root.left == null && root.right == null){
        //     sum += Integer.parseInt(path);
        //     return;
        // }
        // helper(root.left,path);
        // helper(root.right,path); //my approach
        if(root == null) return 0;
        currNum = currNum*10+root.val;
        if(root.left == null && root.right == null){
            return currNum;
        }
        return helper(root.left,currNum) + helper(root.right,currNum);
    }
    public int sumNumbers(TreeNode root) {
        // sum = 0;
        // helper(root,"");
        // return sum;

        return helper(root,0);
    }
}