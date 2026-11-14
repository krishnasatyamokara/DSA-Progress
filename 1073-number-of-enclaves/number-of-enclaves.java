class Solution {

    private void dfs(int row, int col, int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        grid[row][col] = 0;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        for (int k = 0; k < 4; k++) {

            int newRow = row + dRow[k];
            int newCol = col + dCol[k];

            if (newRow >= 0 && newRow < m &&
                newCol >= 0 && newCol < n &&
                grid[newRow][newCol] == 1) {

                dfs(newRow, newCol, grid);
            }
        }
    }

    public int numEnclaves(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // First and last rows
        for (int j = 0; j < n; j++) {

            if (grid[0][j] == 1)
                dfs(0, j, grid);

            if (grid[m - 1][j] == 1)
                dfs(m - 1, j, grid);
        }

        // First and last columns
        for (int i = 0; i < m; i++) {

            if (grid[i][0] == 1)
                dfs(i, 0, grid);

            if (grid[i][n - 1] == 1)
                dfs(i, n - 1, grid);
        }

        // Count remaining land cells
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1)
                    ans++;
            }
        }

        return ans;
    }
}