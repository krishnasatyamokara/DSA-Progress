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

//  class BSTIterator {

//     private ArrayList<Integer> list = new ArrayList<>();
//     private int idx = -1;

//     private void inorder(TreeNode root) {
//         if (root == null) return;

//         inorder(root.left);
//         list.add(root.val);
//         inorder(root.right);
//     }

//     public BSTIterator(TreeNode root) {
//         inorder(root);
//     }

//     public int next() {
//         idx++;
//         return list.get(idx);
//     }

//     public boolean hasNext() {
//         return idx + 1 < list.size();
//     }
// } --> brute force code which takes o(n) space complexity extra


class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        pushleft(root);
    }
    private void pushleft(TreeNode root){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode node = stack.pop();
        if(node.right != null){
            pushleft(node.right);
        }
        return node.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */