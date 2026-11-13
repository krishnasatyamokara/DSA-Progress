class Solution {
    public int numIslands(char[][] grid) {
        int cnt = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean visited[][] = new boolean[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    cnt++;
                    dfs(i,j,grid,visited);
                }
            }
        }
        return cnt;
    }

    private void dfs(int row, int col, char[][] grid, boolean[][] visited){
        int m = grid.length;
        int n = grid[0].length;

        visited[row][col] = true;

        int[] rowDir = {-1,1,0,0};
        int[] colDir = {0,0,1,-1};

        for(int k=0;k<4;k++){
            int newRow = row+rowDir[k];
            int newCol = col+colDir[k];

            if(newRow>=0 && newRow < m &&
                newCol>=0 && newCol < n && 
                !visited[newRow][newCol] &&
                grid[newRow][newCol] == '1'){
                
                dfs(newRow,newCol,grid,visited);

            }
        }
    }
}