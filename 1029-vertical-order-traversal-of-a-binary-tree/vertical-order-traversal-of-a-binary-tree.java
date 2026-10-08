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

    private TreeMap<Integer, TreeMap<Integer, List<Integer>>> map
        = new TreeMap<>();

    private void solve(TreeNode curr, int col, int row) {

        if (curr == null) {
            return;
        }

        if (!map.containsKey(col)) {
            map.put(col, new TreeMap<>());
        }

        if (!map.get(col).containsKey(row)) {
            map.get(col).put(row, new ArrayList<>());
        }

        map.get(col).get(row).add(curr.val);

        solve(curr.left, col - 1, row + 1);
        solve(curr.right, col + 1, row + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        solve(root, 0, 0);

        List<List<Integer>> ans = new ArrayList<>();

        for (TreeMap<Integer, List<Integer>> rows : map.values()) {

            List<Integer> column = new ArrayList<>();

            for (List<Integer> values : rows.values()) {

                Collections.sort(values);

                for (int val : values) {
                    column.add(val);
                }
            }

            ans.add(column);
        }

        return ans;
    }
}