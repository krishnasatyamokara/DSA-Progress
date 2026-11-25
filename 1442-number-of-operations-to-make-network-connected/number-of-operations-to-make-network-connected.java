import java.util.*;

class Solution {
    public int makeConnected(int n, int[][] connections) {
        // Step 1: Check if we have enough cables
        if (connections.length < n - 1) {
            return -1;
        }
        
        // Step 2: Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] conn : connections) {
            adj.get(conn[0]).add(conn[1]);
            adj.get(conn[1]).add(conn[0]);
        }
        
        boolean[] visited = new boolean[n];
        int components = 0;
        
        // Step 3: Traverse using a queue (BFS) to count clusters
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : adj.get(node)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
        
        // Step 4: Return bridges needed
        return components - 1;
    }
}
