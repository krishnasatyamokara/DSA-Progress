class Solution {
    int m, n;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }
    private void dfs(char[][] board, int x, int y) {
        board[x][y] = '#';
        int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] v : d) {
            int a = x + v[0], b = y + v[1];
            if (a >= 0 && a < m && b >= 0 && b < n && board[a][b] == 'O')
                dfs(board, a, b);
        }
    }
}