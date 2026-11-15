class Pair{
    int row;
    int col;
    int tm;
    Pair(int row,int col, int tm){
        this.row = row;
        this.col = col;
        this.tm = tm;
    }
}


class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int tm = 0;
        int cntFresh = 0;
        Queue<Pair> q = new LinkedList<>();
        int[][] visited = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 2){
                    q.add(new Pair(i,j,0));
                    visited[i][j] = 2;
                }else{
                    visited[i][j] = 0;
                }
                if(grid[i][j] == 1){
                    cntFresh++;
                }
            }
        }

        int cnt = 0;
        int[] dRow = {-1,1,0,0};
        int[] dCol = {0,0,1,-1};

        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().tm;
            tm = Math.max(t,tm);
            q.remove();
            for(int i=0;i<4;i++){
                int nRow = r+dRow[i];
                int nCol = c+dCol[i];
                if(nRow>=0 && nRow<m && nCol>=0 && nCol<n &&
                visited[nRow][nCol]==0 && grid[nRow][nCol]==1){
                    q.add(new Pair(nRow,nCol,t+1));
                    visited[nRow][nCol] = 2;
                    cnt++;
                }
            }

        }
        if(cnt != cntFresh) return -1;
        return tm;
 
    }
}