import java.util.*;

class Solution { 
    // Helper structure to hold both the row level and the node value
    private static class NodeInfo {
        int row;
        int val;
        NodeInfo(int row, int val) {
            this.row = row;
            this.val = val;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) { 
        List<List<Integer>> ans = new ArrayList<>(); 
        if (root == null) return ans; 
        
        int col = 0; 
        // queue tracks: Entry < Node, Entry < Col, Row > >
        Queue<Map.Entry<TreeNode, Map.Entry<Integer, Integer>>> queue = new ArrayDeque<>(); 
        
        // map tracks: Column -> List of NodeInfo elements
        Map<Integer, ArrayList<NodeInfo>> map = new HashMap<>(); 
        
        // Initialize with root at col = 0, row = 0
        queue.offer(new AbstractMap.SimpleEntry<>(root, new AbstractMap.SimpleEntry<>(0, 0))); 
        
        int min = 0; 
        int max = 0; 
        
        while (!queue.isEmpty()) { 
            Map.Entry<TreeNode, Map.Entry<Integer, Integer>> removed = queue.poll(); 
            TreeNode node = removed.getKey(); 
            col = removed.getValue().getKey(); 
            int row = removed.getValue().getValue(); 
            
            if (node != null) { 
                if (!map.containsKey(col)) { 
                    map.put(col, new ArrayList<>()); 
                } 
                // Save the node's value along with its row level
                map.get(col).add(new NodeInfo(row, node.val)); 
                
                min = Math.min(min, col); 
                max = Math.max(max, col); 
                
                // Progress down the tree, incrementing row and adjusting col
                if (node.left != null) {
                    queue.offer(new AbstractMap.SimpleEntry<>(node.left, new AbstractMap.SimpleEntry<>(col - 1, row + 1))); 
                }
                if (node.right != null) {
                    queue.offer(new AbstractMap.SimpleEntry<>(node.right, new AbstractMap.SimpleEntry<>(col + 1, row + 1))); 
                }
            } 
        } 
        
        for (int i = min; i <= max; i++) { 
            if (map.containsKey(i)) {
                ArrayList<NodeInfo> currentColumnNodes = map.get(i);
                
                // Custom sort: Sort by Row ascending (top to bottom). 
                // If rows match, sort by Value ascending.
                Collections.sort(currentColumnNodes, (a, b) -> {
                    if (a.row != b.row) {
                        return a.row - b.row;
                    }
                    return a.val - b.val;
                }); 
                
                // Extract the sorted values into a clean integer list
                List<Integer> sortedValues = new ArrayList<>();
                for (NodeInfo info : currentColumnNodes) {
                    sortedValues.add(info.val);
                }
                ans.add(sortedValues); 
            }
        } 
        return ans; 
    } 
}
