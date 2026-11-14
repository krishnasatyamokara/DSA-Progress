class Solution {

    private int dfs(int row, int col, boolean[][] visited,
                    int[][] grid, boolean[] flag) {

        visited[row][col] = true;

        int m = grid.length;
        int n = grid[0].length;

        int count = 1;

        // Check whether the current cell is on the boundary
        if (row == 0 || row == m - 1 ||
            col == 0 || col == n - 1) {
            flag[0] = true;
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, 1, -1};

        for (int k = 0; k < 4; k++) {

            int newRow = row + dRow[k];
            int newCol = col + dCol[k];

            if (newRow >= 0 && newRow < m &&
                newCol >= 0 && newCol < n &&
                !visited[newRow][newCol] &&
                grid[newRow][newCol] == 1) {

                count += dfs(newRow, newCol, visited, grid, flag);
            }
        }

        return count;
    }

    public int numEnclaves(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 && !visited[i][j]) {

                    boolean[] flag = {false};

                    int count = dfs(i, j, visited, grid, flag);

                    if (!flag[0]) {
                        ans += count;
                    }
                }
            }
        }

        return ans;
    }
}