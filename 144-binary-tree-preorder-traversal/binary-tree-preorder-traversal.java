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
    // private List<Integer> bt = new ArrayList<>();
    // private void preorder(TreeNode root){
    //     if(root == null) return;
    //     bt.add(root.val);
    //     preorder(root.left);
    //     preorder(root.right);
    // } -- recursive approach
    public List<Integer> preorderTraversal(TreeNode root) {
        // preorder(root);
        // return bt;

        
        List<Integer> bt = new ArrayList<>();
        if(root == null) return bt;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            bt.add(root.val);
            if(root.right != null)
            stack.push(root.right);
            if(root.left != null){
                stack.push(root.left);
            }
        }
        return bt;
        
    }
}