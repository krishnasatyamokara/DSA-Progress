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
    // private void inorder(TreeNode root){
    //     if(root == null) return ;
    //     inorder(root.left);
    //     bt.add(root.val);
    //     inorder(root.right);
    // } -- using recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        // inorder(root);
        // return bt;

        // using iterative approach

        List<Integer> bt = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(true){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                if(stack.isEmpty()) break;
                node = stack.pop();
                bt.add(node.val);
                node = node.right;
            }
        }
        return bt;
    }
}