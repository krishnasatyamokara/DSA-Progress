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
    private List<Integer> list = new ArrayList<>();
    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
    public boolean findTarget(TreeNode root, int k) {
        inorder(root);
        int left = 0;
        int right = list.size()-1;
        while(left < right){
            int sum = list.get(left)+list.get(right);
            if(sum == k) return true;
            else if(sum > k){
                right--;
            }else{
                left++;
            }
        }
        return false;
    }
}