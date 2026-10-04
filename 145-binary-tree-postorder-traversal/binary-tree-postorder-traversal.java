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
    // private void postorder(TreeNode root){
    //     if(root == null) return;
    //     postorder(root.left);
    //     postorder(root.right);
    //     bt.add(root.val);
    // } -- recursive approch
    public List<Integer> postorderTraversal(TreeNode root) {
        // iterative approach using 2
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        List<Integer> bt = new ArrayList<>();
        if(root == null) return bt;
        st1.push(root);
        while(!st1.isEmpty()){
            TreeNode node = st1.pop();
            st2.push(node);
            if(node.left != null){
                st1.push(node.left);
            }
            if(node.right != null)
            st1.push(node.right);
        }
        while(!st2.isEmpty()){
            TreeNode node = st2.pop();
            bt.add(node.val);
        }
        return bt;

        // postorder(root);
        // return bt;
    }
}