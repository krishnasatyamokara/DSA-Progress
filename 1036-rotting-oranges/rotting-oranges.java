
class Solution {

    private void dfs(int row, int col, int t,
                     int[][] grid, int[][] time) {

        int m = grid.length;
        int n = grid[0].length;

        // Already reached this cell at an earlier or equal time
        if (time[row][col] <= t) return;

        time[row][col] = t;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        for (int k = 0; k < 4; k++) {

            int newRow = row + dRow[k];
            int newCol = col + dCol[k];

            if (newRow >= 0 && newRow < m &&
                newCol >= 0 && newCol < n &&
                grid[newRow][newCol] == 1) {

                dfs(newRow, newCol, t + 1, grid, time);
            }
        }
    }

    public int orangesRotting(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] time = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }

        // Start DFS from every initially rotten orange
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 2) {
                    dfs(i, j, 0, grid, time);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    if (time[i][j] == Integer.MAX_VALUE)
                        return -1;

                    ans = Math.max(ans, time[i][j]);
                }
            }
        }

        return ans;
    }
}
