class Solution {
    // 1. Your same parent tracking methodology!
    public void markParent(TreeNode root, Map<TreeNode, TreeNode> parent_track, Map<TreeNode, Integer> nodeMap, int startValue, int destValue, TreeNode[] targets) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            // Cache target node pointers so we don't have to search for them later
            if (node.val == startValue) targets[0] = node;
            if (node.val == destValue) targets[1] = node;

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

    public String getDirections(TreeNode root, int startValue, int destValue) {
        Map<TreeNode, TreeNode> parent_track = new HashMap<>();
        TreeNode[] targets = new TreeNode[2]; // targets[0] = startNode, targets[1] = destNode
        
        markParent(root, parent_track, null, startValue, destValue, targets);
        
        TreeNode startNode = targets[0];
        TreeNode destNode = targets[1];

        // 2. BFS Setup
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        
        // Track the edge directions: cameFrom.put(childNode, EdgeInfo)
        // EdgeInfo tells us how we reached childNode from its previous neighbor
        Map<TreeNode, EdgeInfo> cameFrom = new HashMap<>(); 
        
        queue.offer(startNode);
        visited.add(startNode);
        
        boolean found = false;
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(curr == destNode) {
                found = true;
                break;
            }
            
            // Move Up (Parent)
            TreeNode parent = parent_track.get(curr);
            if(parent != null && !visited.contains(parent)){
                queue.offer(parent);
                visited.add(parent);
                cameFrom.put(parent, new EdgeInfo(curr, "U"));
            }
            // Move Left
            if(curr.left != null && !visited.contains(curr.left)){
                queue.offer(curr.left);
                visited.add(curr.left);
                cameFrom.put(curr.left, new EdgeInfo(curr, "L"));
            }
            // Move Right
            if(curr.right != null && !visited.contains(curr.right)){
                queue.offer(curr.right);
                visited.add(curr.right);
                cameFrom.put(curr.right, new EdgeInfo(curr, "R"));
            }
        }
        
        // 3. Backtrack from destNode to startNode to build the directions
        StringBuilder ans = new StringBuilder();
        TreeNode curr = destNode;
        while(curr != startNode) {
            EdgeInfo edge = cameFrom.get(curr);
            ans.append(edge.dir);
            curr = edge.from; // Step backward
        }
        
        // Since we backtracked from Dest -> Start, reverse it to get Start -> Dest
        return ans.reverse().toString();
    }
    
    // Simple helper class to track the path backward
    class EdgeInfo {
        TreeNode from;
        String dir;
        EdgeInfo(TreeNode from, String dir) {
            this.from = from;
            this.dir = dir;
        }
    }
}
