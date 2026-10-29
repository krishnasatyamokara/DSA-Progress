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
    private int idx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private TreeNode build(int[] preorder,int min,int max){
        if(idx == preorder.length) return null;
        int val = preorder[idx];
        if(val <= min || val >= max) return null;
        TreeNode root = new TreeNode(val);
        idx++;
        root.left = build(preorder,min,root.val);
        root.right = build(preorder,root.val,max);
        return root;
    }
}