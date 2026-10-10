import java.util.*;

class Solution {
    public void markParent(TreeNode root, Map<TreeNode, TreeNode> parent_track){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                    parent_track.put(node.left, node);
                }
                if(node.right != null){
                    queue.offer(node.right);
                    parent_track.put(node.right, node);
                }
            }
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent_track = new HashMap<>();
        markParent(root, parent_track);
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int curr_level = 0;
        
        queue.offer(target);
        visited.put(target, true); 

        while(!queue.isEmpty()){
            if(curr_level == k) break;
            curr_level++;
            
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode curr = queue.poll();
                if(curr.left != null && visited.get(curr.left) == null){
                    queue.offer(curr.left);
                    visited.put(curr.left, true);
                }
                if(curr.right != null && visited.get(curr.right) == null){
                    queue.offer(curr.right);
                    visited.put(curr.right, true);
                }
                if(parent_track.get(curr) != null && 
                   visited.get(parent_track.get(curr)) == null){
                    queue.offer(parent_track.get(curr));
                    visited.put(parent_track.get(curr), true);
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            ans.add(queue.poll().val);
        }
        return ans;
    }
}
