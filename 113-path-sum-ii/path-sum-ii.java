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
    List<List<Integer>> ans = new ArrayList<>();
    private void helper(TreeNode root, int target, List<Integer> curr){
        if(root == null) return;
        curr.add(root.val);
        
        if(target == root.val && root.left == null && root.right == null){
            ans.add(new ArrayList<>(curr));
        }
        else{
            int newTarget = target-root.val;
            helper(root.left,newTarget,curr);
            helper(root.right,newTarget,curr);
        }
        curr.remove(curr.size() - 1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList<>();
        helper(root,targetSum,new ArrayList<>());
        return ans;
    }
}