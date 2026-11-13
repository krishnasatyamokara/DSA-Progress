class Solution {

    public void dfs(int node, int[][] isConnected, boolean[] visited){
        visited[node] = true;
        
        for (int j = 0; j < isConnected.length; j++) {
            
            if (isConnected[node][j] == 1 && !visited[j]) {
                dfs(j, isConnected, visited);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;
        boolean visited[] = new boolean[V];
        int cnt = 0;

        for(int i=0;i<V;i++){
            if(!visited[i]){
                cnt++;
                dfs(i,isConnected,visited);
            }
        }
        return cnt;
    }
}