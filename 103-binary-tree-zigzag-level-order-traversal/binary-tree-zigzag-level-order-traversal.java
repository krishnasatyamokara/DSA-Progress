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
    private List<List<Integer>> ans = new ArrayList<>();
    private Queue<TreeNode> queue = new LinkedList<>();
    private boolean leftToRight = true;
    private void helper(TreeNode root){
        if(root == null) return;
        queue.offer(root);
        while(!queue.isEmpty()){
        int n = queue.size();
        List<Integer> level = new ArrayList<>();
        for(int i=0;i<n;i++){
            TreeNode node = queue.poll();
            level.add(node.val);
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        if(!leftToRight){
            Collections.reverse(level);

        }
        leftToRight = !leftToRight;
        ans.add(level);
        }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        helper(root);
        return ans;
    }
}