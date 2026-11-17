class Solution {

    private boolean check(int start, int V, int[][] adj, int[] color){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0;
        while(!q.isEmpty()){
            int node = q.peek();
            q.remove();
            for(int it : adj[node]){
                if(color[it] == -1){
                    color[it] = 1-color[node];
                    q.add(it);
                }

                else if(color[it] == color[node]){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        int[] color = new int[V];
        Arrays.fill(color,-1);
        for(int i=0;i<V;i++){
            if(color[i] == -1){
                if(check(i,V,graph,color) == false) return false;
            }
        }
        return true;
    }
}



// dfs
// class Solution {

//     private boolean dfs(int node, int col, int[] color, int[][] adj) {

//         color[node] = col;
//         for(int it : adj[node]) {
//             if(color[it] == -1) {
//                 if(dfs(it, 1 - col, color, adj) == false) {
//                     return false;
//                 }
//             }

//             else if(color[it] == col) {
//                 return false;
//             }
//         }

//         return true;
//     }



//     public boolean isBipartite(int[][] graph) {
//         int V = graph.length;
//         int[] color = new int[V];
//         Arrays.fill(color,-1);

//         for(int i=0;i<V;i++){
//             if(color[i] == -1){
//                 if(dfs(i,0,color,graph) == false) return false;
//             }
//         }
//         return true;

//     }
// }