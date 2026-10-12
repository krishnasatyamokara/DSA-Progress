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
    private int preIdx = 0;
    private TreeNode construct(int[] preorder, int[] inorder,int start, int end){
        if(start > end) return null;
        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);
        int idx = 0;
        while(inorder[idx] != rootVal){
            idx++;
        }
        root.left = construct(preorder,inorder,start,idx-1);
        root.right = construct(preorder,inorder,idx+1,end);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIdx = 0;
        return construct(preorder,inorder,0,inorder.length-1);
    }
}