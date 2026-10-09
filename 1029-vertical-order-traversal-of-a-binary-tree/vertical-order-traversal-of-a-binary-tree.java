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
    private TreeMap<Integer,TreeMap<Integer,ArrayList<Integer>>> map;
    public void solve(TreeNode root,int col, int row){
        if(root == null) return;
        if(!map.containsKey(col)){
            map.put(col,new TreeMap<>());
        }
        if(!map.get(col).containsKey(row)){
            map.get(col).put(row,new ArrayList<>());
        }
        map.get(col).get(row).add(root.val);

        solve(root.left,col-1,row+1);
        solve(root.right,col+1,row+1);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        map = new TreeMap<>();
        solve(root,0,0);
        for(TreeMap<Integer,ArrayList<Integer>> rows :  map.values() ){
            List<Integer> column = new ArrayList<>();
            for(ArrayList<Integer> arr : rows.values()){
                Collections.sort(arr);
                for(int val : arr){
                    column.add(val);
                }
            }
            
            ans.add(column);
        }
        return ans;
    }
}